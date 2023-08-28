package com.giousa.tools.file;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileScanDemo {

    public static void main(String[] args) {
        //IDEA 输入框，默认查询IDEA文件目录传入path，手动选择目录进行生成

        // 路径
        String path = "/Users/zhangmengmeng/workspace/github/java-tools/src/main/java/com/giousa/dubbo";
        try {
            Stream<Path> paths = Files.walk(Paths.get(path));
            List<Path> fileNames = paths.filter(Files::isRegularFile).collect(Collectors.toList());
            System.out.println("fileNames total >>> " + fileNames.size());
            System.out.println(JSON.toJSONString(fileNames));

            //map:: 目录 --> 类名
            Map<String, List<ImmutablePair<String,String>>> map = Maps.newHashMap();
            fileNames.forEach(filePath -> {
                String filePathStr = filePath.toFile().getPath();
                String fileName = filePath.toFile().getName();
                String folderName = filePathStr.replace(fileName, "").replace(path,"").replaceAll("/",".");
                if(folderName.startsWith(".")){
                    folderName = folderName.substring(1);
                }
                if(folderName.endsWith(".")){
                    folderName = folderName.substring(0,folderName.length() -1);
                }
                if(StringUtils.isBlank(folderName) || Objects.equals("/",folderName)){
                    folderName = "-root-";
                }

                List<ImmutablePair<String,String>> list = map.get(folderName);
                if (CollectionUtils.isEmpty(list)) {
                    list = Lists.newArrayList();
                }
//                list.add(getFileContent(filePathStr));
                list.add(ImmutablePair.of(fileName,filePathStr));
                map.put(folderName, list);
            });

            System.out.println(JSON.toJSONString(map));


            map.forEach((k,v) -> {
                buildWordFile(k,v);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

//        getFileContent("/Users/zhangmengmeng/workspace/github/java-tools/src/main/java/com/giousa/dubbo/framework/protocol/http/HttpServerHandler.java");
    }

    //指定生成的文件
    private static final String local_file = "/Users/zhangmengmeng/Desktop/ftl_temp_file/文档.docx";

    private static void buildWordFile(String folderName, List<ImmutablePair<String, String>> list) {
        String title = "\n" +
                "///////////////////////////////////////////////////////////////////////////\n" +
                "// %s\n" +
                "///////////////////////////////////////////////////////////////////////////\n";

        String headTitle = String.format(title, folderName);
        //写入大标题
        FileManager.writeContent(local_file,headTitle);

        for (int i = 0; i < list.size(); i++) {
            ImmutablePair<String, String> pair = list.get(i);
            //写入文件名称
            FileManager.writeContent(local_file,"\n");
            FileManager.writeContent(local_file,StringUtils.join((i+1)+"、",pair.left));
            FileManager.writeContent(local_file,"\n");
            //写入文件内容
            FileManager.writeContent(local_file,getFileContent(pair.right));
            FileManager.writeContent(local_file,"\n");
        }
    }


    /**
     * 获取文件内容
     */
    private static String getFileContent(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            return lines.stream().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            System.out.println("获取文件内容失败! fileName = " + fileName);
        }
        return "获取文件内容失败！";
    }
}
