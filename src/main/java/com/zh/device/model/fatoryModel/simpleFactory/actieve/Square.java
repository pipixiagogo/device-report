package com.zh.device.model.fatoryModel.simpleFactory.actieve;

import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class Square implements Shape {

    public Square(){
        System.out.println("Square ");
    }
    @Override
    public void show() {
        System.out.println("show Square ");
    }
}
