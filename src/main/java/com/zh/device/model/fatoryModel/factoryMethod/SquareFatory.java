package com.zh.device.model.fatoryModel.factoryMethod;

import com.zh.device.model.fatoryModel.simpleFactory.actieve.Square;
import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class SquareFatory implements Factory {
    @Override
    public Shape getShape() {
        return new Square();
    }
}
