package com.zh.device.model.fatoryModel.abstractFactory.factory;

import com.zh.device.model.fatoryModel.abstractFactory.Ak.AK;
import com.zh.device.model.fatoryModel.abstractFactory.Ak.AK_Bullet;
import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Bullet;
import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Gun;

public class AK_Factory implements Factory {
    @Override
    public Gun getGun() {
        return new AK();
    }

    @Override
    public Bullet getBullet() {
        return new AK_Bullet();
    }
}
