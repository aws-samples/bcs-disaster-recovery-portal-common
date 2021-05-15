// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.machine;

import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.model.DescribeExecutionRequest;
import com.amazonaws.services.stepfunctions.model.DescribeExecutionResult;
import com.amazonaws.services.stepfunctions.model.ExecutionFailedEventDetails;
import com.amazonaws.services.stepfunctions.model.GetExecutionHistoryRequest;
import com.amazonaws.services.stepfunctions.model.HistoryEvent;
import com.amazonaws.services.stepfunctions.model.ListStateMachinesRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;
import com.amazonaws.services.stepfunctions.model.StateMachineListItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class AbstractStateMachine {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final AWSStepFunctions machine;
    protected final ObjectMapper mapper;

    protected AbstractStateMachine(AWSStepFunctions machine, ObjectMapper mapper) {
        this.machine = machine;
        this.mapper = mapper;
    }

    private String getMachineArn() {
        final var name = getClass().getSimpleName();
        return machine.listStateMachines(new ListStateMachinesRequest())
                .getStateMachines().stream()
                .filter(item -> item.getName().contains(name)).findFirst()
                .map(StateMachineListItem::getStateMachineArn)
                .orElseThrow(() -> new StateMachineException("Unable to find state machine " + name));
    }

    /**
     * @return execution Arn.
     */
    public String executeAsync(Object input) throws StateMachineException {
        final String inputString;
        try {
            inputString = mapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            log.warn("Unable to serialize object", e);
            throw new StateMachineException("Unable to serialize object " + input, e);
        }

        final StartExecutionResult startResult = machine.startExecution(new StartExecutionRequest()
                .withStateMachineArn(getMachineArn())
                .withInput(inputString));
        log.info("State machine [{}] started", getClass().getSimpleName());

        return startResult.getExecutionArn();
    }

    public String execute(Object input) throws StateMachineException {
        final var execArn = executeAsync(input);
        DescribeExecutionResult execResult;
        do {
            execResult = machine.describeExecution(new DescribeExecutionRequest().withExecutionArn(execArn));

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.warn("State machine execution interrupted", e);
                break;
            }
        } while (execResult.getStatus().equals("RUNNING"));

        if (execResult.getStatus().equals("SUCCEEDED")) {
            log.info("Machine [{}] ended successfully.", getClass().getSimpleName());
            return execResult.getOutput();
        } else {
            final var error = machine.getExecutionHistory(new GetExecutionHistoryRequest()
                    .withExecutionArn(execArn)
                    .withReverseOrder(true))
                    .getEvents().stream()
                    .filter(event -> event.getType().equals("ExecutionFailed"))
                    .findFirst()
                    .map(HistoryEvent::getExecutionFailedEventDetails)
                    .map(ExecutionFailedEventDetails::getError)
                    .orElse(execResult.getStatus());
            throw new StateMachineException(error);
        }
    }
}
