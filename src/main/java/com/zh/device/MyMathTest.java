package com.zh.device;

import java.io.UnsupportedEncodingException;

public class MyMathTest {
    public static void main(String[] args)throws UnsupportedEncodingException {
//        int [] nums={8,4,5,3,9,11};
       // maoPao(nums);
        //selectSort(nums);
//        insertSort(nums);
       // xiErSort(nums);
//        quickSort(nums,0,nums.length-1);
//        System.out.println(Arrays.toString(nums));
    }
    private static void maoPao(int [] nums){
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<nums.length-i;j++){
                if(nums[j] > nums[j+1]){
                    swap(nums,j,j+1);
                }
            }
        }
    }
    private static void quickSort(int [] nums,int low,int high){
        if(low > high){
            return;
        }
        int i=low;
        int j=high;
        int temp=nums[low];
        while (i < j){
            while (nums[j] >= temp && i<j){
                j--;
            }
            while (nums[i] <= temp && i<j){
                i++;
            }
            if(i<j){
                swap(nums,i,j);
            }
        }
        nums[low] = nums[i];
        nums[i]=temp;
        quickSort(nums,low,j-1);
        quickSort(nums,j+1,high);
    }
    

    //对一个数组分成 步长进行排序 都步长排序grap==1时候 就是对整个数组进行排序完成
    private static void xiErSort(int [] nums){
        int length = nums.length;
        int grap= length/2;
        while (grap >= 1){
            for(int i=grap;i<nums.length;i++){
                int temp=nums[i];
                int j;
                for(j=i-grap;j>=0 && nums[j]>temp;j--){
                    nums[j+grap]=nums[j];
                }
                nums[j+grap]=temp;
            }
            grap = grap/2;
        }
    }
    //插入排序 把第一位就是下表为0 的数组第一个当作有序列表的第一个
    private static void insertSort(int [] nums){
        for(int i =1;i<nums.length; i++){
            int index=nums[i];
            int j;
            for (j = i-1;j>=0 && nums[j]>index ;j--){
                nums[j+1]=nums[j];
            }
            nums[j+1]=index;
        }
    }

    //第一位的下标 跟后面的比较 大的进行交换 小于的位置不变
    private static void selectSort(int [] nums){
       for(int i=0;i<nums.length-1;i++){
           int index=i;
           for(int j=i+1;j<nums.length;j++){
               if(nums[index]>nums[j]){
                   index = j;
               }
           }
           if(index != i){
               swap(nums,i,index);
           }
       }
    }

    private static void swap(int [] num,int low ,int high){
        num[low] = num[low] ^ num[high];
        num[high] = num[low] ^ num[high];
        num[low] = num[low] ^ num[high];
    }
}
