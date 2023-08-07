package com.giousa.tools.json;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public final class JsonProcessor {

    private final List<ClassEntity> classEntities;
    private final JsonParser parser;
    private final String rootClassName;
    private final String packageName;

    public JsonProcessor(String rootClassName, String packageName) {
        this.rootClassName = rootClassName;
        this.packageName = packageName;
        classEntities = new ArrayList<>();
        parser = new JsonParser();
    }

    public List<ClassEntity> parserJsonToClass(String sourceStr) {
        JsonElement root = parser.parse(sourceStr);
        parse(rootClassName, root, null, true);
        return classEntities;
    }

    private void parse(String key, JsonElement root, ClassEntity rootModel, boolean addFieldToRoot) {
        if (root == null) {
            return;
        }
        if (root.isJsonObject()) {
            ClassEntity classEntity = new ClassEntity(key, packageName);
            classEntities.add(classEntity);
            if (rootModel == null) {
                rootModel = classEntity;
            }
            if (!addFieldToRoot) {
                rootModel.getFieldEntities().add(new FieldEntity(key, root));
            }
            JsonObject object = root.getAsJsonObject();
            for (String itemKey : object.keySet()) {
                parse(itemKey, object.get(itemKey), classEntity, false);
            }
        } else if (root.isJsonPrimitive()) {
            rootModel.getFieldEntities().add(new FieldEntity(key, root.getAsJsonPrimitive()));
        } else if (root.isJsonArray()) {
            FieldEntity arrayFieldEntity = new FieldEntity(key, root.getAsJsonArray());
            rootModel.getFieldEntities().add(arrayFieldEntity);
            parseArray(root.getAsJsonArray(), JsonStringUtils.dealMiddleLine(key), arrayFieldEntity, "List<>");
        }
    }

    private void parseArray(JsonArray jsonArray, String key, FieldEntity arrayFieldEntity, String generic) {
        if (!jsonArray.iterator().hasNext()) {
            return;
        }
        JsonElement element = jsonArray.iterator().next();
        if (element.isJsonObject()) {
            arrayFieldEntity.setFieldType(insertGeneric(generic, JsonStringUtils.reverseFirstLetter(key)));
            parse(key, element, null, true);
        } else if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (primitive.isString()) {
                arrayFieldEntity.setFieldType(insertGeneric(generic, "String"));
            } else if (primitive.isBoolean()) {
                arrayFieldEntity.setFieldType(insertGeneric(generic, "Boolean"));
            } else if (primitive.isNumber()) {
                arrayFieldEntity.setFieldType(insertGeneric(generic, "Integer"));
            }
        } else if (element.isJsonArray()) {
            parseArray(element.getAsJsonArray(), key, arrayFieldEntity, insertGeneric(generic, "List<>"));
        }
    }

    private String insertGeneric(String generic, String key) {
        StringBuilder genericBuilder = new StringBuilder(generic);
        int index = genericBuilder.indexOf("<>");
        genericBuilder.insert(index + 1, key);
        return genericBuilder.toString();
    }
}
