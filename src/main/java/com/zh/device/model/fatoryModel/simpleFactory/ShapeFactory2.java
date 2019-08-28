package com.zh.device.model.fatoryModel.simpleFactory;

import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class ShapeFactory2 {

    public static Object getShape(Class< ? extends Shape> clazz){
        Object shape =null;
        try {
            shape = Class.forName(clazz.getName()).newInstance();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return shape;
    }
}
