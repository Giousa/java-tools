package com.giousa.tools.lambda;

import lombok.Data;

import java.io.Serializable;

@Data
public class FunctionUser implements Serializable {

    private String username;

    private Integer age;

    private String url;
}
