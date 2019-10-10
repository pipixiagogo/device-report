package com.zh.device.numberOne;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test10 {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("D:\\demo");
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            for(Path path1:paths){
                System.out.println(path1.getFileName());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
