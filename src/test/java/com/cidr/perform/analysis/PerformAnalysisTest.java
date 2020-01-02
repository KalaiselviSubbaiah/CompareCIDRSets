package com.cidr.perform.analysis;

import com.cidr.perform.analysis.constants.CidrTestConstants;
import org.junit.Test;

import java.io.IOException;

/* *
 * Test Class to Run CIDR Analysis Main Class
 * @author  Kalai
 * @version 1.0
 */
public class PerformAnalysisTest {
    /* *
     * Test Method to Run CIDR Analysis
     */
    @Test
    public void PerformAnalysisTest() throws IOException {

        for(String inputFileName : CidrTestConstants.INPUT_FILE_PATH)
            PerformAnalysis.startAnalysis(inputFileName);
    }
}
