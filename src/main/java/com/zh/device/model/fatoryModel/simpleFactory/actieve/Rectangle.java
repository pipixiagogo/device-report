package com.zh.device.model.fatoryModel.simpleFactory.actieve;

import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class Rectangle implements Shape {

    public Rectangle(){
        System.out.println("Rectangle ");
    }
    @Override
    public void show() {
        System.out.println("show Rectangle ");
    }
}
