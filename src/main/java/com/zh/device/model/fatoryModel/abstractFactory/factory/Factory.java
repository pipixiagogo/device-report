package com.zh.device.model.fatoryModel.abstractFactory.factory;

import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Bullet;
import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Gun;

public interface Factory {
     Gun getGun();

    Bullet getBullet();
}
