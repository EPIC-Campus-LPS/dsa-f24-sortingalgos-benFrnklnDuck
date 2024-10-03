public class QuadSorts {

    /**
     * uses a boolean to keep track of if the array is sorted
     * uses a while loop that continues while the boolean is false
     * at the top of the loop, set the boolean to true
     * iterate through whole list
     * if not in accending order:
     * use temp variable to switch the two, set boolean to false
     * @param arr the unsorted array
     * @return the array after being sorted
     */
    public static int[] bubbleSort(int[] arr) {
        boolean isSort = false;
        while (!isSort) {
            isSort = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                    isSort = false;
                }
            }
        }
        return arr;
    }

    /**
     * create new, empty array
     * iterate length times
     * set min variable to the largest possible number and temp variable to 0
     * iterate through whole array
     * if a new minimum is found, replace min and store location
     * make the min the next number in the new array
     * set the taken number to the highest possible value
     * @param arr the unsorted array
     * @return the new, sorted array
     */
    public static int[] selectionSort(int[] arr){
        int[] sortList = new int[arr.length];
        for (int i = 0; i < sortList.length; i++) {
            int min = 1003;
            int temp = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    temp = j;
                }
            }
            sortList[i] = min;
            arr[temp] = 1003;
        }
        return sortList;
    }

    /**
     * create a new, empty array
     * create index variable, set to 0
     * iterate length times
     * iterate through whole array except last number
     * if location for next number is found, store index and break
     * this method calls insert
     * @param arr the unsorted array
     * @return the new, sorted array
     */
    public static int[] insertionSort(int[] arr){
        int[] sortList = new int[arr.length];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < sortList.length - 1; j++) {
                if (arr[i] <= sortList[j] || sortList[j] == 0) {
                    index = j;
                    break;
                }
            }
            insert(sortList, index, arr[i]);
        }
        return sortList;
    }

    /**
     * set temp to num and tempUp to the number at the location to be inserted
     * iterate through array starting at the index
     * change the current number to temp
     * change temp to tempUp
     * if there's another number to the right, set tempUp to that
     * @param arr the array that needs to be changed
     * @param dex the location the number should be put
     * @param num the number to be inserted
     */
    public static void insert(int[] arr, int dex, int num) {
        int temp = num;
        int tempUp = arr[dex];
        for (int i = dex; i < arr.length; i++) {
            arr[i] = temp;
            temp = tempUp;
            if (i < arr.length - 1) {
                tempUp = arr[i + 1];
            }
        }
    }

    /**
     * this method calls split
     * @param arr the unsorted array
     * @return the sorted array
     */
    public static int[] mergeSort(int[] arr){
        arr = split(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * base case if the bounds are equal
     * create a new array only containing that number and return it
     * recursive case otherwise
     * calculate the middle of the given split bounds
     * create new array for the left and right sides of the middle
     * call split again for each side to instantiate the arrays
     * this method calls merge
     * @param arr the array to split
     * @param left the left bound of the split
     * @param right the right bound of the split
     * @return an array after being merged back
     */
    public static int[] split(int[] arr, int left, int right) {
        if (left == right) {
            int[] retArr = new int[1];
            retArr[0] = arr[left];
            return retArr;
        } else {
            int mid = ((right - left) / 2) + left;
            int[] leftarr = split (arr, left, mid);
            int[] rightarr = split (arr, mid+1, right);
            return merge(leftarr, rightarr);
        }
    }

    /**
     * create a new array to hold both given
     * create and set equal to 0 a count one, two and main variable
     * iterate through both arrays
     * count one keeps track of the location in the first array
     * count two keeps track of the location in the second array
     * maincount keeps track of the location in the merged array
     * if the end of either array is reached, fill the other in completely
     * if not, put the smallest one in the main array next and increment the location in that array
     * @param arrOne one of the arrays to combine
     * @param arrTwo the other array to combine
     * @return the full array
     */
    public static int[] merge(int[] arrOne, int[] arrTwo) {
        int[] array = new int[arrOne.length + arrTwo.length];
        int countOne = 0;
        int countTwo = 0;
        int mainCount = 0;
        while (countOne < arrOne.length || countTwo < arrTwo.length) {
            if (countOne == arrOne.length) {
                array[mainCount] = arrTwo[countTwo];
                mainCount++;
                countTwo++;
            } else if (countTwo == arrTwo.length) {
                array[mainCount] = arrOne[countOne];
                mainCount++;
                countOne++;
            } else {
                if (arrOne[countOne] > arrTwo[countTwo]) {
                    array[mainCount] = arrTwo[countTwo];
                    mainCount++;
                    countTwo++;
                } else {
                    array[mainCount] = arrOne[countOne];
                    mainCount++;
                    countOne++;
                }
            }
        }
        return array;
    }

    /**
     * this method calls sort
     * @param arr the unsorted array
     * @return the sorted array
     */
    public static int[] quickSort(int[] arr) {
        return sort(arr, 0, arr.length - 1);
    }

    /**
     * base case if the section has been completely sorted
     * return the array
     * recursive case otherwise
     * choose a pivot at the end of the unsorted part
     * create a new, empty array
     * create counts moving in from both sides
     * iterate through the unsorted part
     * if current number is less than pivot, move to left location and increment left count
     * if current number is greater than pivot, move to right location and decrement right count
     * fill in the rest of the already sorted part
     * call again with each half of the unsorted part on either side of the previous pivot
     * return when fully sorted
     * @param arr the full array
     * @param start where the unsorted part begins
     * @param end where the unsorted part ends
     * @return the sorted array
     */
    public static int[] sort(int[] arr, int start, int end) {
        if (start == end || end < start) {
            return arr;
        } else {
            int pivot = arr[end];
            int[] sortList = new int[arr.length];
            int countF = start;
            int countB = end;
            for (int i = start; i <= end; i++) {
                if (arr[i] < pivot) {
                    sortList[countF] = arr[i];
                    countF++;
                } else {
                    sortList[countB] = arr[i];
                    countB--;
                }
            }
            for (int i = 0; i < start; i++) {
                sortList[i] = arr[i];
            }
            for (int i = end + 1; i < sortList.length; i++) {
                sortList[i] = arr[i];
            }
            sortList[countF] = pivot;
            int[] arr3 = sort(sortList, start, countF - 1);
            int[] arr4 = sort(arr3, countF + 1, end);
            return arr4;
        }
    }
}
