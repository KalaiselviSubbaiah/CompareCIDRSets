package com.cidr.perform.analysis.model;

import com.cidr.perform.analysis.constants.CidrConstants;
import com.cidr.perform.analysis.utils.Cidrutils;

import java.util.List;
import java.util.function.Predicate;


/* *
 * CIDR Model Class with CIDR Attributes
 * @author  Kalai
 * @version 1.0
 */
public class CidrModel {

    private long ipStartRange;
    private long ipEndRange;
    private String IPAddress;

    public long getIpStartRange() {
        return ipStartRange;
    }

    public void setIpStartRange(long ipStartRange) {
        this.ipStartRange = ipStartRange;
    }

    public long getIpEndRange() {
        return ipEndRange;
    }

    public void setIpEndRange(long ipEndRange) {
        this.ipEndRange = ipEndRange;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public String toString() {
        return getIPAddress();
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    /**
     * Constructor
     * * @param String IPAddress
     * Initiaze Start and End Ip range from given ip String
     */
    public CidrModel(String IPAddress) {
        this.IPAddress = IPAddress;
        //Method to get IP Ranges
        List<Long> ipRanges = Cidrutils.getIPRange(IPAddress);
        this.setIpStartRange(ipRanges.get(CidrConstants.DEFAULT_VALUE));
        this.setIpEndRange(ipRanges.get(CidrConstants.DEFAULT_VALUE + 1));
    }

    /**
     * Equals Override
     */
    public boolean equals(Object cidrCompared) {
        if (!(cidrCompared instanceof CidrModel))
            return false;
        else {
            CidrModel compareCIDR = (CidrModel) cidrCompared;
            return cidrEquals.test(compareCIDR);
        }
    }
    /**
     * This method is used to Check if one CIDR Model Contains Other
     * * @param CidrModel
     * @return Boolean through Functional Interface which takes Lambda Expression
     */
    public Predicate<CidrModel> cidrContains = cidrCompared -> (this.ipStartRange < cidrCompared.getIpStartRange()
            && this.ipEndRange > cidrCompared.getIpEndRange());
    /**
     * This method is used to Check if one CIDR Model Intersects Other
     * * @param CidrModel
     * @return Boolean through Functional Interface which takes Lambda Expression
     */
    public Predicate<CidrModel> cidrIntersects = cidrCompared -> (this.ipStartRange <= cidrCompared.getIpEndRange() &&
            this.ipEndRange >= cidrCompared.getIpStartRange() ||
            cidrCompared.getIpStartRange() <= this.ipEndRange && cidrCompared.getIpEndRange() >= this.ipStartRange);
    /**
     * This method is used to Check if one CIDR Model is Adjacent to Other
     * * @param CidrModel
     * @return Boolean through Functional Interface which takes Lambda Expression
     */
    public Predicate<CidrModel> cidrIsAdjacent = cidrCompared -> (
            this.ipStartRange == cidrCompared.getIpEndRange() + 1 || this.ipEndRange == cidrCompared.getIpStartRange() - 1
    );
    /**
     * This method is used to Check if one CIDR Model is Equal to Other
     * * @param CidrModel
     * @return Boolean through Functional Interface which takes Lambda Expression
     */
    public Predicate<CidrModel> cidrEquals = cidrCompared -> (
            this.ipStartRange == cidrCompared.getIpStartRange() && this.ipEndRange == cidrCompared.getIpEndRange()

    );


}
