package com.zh.device.model.shiPeiZheModel;

//充当转接头的作用  不改变类的情况下 使用到原本的方法作用
public class AdapetChange extends DoubleSocket implements SocketCreate {

    @Override
    public void insert() {
        System.out.println("转接头");
        super.insert();
    }
}
