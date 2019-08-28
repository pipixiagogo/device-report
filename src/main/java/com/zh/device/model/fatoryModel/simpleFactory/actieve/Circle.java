package com.zh.device.model.fatoryModel.simpleFactory.actieve;

import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class Circle implements Shape {

    public Circle(){
        System.out.println("Circle");
    }
    @Override
    public void show() {
        System.out.println("show Circle");
    }
}
