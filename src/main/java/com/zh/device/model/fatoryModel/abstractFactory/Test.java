package com.zh.device.model.fatoryModel.abstractFactory;

import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Bullet;
import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Gun;
import com.zh.device.model.fatoryModel.abstractFactory.factory.AK_Factory;
import com.zh.device.model.fatoryModel.abstractFactory.factory.Factory;

public class Test {
    public static void main(String[] args) {
        Factory factory = new AK_Factory();
        Gun gun = factory.getGun();
        gun.shooting();
        Bullet bullet = factory.getBullet();
        bullet.load();
    }
}
