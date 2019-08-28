package com.zh.device.Entity;

public class CollectionObject {

    public static final int COLLECTION_OBJ=8;
    public static final int COLLECTION_TIME=0;
    private long canId;
    private int collectionTime;

    public long getCanId() {
        return canId;
    }

    public void setCanId(long canId) {
        this.canId = canId;
    }

    public int getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(int collectionTime) {
        this.collectionTime = collectionTime;
    }
}
