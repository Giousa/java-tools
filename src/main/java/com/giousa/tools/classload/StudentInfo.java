package com.giousa.tools.classload;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentInfo implements Serializable {

    private Long id;

    private String name;
}
