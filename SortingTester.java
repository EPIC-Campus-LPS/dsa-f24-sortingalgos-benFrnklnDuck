//Be sure to add your documentation here.

public class SortingTester {

    public static void main(String[] args) {
        int[] numElements = {10, 100, 1000, 10000, 50000, 100000};

        for(int index = 0; index < numElements.length; index++){
            int NUM_ELEMENTS = numElements[index];

            int[][] testCases = new int[5][NUM_ELEMENTS]; //0 - bubble, 1 - selection, 2 - insertion, 3 - merge

            //Build a random array
            int[] originalList = new int[NUM_ELEMENTS];
            for(int i = 0; i < NUM_ELEMENTS; i++){
                originalList[i] = (int)(Math.random() * 1000) + 1;
            }

            //set each test case to deep copy of originalList
            for(int j = 0; j < testCases.length; j++){
                testCases[j] = copyArray(originalList);
            }

            //Sort them using each of the sorting methods
            long time = System.currentTimeMillis();
            testCases[0] = QuadSorts.bubbleSort(testCases[0]);
            long newTime = System.currentTimeMillis();
            System.out.println("Bubble sort #" + (index + 1) + " took " + (newTime - time) + " milliseconds");

            time = System.currentTimeMillis();
            testCases[1] = QuadSorts.selectionSort(testCases[1]);
            newTime = System.currentTimeMillis();
            System.out.println("Selection sort #" + (index + 1) + " took " + (newTime - time) + " milliseconds");

            time = System.currentTimeMillis();
            testCases[2] = QuadSorts.insertionSort(testCases[2]);
            newTime = System.currentTimeMillis();
            System.out.println("Insertion sort #" + (index + 1) + " took " + (newTime - time) + " milliseconds");

            time = System.currentTimeMillis();
            testCases[3] = QuadSorts.mergeSort(testCases[3]);
            newTime = System.currentTimeMillis();
            System.out.println("Merge sort #" + (index + 1) + " took " + (newTime - time) + " milliseconds");

            time = System.currentTimeMillis();
            testCases[4] = QuadSorts.quickSort(testCases[4]);
            newTime = System.currentTimeMillis();
            System.out.println("Quick sort #" + (index + 1) + " took " + (newTime - time) + " milliseconds");

            //Check if it worked
            if(inOrder(testCases[0]) && sameNumbers(originalList, testCases[0])){
                System.out.println("Passed Bubble #" + (index + 1));
            }

            if(inOrder(testCases[1]) && sameNumbers(originalList, testCases[1])){
                System.out.println("Passed Selection #" + (index + 1));
            }

            if(inOrder(testCases[2]) && sameNumbers(originalList, testCases[2])){
                System.out.println("Passed Insertion #" + (index + 1));
            }

            if(inOrder(testCases[3]) && sameNumbers(originalList, testCases[3])){
                System.out.println("Passed Merge #" + (index + 1));
            }

            if(inOrder(testCases[4]) && sameNumbers(originalList, testCases[3])) {
                System.out.println("Passed Quick #" + (index + 1));
            }

        }

    }



    /**
     * This function will return a deep copy of the given array
     */
    public static int[] copyArray(int[] arr){
        int[] copy = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            copy[i] = arr[i];
        return copy;
    }


    /**
     * This function will determine whether the two passed arrays
     * (one sorted & one not) have the same number of each number.
     */
    public static boolean sameNumbers(int[] arr1, int[] arr2){
        int[] fTable1 = frequencyTable(arr1);
        int[] fTable2 = frequencyTable(arr2);

        if(fTable1.length != fTable2.length)
            return false;

        for(int i = 0; i < fTable1.length; i++){
            if(fTable1[i] != fTable2[i])
                return false;
        }
        return true;
    }

    public static int[] frequencyTable(int[] arr){
        //find min & max
        int min = arr[0];
        int max = arr[0];

        for(int num : arr){
            if(min > num)
                min = num;
            if(max < num)
                max = num;
        }

        //tally numbers in array
        int[] fTable = new int[max - min + 1];
        for(int num : arr){
            fTable[num - min]++;
        }

        return fTable;
    }

    /**
     * This function will return whether the given array
     * is in non-decreasing order.
     */
    public static boolean inOrder(int[] arr){
        for(int i = 0; i < arr.length - 1; i++)
            if(arr[i] > arr[i + 1])
                return false;
        return true;
    }

}