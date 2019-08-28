package com.zh.device.model.fatoryModel.simpleFactory;

import com.zh.device.model.fatoryModel.simpleFactory.actieve.Circle;
import com.zh.device.model.fatoryModel.simpleFactory.actieve.Rectangle;
import com.zh.device.model.fatoryModel.simpleFactory.actieve.Square;
import com.zh.device.model.fatoryModel.simpleFactory.interfaces.Shape;

public class Test {
    public static void main(String[] args) {

//        Shape circle = ShapeFactory.getShape("Circle");
//        circle.show();
//
//        Shape rectangle = ShapeFactory.getShape("Rectangle");
//        rectangle.show();
//
//        Shape square = ShapeFactory.getShape("Square");
//        square.show();

        //--------------------------------------------
        Shape circle = (Shape) ShapeFactory2.getShape(Circle.class);
        circle.show();

        Rectangle rectangle = (Rectangle)ShapeFactory2.getShape(Rectangle.class);
        rectangle.show();

        Square shape = (Square)ShapeFactory2.getShape(Square.class);
        shape.show();
    }
}
