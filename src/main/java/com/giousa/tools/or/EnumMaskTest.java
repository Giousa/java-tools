package com.giousa.tools.or;

import static com.giousa.tools.or.PermissionFeature.*;

public class EnumMaskTest {

    public static void main(String[] args) {

        PermissionFeature[] userPermission = new PermissionFeature[]{USER, QUERY, STAT};
        int features = PermissionFeature.of(userPermission);
        System.out.println("打印权限begin：");
        printUserPersission(features);
        System.out.println("打印权限end：");

        features = PermissionFeature.addFeature(features, ANALYSE);
        System.out.println("-------------------");
        printUserPersission(features);

        features = PermissionFeature.deleteFeature(features, STAT);
        System.out.println("-------------------");
        printUserPersission(features);
    }

    private static void printUserPersission(int features) {
        System.out.println("用户拥有的权限如下:");
        for (PermissionFeature feature : PermissionFeature.values()) {
            if (PermissionFeature.isEnabled(features, feature)) {
                System.out.println("权限：" + feature.getName());
            }
        }
    }
}

