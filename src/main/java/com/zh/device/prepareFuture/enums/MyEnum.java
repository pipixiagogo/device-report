package com.zh.device.prepareFuture.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum  MyEnum {

    ONE(1,0,6,2,3,4,5),
    TWO(2,1,2,3,4,5,6),
    THREE(3,2,3,4,5,6,7),
    FOUR(4,3,4,5,6,7,8),
    FIVE(5,4,5,6,7,8,9),
    SIX(6,7,7,7,7,7);
    private static Map<Integer,MyEnum> map;
    private int status;
    private List<Integer> nums;
    MyEnum(int status,Integer...nums){
        this.status=status;
        this.nums= Arrays.asList(nums);
        init();
    }

    private void init() {
        if(map == null){
            map=new HashMap<>();
        }
        map.put(status,this);
    }
    public  boolean match(Integer val){
        if(val != null){
            return this.nums.contains(val);
        }
        return false;
    }
    public static MyEnum getVal(Integer status){
        MyEnum myEnum = map.get(status);
        if(myEnum != null){
            return myEnum;
        }
        return null;
    }

    public List<Integer> getNums() {
        return nums;
    }

}
