package com.zh.device.model.fatoryModel.factoryMethod;

import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class Test {
    public static void main(String[] args) {
        Factory factory = new CircleFactory();
        Shape shape = factory.getShape();
        shape.show();
    }
}
