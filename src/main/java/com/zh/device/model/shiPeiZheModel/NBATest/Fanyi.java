package com.zh.device.model.shiPeiZheModel.NBATest;

public class Fanyi extends Player {

    private ZhongFeng zhongFeng = new ZhongFeng();
    @Override
    public void attack() {
        zhongFeng.jinGong();
    }

    @Override
    public void defense() {
        zhongFeng.fangShou();
    }
}
