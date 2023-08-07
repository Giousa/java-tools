package com.giousa.tools.json;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class GenerateUtils {


    public static void main(String[] args) {
        String json = "{\n" +
                "  \"id\": 2,\n" +
                "  \"bizType\": \"zwrfeu\",\n" +
                "  \"flag\": true,\n" +
                "  \"isSwitch\": false,\n" +
                "  \"status\": 97,\n" +
                "  \"age\": 64,\n" +
                "  \"birthday\": \"2023-08-07 15:52:12\",\n" +
                "  \"ageDay\": 0,\n" +
                "  \"count\": 0\n" +
                "}";
        generate("com.giousa", "Student", json);
    }


    public static void generate(String packageName, String rootClassName, String jsonStr) {
        JsonProcessor processor = new JsonProcessor(rootClassName, packageName);
        List<ClassEntity> classEntities = processor.parserJsonToClass(jsonStr);
        try {
            for (ClassEntity model : classEntities) {
                System.out.println("生成类model: " + JSON.toJSONString(model));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
