/**
 *  File: Sorts.java
 *  Professor: Professor Kapleau
 *  Course: CS114
 *  Section: H02
 *  
 *  The code below contains several sorting algorithms including my implementation of heapsort. Comments are below.
 * 
 *  kFLFBx9T7XJmrr3p2F/fmAj+8DS8GqnKeq/TOIAPcuI=
 */

//import required files
import java.util.Arrays;

public class Sorts{
    //heapSort() - a sorting algorithm that creates, breaks, and mends max-heaps until the array is sorted
    public static <T extends Comparable<? super T>> void heapSort(T[] array){
        maxHeapify(array, array.length);
        for(int size = array.length; size > 0; reheapify(array, 0, size)){
            //Swaps root with last available index, effectively breaking the max-heap
            T temp = array[0];
            array[0] = array[size >0? size -1 : 0];
            array[size> 0? size---1: 0] = temp;
        }
    }

    //maxHeapify() - creates the first max-heap by making max-heaps out of every node from leaf to root
    private static <T extends Comparable<? super T>> void maxHeapify(T[] array, int size){
        for(int parentIndex = (size - 2)/2; parentIndex >=0; --parentIndex){
            int biggest = (parentIndex*2 +1 < size)? (parentIndex*2 + 2 < size && array[parentIndex*2 + 1].compareTo(array[parentIndex*2+2]) < 0? parentIndex*2 + 2 : parentIndex*2 + 1) : -1;
            if(biggest != -1 && array[biggest].compareTo(array[parentIndex]) > 0){
                T temp = array[biggest];
                array[biggest] = array[parentIndex];
                array[parentIndex] = temp;
                reheapify(array, biggest, size);
            }
        }
    }

    //reheapify() - mends the max-heap by recursively making semi-heaps from the root down until where children nodes have the semi-heap preserved and thus the code no longer recurs
    private static <T extends Comparable<? super T>> void reheapify(T[] array, int parentIndex, int size){
        if(parentIndex < size){
            int biggest = (parentIndex*2 +1 < size)? (parentIndex*2 + 2 < size && array[parentIndex*2 + 1].compareTo(array[parentIndex*2+2]) < 0? parentIndex*2 + 2 : parentIndex*2 + 1) : -1;

            //Continue recursion if any children are larger 
            if(biggest != -1 && array[biggest].compareTo(array[parentIndex]) > 0){
                T temp = array[biggest];
                array[biggest] = array[parentIndex];
                array[parentIndex] = temp;
                reheapify(array, biggest, size);
            }
        }
    }

    /**** The methods below heapSort were already given and thus not my responsibility to determine if they function properly  ****/

    public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {

        T temp;
        boolean sorted;

        for (int i = array.length - 1; i > 0; --i) {

            sorted = true;

            for (int j = 0; j < i; ++j) {

                if (array[j].compareTo(array[j + 1]) > 0) {

                    sorted = false;

                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

            if (sorted) {
                break;
            }
        }
    }

    public static <T extends Comparable<? super T>> void insertSort(T[] array) {

        T temp;
        int j;

        for (int i = 1; i < array.length; ++i) {

            temp = array[i];

            for (j = i; j > 0; --j) {

                if (array[j - 1].compareTo(temp) > 0) {
                    array[j] = array[j - 1];
                }
                else {
                    break;
                }
            }

            if (j < i) {
                array[j] = temp;
            }
        }
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] array) {

        if (array.length > 1) {

            T[] left  = Arrays.copyOfRange(array, 0, array.length / 2);
            T[] right = Arrays.copyOfRange(array, array.length / 2, array.length);

            mergeSort(left);
            mergeSort(right);

            int i, l = 0, r = 0;

            for (i = 0; i < array.length && l < left.length && r < right.length; ++i) {
                if (left[l].compareTo(right[r]) <= 0) {
                    array[i] = left[l++];
                }
                else {
                    array[i] = right[r++];
                }
            }

            if (i < array.length) {
                if (l < left.length) {
                    while (i < array.length) {
                        array[i++] = left[l++];
                    }
                }
                else {
                    while (i < array.length) {
                        array[i++] = right[r++];
                    }
                }
            }
        }
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] array) {

        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] array, int left, int right) {

        int pivot = right;

        int l = left, r = right;

        if (left < right) {
            while (l < r) {

                while (l < r && array[l].compareTo(array[pivot]) <= 0) {
                    ++l;
                }

                while (l < r && array[pivot].compareTo(array[r]) <= 0) {
                    --r;
                }

                if (l < r) {
                    T temp = array[l];
                    array[l] = array[r];
                    array[r] = temp;
                }
            }

            if (r != pivot) {
                T temp = array[pivot];
                array[pivot] = array[r];
                array[r] = temp;
            }

            quickSort(array, left, r - 1);
            quickSort(array, r + 1, right);
        }
    }

    public static <T extends Comparable<? super T>> void selectSort(T[] array) {

        T temp;
        int mini;

        for (int i = 0; i < array.length - 1; ++i) {

            mini = i;

            for (int j = i + 1; j < array.length; ++j) {

                if (array[j].compareTo(array[mini]) < 0) {
                    mini = j;
                }
            }

            if (i != mini) {

                temp = array[i];
                array[i] = array[mini];
                array[mini] = temp;
            }
        }
    }
}
