package com.zh.device.model.fatoryModel.abstractFactory.Ak;

import com.zh.device.model.fatoryModel.abstractFactory.abstractPac.Gun;

public class AK extends Gun {
    @Override
    public void shooting() {
        System.out.println("AK shooting");
    }
}
