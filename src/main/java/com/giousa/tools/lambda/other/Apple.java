package com.giousa.tools.lambda.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple implements Serializable {
    private String name; // 名称
    private String color; // 颜色
    private Double weight; // 重量
    private Double price; // 价格
}
