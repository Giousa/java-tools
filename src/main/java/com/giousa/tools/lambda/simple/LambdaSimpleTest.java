package com.giousa.tools.lambda.simple;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class LambdaSimpleTest {

    public static void main(String[] args) {
        Map<Long, String> map = Maps.newHashMap();
        map.put(1L, "a");
        map.put(2L, "b");
        map.put(3L, "c");

        Long b = getVolumeKey(map, "b");
        System.out.println("result >>> " + b);
    }

    public static Long getVolumeKey(Map<Long, String> map, String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        return map.entrySet().stream().filter(it -> Objects.equals(value, it.getValue())).map(Map.Entry::getKey).findFirst().orElse(null);
    }
}
