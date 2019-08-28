package com.zh.device.model.fatoryModel.abstractFactory.Ak;

import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Bullet;

public class AK_Bullet extends Bullet {
    @Override
    public void load() {
        System.out.println("AK load");
    }
}
