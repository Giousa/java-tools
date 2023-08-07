package com.giousa.tools.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class FieldEntity {

  private String fieldName;
  private String fieldType;

  public FieldEntity(String fieldName, JsonElement element) {
    this.fieldName = fieldName;

    if (element.isJsonPrimitive()) {
      JsonPrimitive primitive = element.getAsJsonPrimitive();
      if (primitive.isBoolean()) {
        fieldType = "Boolean";
      } else if (primitive.isNumber()) {
        fieldType = "Integer";
      } else if (primitive.isString()) {
        fieldType = "String";
      }
    } else if (element.isJsonObject()) {
      fieldType = JsonStringUtils.dealMiddleLine(JsonStringUtils.reverseFirstLetter(fieldName));
    } else if (element.isJsonArray()) {
      fieldType = "List";
    }
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldType() {
    return fieldType;
  }

  public void setFieldType(String fieldType) {
    this.fieldType = fieldType;
  }
}
