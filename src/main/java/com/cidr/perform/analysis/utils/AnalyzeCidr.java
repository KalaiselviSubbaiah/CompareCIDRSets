package com.cidr.perform.analysis.utils;

import com.cidr.perform.analysis.constants.CidrConstants;
import com.cidr.perform.analysis.model.CidrModel;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;


/* *
 * Class to Analyze CIDR and Show Comparison between sets that are adjacent,Intersecting,Contains
 * @author  Kalai
 * @version 1.0
 */
public class AnalyzeCidr {
    /**
     * This method is used to obtain  Two Dimensional Array of Comparison between sets
     * that are Adjacent,Intersecting,Contains[Ex : Binary Value 111  , Decimal result of this method 7]
     * * ArrayList<LinkedHashSet<CidrModel>> inputData
     * @return void
     */
    public static void ComputeMatrix(ArrayList<LinkedHashSet<CidrModel>> inputData) {
        int cidrSize = inputData.size();

        System.out.println("CIDR SET SIZE  = " + cidrSize);

        for (int i = 0; i < cidrSize; i++) {

            for (int j = i + 1; j < cidrSize; j++) {
                // Converting HashSet to Array List to Facilitate iteration and push to 2DArray
                ArrayList<CidrModel> baseList = inputData.get(i).stream().collect(Collectors.toCollection(ArrayList::new));
                ArrayList<CidrModel> compareList = inputData.get(j).stream().collect(Collectors.toCollection(ArrayList::new));
                int baseSetSize = baseList.size();
                int compareSetSize = compareList.size();
                int[][] analysisResult = new int[baseList.size()][compareList.size()];
               // int[][] analysisResult = new int[300][300];
                int baseIndex = 0;
                int compareIndex = 0;
                int resultij = 0;
                int resultji = 0;
                System.out.println(" Base Set Of CIDR  = " + baseList);
                System.out.println(" Compare Set Of CIDR  = " + compareList);
                System.out.println(" RESULTANT MATRIX SIZE = " + baseSetSize + " * "+ compareSetSize);
                for (CidrModel baseIp : baseList) {
                    //Getting Index of Object to Map it to respective position in 2D Array
                    baseIndex = baseList.indexOf(baseIp);
                    for (CidrModel compareIp : compareList) {
                        compareIndex = compareList.indexOf(compareIp);

                        // CIDR Adjacent Check

                        if (baseIp.cidrIsAdjacent.test(compareIp)) {
                            //Adding data to first bit for Adjacent check
                            resultij |= resultji |= 1;
                        }

                        //CIDR contains Check
                        if (baseIp.cidrContains.test(compareIp))
                            //Adding data to second bit for Contains check
                            resultij |= 2;
                        if (compareIp.cidrContains.test(baseIp))
                            resultji |= 2;

                        //CIDR intersect Check
                        if (baseIp.cidrIntersects.test(compareIp)) {
                            //Adding data to Third bit for intersect check
                            resultij |= resultji |= 6;
                        }

                        analysisResult[baseIndex][compareIndex] = resultij;
                        if((baseIndex < compareSetSize) && (compareIndex < baseSetSize)){
                            analysisResult[compareIndex][baseIndex] = resultji;
                        }
                    }
                }
                //Method to print results in Matrix format
                buildMatrix(analysisResult);
            }

        }


    }
    /**
     * This method is used to  Print Two Dimensional Array in Matrix Format
     * * @param int[][] analysisResult
     * if the value of 1,2 position is  111 then  first element in set 1
     * is Adjacent,Intersecting,Contains second element in set 2 .
     if the value of 3,2 position is  000 then  third element in set 1
     * is neither Adjacent,Intersecting nor Contains second element in set 2 .
     * @return void
     */
    public static void buildMatrix(int[][] analysisResult) {
        System.out.println(CidrConstants.MATRIX_FORMAT);
        for (int[] rows : analysisResult) {
            System.out.print(CidrConstants.START_BRACKET);
            for (int columns : rows) {
                System.out.print(CidrConstants.EMPTY_SEPARATOR + String.format
                        (CidrConstants.STRING_FORMATTER, Integer.toBinaryString(columns))
                        .replace(CidrConstants.EMPTY_SEPARATOR, CidrConstants.ZERO_REPLACER));
            }
            System.out.println(CidrConstants.END_BRACKET);
        }
    }
}
