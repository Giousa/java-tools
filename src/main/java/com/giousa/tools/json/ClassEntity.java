package com.giousa.tools.json;


import java.util.ArrayList;
import java.util.List;

public class ClassEntity {

    private String packageName;
    private String className;
    private List<FieldEntity> fieldEntities;

    public ClassEntity(String className, String packageName) {
        this.className = JsonStringUtils.dealMiddleLine(JsonStringUtils.reverseFirstLetter(className));
        this.fieldEntities = new ArrayList<>();
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<FieldEntity> getFieldEntities() {
        return fieldEntities;
    }

    public void setFieldEntities(List<FieldEntity> fieldEntities) {
        this.fieldEntities = fieldEntities;
    }
}
