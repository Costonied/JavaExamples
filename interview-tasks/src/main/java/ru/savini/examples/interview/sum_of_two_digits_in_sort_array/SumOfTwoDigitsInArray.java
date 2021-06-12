package ru.savini.examples.interview.sum_of_two_digits_in_sort_array;

public class SumOfTwoDigitsInArray {
    private static int[] firstExample = {-10, -5, 0, 1, 7, 15};
    private static int[] secondExample = {-10, -8, -7, -5, 11};

    public static void main(String[] args) {
        slowAlgorithm(firstExample, 0);
        slowAlgorithm(secondExample, 0);

        fastAlgorithm(firstExample, 0);
        fastAlgorithm(secondExample, 0);
    }

    public static void fastAlgorithm(int[] source, int target) {
        int indexA = -1;
        int indexB = -1;
        int minDiff = Integer.MAX_VALUE;
        int currentDiff;
        int currentIndexB;
        for (int i = 0; i < source.length - 1; i++) {
            currentIndexB = getIndexWithBestDiff(source, target, source[i], i + 1, source.length-1);
            currentDiff = calculateDiff(target, source[i], source[currentIndexB]);
            if (currentDiff == 0) {
                indexA = i;
                indexB = currentIndexB;
                break;
            } else if (currentDiff < minDiff) {
                minDiff = currentDiff;
                indexA = i;
                indexB = currentIndexB;
            }
        }
        System.out.printf("{%s, %s}\n", indexA, indexB);
    }

    /**
     * Using binary search algorithm while searching.
     * O(log n)
     */
    private static int getIndexWithBestDiff(int[] sortedArray, int target, int firstDigit, int low, int high) {
        int currentDiff;
        int indexWithMinDiff = sortedArray.length - 1;
        int minDiff = calculateDiff(target, firstDigit, sortedArray[sortedArray.length-1]);

        while (low <= high) {
            int mid = (low + high) / 2;
            currentDiff = calculateDiff(target, firstDigit, sortedArray[mid]);
            if (currentDiff < minDiff) {
                minDiff = currentDiff;
                indexWithMinDiff = mid;
                high = mid - 1;
            } else if (currentDiff > minDiff) {
                low = mid + 1;
            } else {
                indexWithMinDiff = mid;
                break;
            }
        }
        return indexWithMinDiff;
    }

    private static int calculateDiff(int target, int firstDigit, int secondDigit) {
        return Math.abs(firstDigit + secondDigit - target);
    }

    /**
     * Самый простой и долгий вариант решения,
     * который <b>не проходит по условию</b><br>
     * "Сложность алгоритма - не сложнее O(n)"<br>
     * т.к. сложность данного решения O(n*n) - из-за двойного перебора массива
     */
    public static void slowAlgorithm(int[] source, int target) {
        int minDiff = Integer.MAX_VALUE;
        int indexI = -1;
        int indexJ = -1;
        int currentSum;
        int currentDiff;

        for(int i = 0 ; i < source.length ; i ++) {
            for(int j = 0 ; j < source.length ; j++) {
                if(i == j) {
                    continue;
                }
                currentSum = source[i] + source[j];
                currentDiff = Math.abs(target - currentSum);
                if(currentDiff < minDiff) {
                    minDiff = currentDiff;
                    indexI = i;
                    indexJ = j;
                }
            }
        }

        printArray(source);
        System.out.println("]\nIndexes: " + indexI + indexJ);
    }

    private static void printArray(int[] arr) {
        System.out.print("---\nInput array is: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
