package com.zh.device;

import java.util.Arrays;

public class MyMathTest2 {
    public static void main(String[] args) {
        int[] array = {8, 4, 5, 3, 9, 11};
        quickSort2(array,0,array.length-1);
//        xiErSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void maoPao(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    private static void select(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            if (index != i) {
                swap(array, i, index);
            }
        }
    }

    private static void xiErSort(int [] array){
        int lenth=array.length;
        int grap =lenth / 2;
        while (grap>=1){
            for(int i=grap;i<array.length;i++){
                int temp = array[i];
                int j;
                for(j=i-grap;j>=0 && array[j]> temp;j--){
                    array[j+grap]=array[j];
                }
                array[j+grap] = temp;
            }
            grap = grap/2;
        }
    }

    private static void quickSort2(int [] array,int low ,int high){
        if(low > high){
            return;
        }
        int i=low;
        int j=high;
        int temp = array[low];
        while (i<j){
            while (i<j && array[j]>=temp ){
                j--;
            }
            while (i<j && array[i]<=temp){
                i++;
            }
            if(i<j){
                swap(array,i,j);
            }
        }
        //交换位置
        //  int temp = array[low];
        array[low] = array[i];
        array[i]=temp;
        quickSort2(array,low,j-1);
        quickSort2(array,j+1,high);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int temp = array[low];//选取基点
        while (i < j) {
            while (temp <= array[j] && i < j) {
                j--;
            }
            while (temp >= array[i] && i < j) {
                i++;
            }
            if (i < j) {
                swap(array, i, j);
            }
        }
        //确认新的基点
        array[low] = array[i];
        array[i] = temp;
        quickSort(array,low,j-1);
        quickSort(array,j+1,high);
    }

    private static void insert(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i]; //4
            int j;
            for (j = i - 1; j >= 0 && array[j] > temp; j--) {
                // 8
                array[j + 1] = array[j]; //交换位置
            }
            array[j + 1] = temp;
        }
    }

    private static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }


}
