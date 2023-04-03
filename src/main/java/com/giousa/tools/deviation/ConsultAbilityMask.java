package com.giousa.tools.deviation;

import java.util.ArrayList;
import java.util.List;

public enum ConsultAbilityMask {
    VIRTUAL_DOCTOR(1, "虚拟医生"),
    FITNESS_TRAINER(1 << 1, "健身教练"),
    HEALTH_MANAGER(1 << 2, "健康管理师"),
    PSYCHOLOGIST(1 << 3, "心理咨询师"),
    NUTRITIONIST(1 << 4, "营养师"),
    REHABILITATION(1 << 5, "康复治疗师"),
    REGISTRATION(1 << 6, "挂号客服"),
    FAMOUS_DOCTOR(1 << 7, "名医客服"),
    PHYSICAL_EXAMINATION_DOCTOR(1 << 8, "体检客服"),
    SPECIALIST(1 << 9, "专病医生"),
    SALESMAN(1 << 10, "销售助手"),
    RUIJIN(1 << 11, "瑞金医院医生"),
    SILENT_USER(1 << 12, "沉默用户开关"),
    ONLINE_CUSTOMER(1 << 13, "在线客服"),
    VIDEO_CONSULT(1 << 14, "预约视频接诊"),

    ;

    private int mask;

    private String desc;

    ConsultAbilityMask(int mask, String desc) {
        this.mask = mask;
        this.desc = desc;
    }

    public int getMask() {
        return mask;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isSupport(int mask) {
        return (this.getMask() & mask) > 0;
    }

    public static boolean isSupport(int mask, ConsultAbilityMask consultAbilityMask) {
        return (consultAbilityMask.getMask() & mask) > 0;
    }

    public static List<ConsultAbilityMask> enabledList(int mask) {
        List<ConsultAbilityMask> list = new ArrayList<>();
        for (ConsultAbilityMask c : ConsultAbilityMask.values()) {
            if (ConsultAbilityMask.isSupport(mask, c)) {
                list.add(c);
            }
        }
        return list;
    }

    public static ConsultAbilityMask of(int mask) {
        for (ConsultAbilityMask c : ConsultAbilityMask.values()) {
            if (c.mask == mask) {
                return c;
            }
        }
        return null;
    }
}
