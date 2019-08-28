package com.zh.device.model.fatoryModel.abstractFactory.factory;

import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Bullet;
import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Gun;
import com.zh.device.model.fatoryModel.abstractFactory.m4A1.M4A1;
import com.zh.device.model.fatoryModel.abstractFactory.m4A1.M4A1_Bullet;

public class M4A1_Factory implements Factory {
    @Override
    public Gun getGun() {
        return new M4A1();
    }

    @Override
    public Bullet getBullet() {
        return new M4A1_Bullet();
    }
}
