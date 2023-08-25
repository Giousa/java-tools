package com.giousa.tools.file;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileScanDemo {

    public static void main(String[] args) {
        //IDEA 输入框，默认查询IDEA文件目录传入path，手动选择目录进行生成

        // 路径
        String path = "/Users/zhangmengmeng/workspace/github/java-tools/src/main/java/com/giousa/dubbo";
        try (Stream<Path> paths = Files.walk(Paths.get(path))){
            List<Path> fileNames = paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            System.out.println("fileNames total >>> "+fileNames.size());
            System.out.println(JSON.toJSONString(fileNames));
        } catch (IOException e) {
            e.printStackTrace();
        }

        readFile("/Users/zhangmengmeng/workspace/github/java-tools/src/main/java/com/giousa/dubbo/framework/protocol/http/HttpServerHandler.java");
    }


    private static void readFile(String fileName){
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            String collect = lines.stream().collect(Collectors.joining("\n"));
            System.out.println("collect >>> ");
            System.out.println(String.join(",", collect));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
