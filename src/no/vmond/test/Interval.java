package no.vmond.test;

import java.util.*;

public class Interval {

    public static void main(String[] args) {

        Interval interval = new Interval();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Set of Include Intervals (e.g 10-199, 200-300) :");
            String incIntervals = scanner.nextLine();
            int[][] incPairs = interval.validateAndConvert(incIntervals);


        System.out.println("Enter Set of Exclude Intervals (e.g 20-30) :");
            String excIntervals = scanner.nextLine();
            int[][] excPairs = interval.validateAndConvert(excIntervals);

        interval.displayIntervals(incPairs , excPairs );

    }


    /**
     * Display Interval takes set of include pairs and exclude pairs and provide result
     * considering all includes and “remove” the excludes.
     *
     * @Algorithm
     *
     *  Representation :
     *      Each Pair identify with Start and end element.
     *      Xs - Start Element
     *      Xe - End Elemtn
     *      Exclude Pairs -> Represent AS -> Es / Ee -> (10-100) -> Es:10 , Ee: 100
     *      Include Pairs -> Represent AS -> Is / Ie -> (10-100) -> Is:10 , Ie: 100
     *      Sudo :
     *          if(ES Empty) {}
     *          else {
     *              Es > Is && Ee < Ie : cond1
     *              Es > Is && Ee > Ie : cond2
     *              {
     *                  Ee > Ien (iterate next and compare)
     *              }
     *          }
     *
     * @param incPairs - Contains Set of include pairs 10-100
     * @param excPairs - Contains Set of exclude pairs 20-30
     */
    private void displayIntervals(int[][] incPairs, int[][] excPairs) {

        TreeMap<Integer, Integer> resultSet = new TreeMap<>();

        if (excPairs.length == 0) {  // Only Include Paris are given

            int len = incPairs.length;
            int rs = incPairs[0][0];    // First Element of Array
            int rn = incPairs[len - 1][1]; // Last Element of Array

            resultSet.put(rs, rn) ;

        } else {

            int lastScan = 0; // To maintain last scan position/index

            for (int e = 0; e < excPairs.length; e++) {

                int Es = excPairs[e][0];
                int Ee = excPairs[e][1];

                for (int i = lastScan; i < incPairs.length; i++) {

                    int Is = incPairs[i][0];
                    int Ie = incPairs[i][1];

                    if (Es > Is && Ee < Ie) { // Exclude pair is with boundary of current include pair

                        resultSet.put(Is, Es - 1);
                        resultSet.put(Ee + 1, Ie);

                    } else if (Es > Is && Ee > Ie) {  // Exclude end is bigger than current include end

                        i++; // Iterate to compare with next Include Pair

                        int Isn = incPairs[i][0];
                        int Ien = incPairs[i][1];

                        if (Ee > Isn) {
                            resultSet.put(Is, Es - 1);
                            resultSet.put(Ee + 1, Ien);
                        }
                    }

                    lastScan = i;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i=0;
        for (Integer start : resultSet.keySet()) {
            if(i>0){
                sb.append(",");
            }
            sb.append(start).append("-").append(resultSet.get(start));
            i++ ;
        }
        System.out.println("Result : \n " + sb.toString());
    }

    /**
     * Validate input data and convert them to Array for final calculation
     * @param incIntervals
     * @return
     */
    private int[][] validateAndConvert(String incIntervals) {
        TreeMap<Integer, Integer> incIntervalSet = sortPairsBasedOnFirstElement(incIntervals);
        return convertMapToArray(incIntervalSet);
    }


    /**
     *  Function converts the sorted Map in to two dimensional array, where each row contains two column.
     *  column[0] - represents key
     *  column[1] - represents value
     *
     *  For efficient comparing in current algorithm, we need to keep track of last traverse point/index.
     *  To achieve that array is best option.
     *
     * @param sortedMap - Pairs provided as program argument sort by first value and store in map
     * @return List of Paris in
     */
    private int[][] convertMapToArray(TreeMap<Integer, Integer> sortedMap){

        int[][] pairs = new int[sortedMap.size()][2];

        int ind = 0;
        for (Integer integer : sortedMap.keySet()) {
            pairs[ind][0] = integer;
            pairs[ind][1] = sortedMap.get(integer);
            ind++;
        }
        return pairs ;
    }

    private TreeMap<Integer, Integer> sortPairsBasedOnFirstElement(String intervals) {

        TreeMap<Integer, Integer> integerTreeMap = new TreeMap<>();

        try {

            String[] split = intervals.split(",");

            for (String interval : split) {

                String[] nums = interval.split("-");

                // each pair store as key and value for sorting by Key, 10-100 will be store as map.put(10, 100)
                integerTreeMap.put(Integer.valueOf(nums[0].trim()), Integer.valueOf(nums[1].trim()));
            }

        } catch (Exception e) {
            System.out.print("Invalid Inputs, please add in given format e.g 10-100, 200-300") ;
            System.exit(0);
        }
        return integerTreeMap;
    }


}
