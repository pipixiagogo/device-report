package com.zh.device.model.fatoryModel.simpleFactory;

import com.zh.device.model.fatoryModel.simpleFactory.actieve.Circle;
import com.zh.device.model.fatoryModel.simpleFactory.actieve.Rectangle;
import com.zh.device.model.fatoryModel.simpleFactory.actieve.Square;
import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class ShapeFactory {
    public static Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("Circle")){
            return new Circle();
        }else if(shapeType.equalsIgnoreCase("Rectangle")){
            return new Rectangle();
        }else if(shapeType.equalsIgnoreCase("Square")){
            return new Square();
        }else {
            return null;
        }
    }
}
