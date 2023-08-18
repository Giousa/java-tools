package com.giousa.tools.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTest {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("1","2","3","4","5");
        List<String> params = Lists.newArrayList();
        for (int i = 1; i < list.size()+1; i++) {
            params.add("param"+i);
        }
        System.out.println("参数列表: "+params);

        List<Map<String,String>> listMap = Lists.newArrayList();

        for (int i = 0; i < 5; i++) {
            Map<String,String> map = new HashMap<>();
            for (int j = 0; j < 10; j++) {
                map.put("param"+j, StringUtils.join("数据","#",i,"#",j));
            }
            listMap.add(map);
        }

        System.out.println(JSON.toJSONString(listMap));
    }
}
