// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.dr.vpc;

import aws.proserve.bcs.dr.util.Preconditions;

import java.util.regex.Pattern;

public class Cidr {
    private static final String CIDR_REGEX = "([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)/([0-9]+)";
    private static final Pattern CIDR_PATTERN = Pattern.compile(CIDR_REGEX);

    public static boolean matches(String cidr, String newCidr) {
        final var matcher = CIDR_PATTERN.matcher(cidr);
        final var newmatcher = CIDR_PATTERN.matcher(newCidr);
        return matcher.find()
                && newmatcher.find()
                && matcher.group(5).equals(newmatcher.group(5));
    }

    private final String block;
    private final int[] items;

    public Cidr(String block) throws IllegalArgumentException {
        this.block = block;
        this.items = new int[5];

        final var matcher = CIDR_PATTERN.matcher(block);
        Preconditions.checkArgument(matcher.find(), block);

        for (int i = 0; i < items.length; i++) {
            items[i] = Integer.parseInt(matcher.group(i + 1));
        }
    }

    Cidr(int[] items) {
        this.items = items;
        this.block = String.format("%s.%s.%s.%s/%s", items[0], items[1], items[2], items[3], items[4]);
    }

    public String getBlock() {
        return block;
    }

    public long getSize() {
        final var m = 32 - items[4];
        return m == 0 ? 0 : (long) Math.pow(2, m);
    }

    /**
     * @param offset should be between 0 and 255
     */
    public String findAddress(int offset) {
        final var size = getSize();
        Preconditions.checkArgument(offset >= 0 && offset <= 255, "offset should be between 0 and 255");
        Preconditions.checkArgument(offset <= size, "offset should be less than " + size);
        final var mask = items[4];
        final var newItems = new int[4];
        newItems[0] = items[0];
        newItems[1] = items[1];
        newItems[2] = items[2];

        int a, b, m;
        a = b = 0xFF;
        if (mask <= 24) {
            newItems[3] = offset;
        } else {
            m = mask - 24;
            a <<= (8 - m);
            b >>= m;
            newItems[3] = (items[3] & a) | (offset & b);
        }

        return String.format("%s.%s.%s.%s", newItems[0], newItems[1], newItems[2], newItems[3]);
    }

    public boolean canMask(Cidr that) {
        if (items[4] > that.items[4]) {
            return false;
        }

        final var mask = items[4];
        int a, b, m;
        a = b = 0xFF;
        if (mask <= 8) {
            a <<= (8 - mask);
            b = (items[0] & a) ^ (that.items[0] & a);
            return b == 0;
        } else if (mask <= 16) {
            m = mask - 8;
            a <<= (8 - m);
            b = (items[1] & a) ^ (that.items[1] & a);
            return b == 0 && items[0] == that.items[0];
        } else if (mask <= 24) {
            m = mask - 16;
            a <<= (8 - m);
            b = (items[2] & a) ^ (that.items[2] & a);
            return b == 0 && items[0] == that.items[0] && items[1] == that.items[1];
        } else {
            m = mask - 24;
            a <<= (8 - m);
            b = (items[3] & a) ^ (that.items[3] & a);
            return b == 0 && items[0] == that.items[0] && items[1] == that.items[1] && items[2] == that.items[2];
        }
    }

    public Cidr mask(Cidr cidr) {
        final var newItems = new int[5];
        newItems[4] = cidr.items[4];

        final var mask = items[4];
        int a, b, m;
        a = b = 0xFF;
        if (mask <= 8) {
            a <<= (8 - mask);
            b >>= mask;
            newItems[0] = (items[0] & a) | (cidr.items[0] & b);
            newItems[1] = cidr.items[1];
            newItems[2] = cidr.items[2];
            newItems[3] = cidr.items[3];
        } else if (mask <= 16) {
            m = mask - 8;
            a <<= (8 - m);
            b >>= m;
            newItems[0] = items[0];
            newItems[1] = (items[1] & a) | (cidr.items[1] & b);
            newItems[2] = cidr.items[2];
            newItems[3] = cidr.items[3];
        } else if (mask <= 24) {
            m = mask - 16;
            a <<= (8 - m);
            b >>= m;
            newItems[0] = items[0];
            newItems[1] = items[1];
            newItems[2] = (items[2] & a) | (cidr.items[2] & b);
            newItems[3] = cidr.items[3];
        } else {
            m = mask - 24;
            a <<= (8 - m);
            b >>= m;
            newItems[0] = items[0];
            newItems[1] = items[1];
            newItems[2] = items[2];
            newItems[3] = (items[3] & a) | (cidr.items[3] & b);
        }

        return new Cidr(newItems);
    }
}
