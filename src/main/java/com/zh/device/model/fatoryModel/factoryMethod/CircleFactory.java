package com.zh.device.model.fatoryModel.factoryMethod;

import com.zh.device.model.fatoryModel.simpleFactory.actieve.Circle;
import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class CircleFactory implements Factory {
    @Override
    public Shape getShape() {
        return new Circle();
    }
}
