package com.giousa.tools.string;

import org.apache.commons.lang3.StringUtils;

public class StringTest {

    public static void main(String[] args) {
        String fileName = "ConsultEmrCaseController.java";
        String classPath = "Users/zhangmengmeng/workspace/stjk/consult-workbench/consultworkbench-core/src/main/java/com/stjk/consultworkbench/web/controller/ConsultEmrCaseController.java";
        System.out.println("文件路径："+classPath);
        classPath = classPath.replace("/", ".");
        String className = classPath.substring(classPath.indexOf("src.main.java"));
        System.out.println("className = "+className);

        String packageName = className.replace("src.main.java.","").replace(StringUtils.join(".",fileName),"");
        System.out.println("packageName = "+packageName);


    }
}
