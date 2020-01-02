package com.cidr.perform.analysis.constants;

import java.util.regex.Pattern;

/* *
 * CIDR Constant Class to Aid CIDR Analysis
 * @author  Kalai
 * @version 1.0
 */
public class CidrConstants {
    public static final String INPUT_FILE_PATH = "src/main/resources/cidrblocks.txt";
    public static final String CIDR_SEPARATOR = ",";
    public static final String VALID_IP_WARNING = "Provide Valid CIDR BLOCK";
    public static final String IP_SEPARATOR = ".";
    public static final String EMPTY_SEPARATOR = " ";
    public static final String RANGE_SEPARATOR = "/";
    public static final Pattern CIDR_PATTERN = Pattern.compile("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}\\/\\d{1,2}");
    public static final long UNSIGNED_MASK = 0xffffffffL;
    public static final int DEFAULT_VALUE = 0;
    public static final int IP_MAX_VALUE = 255;
    public static final long IP_MAX_BIT = 8;
    public static final int DOT_LENGTH = 3;
    public static final String START_BRACKET = "[";
    public static final String END_BRACKET = " ]";
    public static final String STRING_FORMATTER = "%3s";
    public static final String ZERO_REPLACER = "0";
    public static final String MATRIX_FORMAT = " 000/111 : ADJACENT :CONTAINS: INTERSECTS ";

}
