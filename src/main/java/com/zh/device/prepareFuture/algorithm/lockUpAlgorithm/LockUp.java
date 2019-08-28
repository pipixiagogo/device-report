package com.zh.device.prepareFuture.algorithm.lockUpAlgorithm;

public class LockUp {
    public static void main(String[] args) {
        int [] nums = {1,6,4,5,8};
        int [] nums1 = {1,4,5,6,8};
//        orderSearch(nums,10);
        int i = insertSearch(nums1, 6);
        if(i == -1){
            System.out.println("你要找的数字在该数组中不存在");
        }else {
            System.out.println("你要找的数字在该数组的"+i+"位置");
        }

    }

    //顺序表查找算法
    private static void orderSearch(int [] nums,int searchNum){
        int j=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == searchNum){
                j=i;
            }
        }
        if(j>-1){
            System.out.println("找到了 在数组的第"+j+"位");
        }else {
            System.out.println("在该数组中没找到"+searchNum);
        }
    }
    //二分查找法
    private static int  binarySearch(int [] nums , int searchNum){
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=(high+low)/2;
            if(searchNum<nums[mid]){
                high=mid-1;
            }else if(searchNum>nums[mid]){
                low=mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }
    //插值查找
    private static int insertSearch(int [] nums,int searchNum){
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=low+(searchNum-nums[low])/(nums[high]-nums[low])*(high-low);
            if(searchNum < nums[mid]){
                high = mid-1;
            }else if(searchNum > nums[mid]){
                low = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
