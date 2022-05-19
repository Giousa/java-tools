package com.giousa.tools.or;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum WeekEnum {

    MON(1, "星期一"),
    TUES(2, "星期二"),
    WEB(3, "星期三"),
    THUR(4, "星期四"),
    FRI(5, "星期五"),
    SAT(6, "星期六"),
    SUN(7, "星期七"),

    ;

    WeekEnum(Integer index, String desc) {
        System.out.println("构造方法： index: "+index+",desc: "+desc);
        this.index = index;
        this.desc = desc;
    }

    private Integer index;

    private String desc;
}
