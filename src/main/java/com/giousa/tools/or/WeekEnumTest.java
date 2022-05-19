package com.giousa.tools.or;

public class WeekEnumTest {

    public static void main(String[] args) {
        System.out.println("序号index：");
        System.out.println(WeekEnum.FRI.getIndex());

        System.out.println("返回枚举常量的序数(它在枚举声明中的位置，其中初始常量序数为零)：");
        System.out.println(WeekEnum.MON.ordinal());
        System.out.println(WeekEnum.TUES.ordinal());
        System.out.println(WeekEnum.WEB.ordinal());
        System.out.println(WeekEnum.THUR.ordinal());
        System.out.println(WeekEnum.FRI.ordinal());
        System.out.println(WeekEnum.SAT.ordinal());
        System.out.println(WeekEnum.SUN.ordinal());

        System.out.println("左移：<<");
        System.out.println(1 << 0);//1
        //0001 << 1  2^1  0000 0010 = 2
        System.out.println(1 << 1);//2
        System.out.println(1 << 2);//4
        //0001 << 3  2^3  0000 1000 = 8
        System.out.println(1 << 3);//8
        System.out.println(1 << 4);//16
        //0001 << 5  2^5  0010 0000 = 32
        System.out.println(1 << 5);//32
        System.out.println(1 << 6);//64

        for(WeekEnum weekEnum : WeekEnum.values()){
            System.out.println("轮询："+weekEnum.name());
        }
    }
}
