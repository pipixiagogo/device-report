package com.zh.device.model.fatoryModel.factoryMethod;

import com.zh.device.model.fatoryModel.simpleFactory.actieve.Rectangle;
import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class RectangleFatory implements Factory {
    @Override
    public Shape getShape() {
        return new Rectangle();
    }
}
