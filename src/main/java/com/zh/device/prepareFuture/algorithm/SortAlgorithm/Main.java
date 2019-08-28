package com.zh.device.prepareFuture.algorithm.SortAlgorithm;

import java.util.Arrays;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        //快速排序 三分取基
        Random ran = new Random();
        int[] array = new int[100];
        int[] nums = {4, 8, 7, 5, 4, 2, 1};
        for (int i = 0; i < 100; i++) {
            array[i] = ran.nextInt(100) + 1;
        }
        midThree(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private static void quicksort(int[] array) {
        quick(array, 0, array.length - 1);
    }

    private static void quick(int[] array, int low, int high) {
        midThree(array, low, high);
        ////将最大值放到最后面  最小值放到最中间 还剩下一个值放在最前面
        int par = partion(array, low, high);
        if (par > low + 1) {
            quick(array, low, par - 1);
        }
        if (par < high - 1) {
            quick(array, par + 1, high);
        }
    }

    public static int partion(int[] array, int low, int high) {
        int tmp = array[low];

        while (low < high) {
            while (low < high && tmp < array[high]) {
                high--;
            }
            if (low < high) {
                array[low] = array[high];
                low++;
            } else break;

            while (low < high && tmp > array[low]) {
                low++;
            }
            if (low < high) {
                array[high] = array[low];
                high--;
            } else break;
        }
        array[low] = tmp;
        return low;
    }

    private static void midThree(int[] array, int low, int high) {
        int mid = (low + high) / 2;
            if(array[low]>array[high]){
                swap(array, low, high);
            }
            if(array[mid]>array[high]){
                swap(array, mid,high );
            }
            if(array[low] < array[mid]){
                swap(array,low,mid);
            }
//
//        if (array[low] > array[high]) {
//            swap(array, low, high);
//        }
//        if (array[low] < array[mid]) {
//            swap(array, low, mid);
//        }
//        if (array[mid] > array[high]) {
//            swap(array, mid, high);
//        }
    }

    public static void swap(int[] array, int low, int high) {
        int tmp = array[low];
        array[low] = array[high];
        array[high] = tmp;
    }
}
