package com.giousa.tools.deviation;

public class DeviationTest {

    public static void main(String[] args) {
        System.out.println("不偏移： "+ConsultAbilityMask.VIRTUAL_DOCTOR.getMask());
        System.out.println("偏移1位： "+ConsultAbilityMask.FITNESS_TRAINER.getMask());
        System.out.println("偏移2位： "+ConsultAbilityMask.HEALTH_MANAGER.getMask());
        System.out.println("偏移3位： "+ConsultAbilityMask.PSYCHOLOGIST.getMask());
        System.out.println("偏移4位： "+ConsultAbilityMask.NUTRITIONIST.getMask());
        System.out.println("偏移5位： "+ConsultAbilityMask.REHABILITATION.getMask());
        System.out.println("偏移6位： "+ConsultAbilityMask.REGISTRATION.getMask());
        System.out.println("偏移14位： "+ConsultAbilityMask.VIDEO_CONSULT.getMask());

        if((50 & ConsultAbilityMask.REHABILITATION.getMask()) != 0){
            System.out.println("存在REHABILITATION权限");
        }else {
            System.out.println("不存在REHABILITATION权限");
        }
    }
}
