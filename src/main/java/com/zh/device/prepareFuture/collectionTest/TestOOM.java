package com.zh.device.prepareFuture.collectionTest;

import java.util.ArrayList;
import java.util.List;

public class TestOOM {
    public static void main(String[] args) {
        List<Person> list  = new ArrayList<>();
        while (true){
            list.add(new Person());
        }

    }
}
class Person{

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
