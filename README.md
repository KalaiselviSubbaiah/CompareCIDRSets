# CompareCIDRSets
CompareCIDRSets

1)Code will Take N Set of CIDR Blocks.<br/>
2)Sample Input Format [154.63.79.163/12,47.255.12.244/25]<br/>
3)At a time it will compare each element Set 1 with every other element in Set 2 and Print a result in Matrix for (111 - ADJACENT :CONTAINS: INTERSECTS ).<br/>
4)Each Matrix Position (ex:1,2)will have 3 bits in which each digit represents different comparison.<br/>
5)Sample Output Format.<br/>
CIDR SET SIZE  = 2 <br/>
 Base Set Of CIDR  = [44.203.228.168/13, 40.204.61.126/32, 37.245.145.192/31] <br/>
 Compare Set Of CIDR  = [188.176.87.26/5, 163.179.65.235/23, 61.152.7.50/30] <br/>
 RESULTANT MATRIX SIZE = 3 * 3 <br/>
 000/111 : ADJACENT :CONTAINS: INTERSECTS  <br/>
[ 000 000 000 ] <br/>
[ 000 000 000 ] <br/>
[ 000 000 000 ] <br/>
6)Test data is available as text file in following path src/main/resources/

//Forking to Local Repository and Execution: <br/>

1)Download as Gradle Project. <br/>
2)Run PerformAnalysis class Method in com.cidr.perform.analysis package that will out put results for a sample set of     
  data.<br/>     
3)PerformAnalysisTest is a test method in PerformAnalysisTest Class that will run test againt few set of input files. <br/>
4) Java open Source JDK 11 is used. <br/>

//Data Representation <br/>
1)Range Bar Chart(depicts two values on the X axis at a time — Low and High.) can be used to Map Ip Range of a CIDR in X axis and Y axis Can have CIDR So that adjacent,Contains ,Intersection Can be see, which gives better performance when data is limited,for more or less data the comparison may not be clear.
