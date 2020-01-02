# CompareCIDRSets
CompareCIDRSets

1)Code will Take N Set of CIDR Blocks.
2)Sample Input Format [154.63.79.163/12,47.255.12.244/25]
2)At a time it will compare each element Set 1 with every other element in Set 2 and Print a result in Matrix for (111 - ADJACENT :CONTAINS: INTERSECTS ).
3)Each Matrix Position (ex:1,2)will have 3 bits in which each digit represents different comparison.
4)Sample Output Format.
CIDR SET SIZE  = 2
 Base Set Of CIDR  = [44.203.228.168/13, 40.204.61.126/32, 37.245.145.192/31]
 Compare Set Of CIDR  = [188.176.87.26/5, 163.179.65.235/23, 61.152.7.50/30]
 RESULTANT MATRIX SIZE = 3 * 3
 000/111 : ADJACENT :CONTAINS: INTERSECTS 
[ 000 000 000 ]
[ 000 000 000 ]
[ 000 000 000 ]
