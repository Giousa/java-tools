package com.giousa.tools.excel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExcelTest {

    public static void main(String[] args) {
        String excelPath = "/Users/zhangmengmeng/Desktop/pro-家医与医生集团映射表.xlsx";
        List<String> sheets = POIUtil.getSheets(excelPath);
        if(CollectionUtils.isEmpty(sheets)){
            System.out.println("sheet为空");
            return;
        }
        System.out.println("sheets = "+ JSON.toJSONString(sheets));

        //默认取第一个
        ImmutablePair<Map<String, List<String>>, Map<String, List<String[]>>> pair = POIUtil.readExcel(excelPath);

        if(Objects.isNull(pair)){
            return;
        }

        //循环打印sheet转FTL模板信息
        for(String sheet : sheets){
            getFtlMap(sheet,pair.right);
        }
    }

    private static String getFtlMap(String sheet,Map<String, List<String[]>> cellMap){
        if(MapUtils.isEmpty(cellMap)){
            return null;
        }
        List<String[]> cellList = cellMap.get(sheet);
        if(CollectionUtils.isEmpty(cellList)){
            return null;
        }

        List<Map<String,String>> listMap = Lists.newArrayList();
        for (int i = 0; i < cellList.size(); i++) {
            Map<String,String> map = new HashMap<>();
            String[] cellArray = cellList.get(i);
            for (int j = 1; j < cellArray.length+1; j++) {
                map.put("param"+j, cellArray[j-1]);
            }
            listMap.add(map);
        }

        System.out.println(sheet + " 转FTL map >>>");
        String jsonString = JSON.toJSONString(listMap);
        System.out.println(jsonString);
        return jsonString;
    }
}
