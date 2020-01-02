package com.cidr.perform.analysis.utils;

import com.cidr.perform.analysis.constants.CidrConstants;
import com.cidr.perform.analysis.model.CidrModel;
import com.google.common.base.Splitter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/* *
 * Utility Class to Support CIDR Analysis
 * @author  Kalai
 * @version 1.0
 */
public class Cidrutils {
    /**
     * This method is used to obtain a list of CIDR Sets from a Text File.
     * * @param String FilePath
     * @return ArrayList<LinkedHashSet<CidrModel>>
     */
    public static ArrayList<LinkedHashSet<CidrModel>> mapToCIDR(String filePath) throws IOException {
        Path cidrFile = Paths.get(filePath);
        ArrayList<LinkedHashSet<CidrModel>> inputData = Files.lines(cidrFile).map(cidrList ->
        {
            return Splitter.on(CidrConstants.CIDR_SEPARATOR).splitToList(cidrList).stream()
                    .map(ips -> new CidrModel(ips))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }).collect(Collectors.toCollection(ArrayList::new));
        return inputData;

    }
    /**
     * This method is used to the bit Ranges for the given CIDR Block.
     * * @param int bits This is the Range Value Specified n Cidr Block 188.176.87.26/bits
     * @return Integer through Functional Interface which takes Lambda Expression
     */
    public static IntFunction<Integer> MASK_FUNCTION = bits -> (int) Math.pow(2, 32 - bits) - 1;

    /**
     * This method is used to obtain IP Start and End Ranges from IP
     * *  @param String IPAddress
     * @return List<Long> : Start and End Ip Range of given IPAddress
     */
    public static List<Long> getIPRange(String IPAddress) {
        List<Long> ipRanges = new ArrayList<>();
        long ipStartRange;
        long ipEndRange;
        if (!CidrConstants.CIDR_PATTERN.matcher(IPAddress).matches())
            throw new IllegalArgumentException(CidrConstants.VALID_IP_WARNING);
        List<String> ipTokens = Splitter.on(CidrConstants.IP_SEPARATOR).splitToList(IPAddress);

        ipStartRange = CidrConstants.DEFAULT_VALUE;
        ipEndRange = CidrConstants.DEFAULT_VALUE;
        for (int i = CidrConstants.DEFAULT_VALUE; i < CidrConstants.DOT_LENGTH; i++) {
            int temp = Integer.parseInt(ipTokens.get(i));
            if (temp < CidrConstants.DEFAULT_VALUE || temp > CidrConstants.IP_MAX_VALUE)
                throw new IllegalArgumentException(CidrConstants.VALID_IP_WARNING);
            ipStartRange += temp;
            ipStartRange <<= CidrConstants.IP_MAX_BIT;
        }
        List<String> rangeTokens = Splitter.on(CidrConstants.RANGE_SEPARATOR).splitToList(ipTokens.get(3));
        long temp = Long.parseLong(rangeTokens.get(0));
        ipStartRange += temp;
        long mask = CidrConstants.UNSIGNED_MASK;
        int bits = Integer.parseInt(rangeTokens.get(1));
        int change = Cidrutils.MASK_FUNCTION.apply(bits);
        mask -= change;
        ipStartRange &= mask;
        ipEndRange = ipStartRange + change;

        ipRanges.add(CidrConstants.DEFAULT_VALUE, ipStartRange);
        ipRanges.add(CidrConstants.DEFAULT_VALUE + 1, ipEndRange);

        return ipRanges;
    }

    /**
     * This method is used to obtain Ip as long  from Ip in String format
     * *  @param long ipValue
     * @return String
     */
    public static String convertToIPString(long ipValue) {
        StringBuilder ipString = new StringBuilder();
        ipString.append(ipValue & CidrConstants.IP_MAX_VALUE);
        ipValue >>= CidrConstants.IP_MAX_BIT;
        for (int i = CidrConstants.DEFAULT_VALUE; i < CidrConstants.DOT_LENGTH; i++) {
            ipString.insert(CidrConstants.DEFAULT_VALUE, CidrConstants.IP_SEPARATOR);
            ipString.insert(CidrConstants.DEFAULT_VALUE, ipValue & CidrConstants.IP_MAX_VALUE);
            ipValue >>= CidrConstants.IP_MAX_BIT;
        }
        return ipString.toString();
    }
}
