package com.zh.device.numberOne;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Test11 {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("D:\\demo");
            List<Path> list = new ArrayList<>();
            Files.walkFileTree(path,new Visitor(list));
            System.out.println(list.size());
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}

class Visitor extends SimpleFileVisitor<Path>{
    private List<Path> list;
    public Visitor(List<Path>list){
        this.list=list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(file.toString().endsWith(".zip")){
            list.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
