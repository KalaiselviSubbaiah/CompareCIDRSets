package com.cidr.perform.analysis;


import com.cidr.perform.analysis.constants.CidrConstants;
import com.cidr.perform.analysis.model.CidrModel;
import com.cidr.perform.analysis.utils.AnalyzeCidr;
import com.cidr.perform.analysis.utils.Cidrutils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;


/* *
 * Main Class of CIDR Analysis
 * @author  Kalai
 * @version 1.0
 */
public class PerformAnalysis {
    public static void main(String[] args) throws IOException {
        startAnalysis();
    }
    /**
     * This method is used to start with Analysis of CIDR Sets
     * @return void
     */
    public static void startAnalysis(String... inputFile) throws IOException {

        //Creating List from the input file
        String filePath = inputFile.length == 0 ? CidrConstants.INPUT_FILE_PATH : inputFile[0];
        ArrayList<LinkedHashSet<CidrModel>> inputData = Cidrutils.mapToCIDR(filePath);
        //Perform Analysis and Print Result in Matrix Format
        AnalyzeCidr.ComputeMatrix(inputData);
    }
}
