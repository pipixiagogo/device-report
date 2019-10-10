package com.zh.device.prepareFuture.algorithm.SortAlgorithm;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = {12, 20, 5, 16, 15, 1, 30,45};
        quikySort(nums, 0, nums.length - 1);
        for (int i : nums) {
            System.out.print(i + "\t");
        }
    }

    //冒泡排序
    //两两比较相邻记录的关键字 如果反序则交换 直到没有反序为止
    //时间复杂度o(n的平方)
    //冒泡排序初级版本
    //1.最简单的排序 只是两两交换位置 遍历数组 找到比它小的数字 交换位置
    private static void easySort(int[] nums) {
        //控制比较的次数
        for (int i = 1; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    //交换 异或
                    nums[j] = nums[j] ^ nums[i];
                    nums[i] = nums[j] ^ nums[i];
                    nums[j] = nums[j] ^ nums[i];
                }
            }
        }
    }

    //2.正宗的冒泡排序
    private static void easyMaoPao(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            //前面的已经排好序了 只要比较（length-i）次就行
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j] > nums[j + 1]) {//如果前者大于后者
                    nums[j] = nums[j] ^ nums[j + 1];
                    nums[j + 1] = nums[j] ^ nums[j + 1];
                    nums[j] = nums[j] ^ nums[j + 1];
                }
            }
        }
    }

    //3.添加个标识flag 高效排序
    private static void flagMaoPao(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length - i; j++) {
                //当没有数据交换的时候 说明序列已经有序了
                if (nums[j] > nums[j + 1]) {//如果前者大于后者
                    nums[j] = nums[j] ^ nums[j + 1];
                    nums[j + 1] = nums[j] ^ nums[j + 1];
                    nums[j] = nums[j] ^ nums[j + 1];
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    //选择排序
    //每一次从待排序的数据元素中选出最小（或最大）的一个元素下表 并交换，
    // 存放在序列的起始位置，直到全部待排序的数据元素排完
    private static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < nums.length; j++) {
                //如果大于 就交换数组下标
                if (nums[index] > nums[j]) {
                    index = j;
                }
            }
            //数组下标不相同 就交换值
            if (index != i) {
                nums[index] = nums[index] ^ nums[i];
                nums[i] = nums[index] ^ nums[i];
                nums[index] = nums[index] ^ nums[i];
            }
        }
    }

    //直接插入排序
    //把待排序的记录按其关键码值的大小逐个插入到一个已经排好序的有序序列中，
    // 直到所有的记录插入完为止，得到一个新的有序序列
    private static void myInsertSort(int[] nums) {
        int i, j;
        for (i = 1; i < nums.length; i++) {
            int temp = nums[i];//需要插入的值
            for (j = i - 1; j >= 0 && nums[j] > temp; j--) {
                nums[j + 1] = nums[j];//当大于temp往后移一位
            }
            //插入temp  //因为第一次出来j==-1了 所以j+1 将3赋值给第一位 下标为0
            nums[j + 1] = temp;
        }
    }

    //希尔排序
    //记录按下标的一定增量分组(步长)，对每组使用直接插入排序算法排序；
    // 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
    public static void xiErsort(int[] a) {
        int n = a.length;
        int gap = n / 2;
        while (gap >= 1) {
            for (int i = gap; i < a.length; i++) {
                int j = 0;
                int temp = a[i];
                //如果大于就交换
                for (j = i - gap; j >= 0 && a[j] > temp; j = j - gap) {
                    a[j + gap] = a[j];
                }
                //不大于就保持原来的位置
                a[j + gap] = temp;
            }
            gap = gap / 2;
        }
    }

    //快速排序
    //每次排序时候设置一个基准点 (基准点影响快排的效率)
    //将小于基准点的放在基准点 左边
    //将大于基准点的放在基准点 右边
    //总的交换次数比冒泡排序交换少了 交互距离扩大了
    //根据左边基准排序
    private static void quikySort(int[] nums, int low, int high) {
        int i, j, temp;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        temp = nums[low];//基准
        while (i < j) {
            //先看右边  右边递减 找到比基准小 或者等于的数
            while (temp <= nums[j] && i < j) {
                j--;
            }
            //在看左边 左边递增  找到比基准大 或者等于的数
            while (temp >= nums[i] && i < j) {
                i++;
            }
            //如果满足条件交换  交换两数的位置
            if (i < j) {
                nums[j] = nums[j] ^ nums[i];
                nums[i] = nums[j] ^ nums[i];
                nums[j] = nums[j] ^ nums[i];
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        nums[low] = nums[i];
        nums[i] = temp;
        //递归调用左半边数组
        quikySort(nums, low, j - 1);
        //递归调用右半边数组
        quikySort(nums, j + 1, high);
    }

    //根据右边排序
    private static void quikySort1(int[] nums, int low, int high) {
        int i, j, temp;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        temp = nums[high];//基准
        while (i < j) {
            //在看左边 左边递增  找到比基准小 或者等于的数
            while (temp >= nums[i] && i < j) {
                i++;
            }
            //在看右边  右边递减 找到比基准大 或者等于的数
            while (temp <= nums[j] && i < j) {
                j--;
            }
            //如果满足条件交换  交换两数的位置
            if (i < j) {
                nums[j] = nums[j] ^ nums[i];
                nums[i] = nums[j] ^ nums[i];
                nums[j] = nums[j] ^ nums[i];
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        nums[high] = nums[j];
        nums[j] = temp;
        //递归调用左半边数组
        quikySort1(nums, low, i - 1);
        //递归调用右半边数组
        quikySort1(nums, i + 1, high);
    }

    private static void quickyMore(int[] nums, int low, int high) {
        if (low > high) {
            return;
        }
        Arrays.copyOf(nums, high - low);
        midThree(nums, low, high);

        int i = low;
        int j = high;
        int temp = nums[low];
        while (i < j) {
            while (temp <= nums[j] && i < j) {
                j--;
            }
            while (temp >= nums[i] && i < j) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        nums[low] = nums[i];
        nums[i] = temp;
        quickyMore(nums, low, j - 1);
        quickyMore(nums, j + 1, high);
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
    }

    public static void swap(int[] array, int low, int high) {
        array[low] = array[low] ^ array[high];
        array[high] = array[low] ^ array[high];
        array[low] = array[low] ^ array[high];
    }

    //堆排序  不搞了 太难了

}
