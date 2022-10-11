package com.giousa.tools.or;

/**
 * 用户权限, 管理员与普通用户权限不同
 * <p>
 * |= 位或运算符 ：
 * |= 运算符和 += 这一类的运算符一样，拆解开就是 a = a | b；
 * 运算规则：两个数都转为二进制，然后从高位开始比较，两个数只要有一个为1则为1，否则就为0。
 * 比如：129|128.
 * 129转换成二进制就是10000001，128转换成二进制就是10000000。从高位开始比较得到，得到10000001，即129.
 * <p>
 * &= 位与运算符 ：
 * &= 运算符和 += 这一类的运算符一样，拆解开就是 a = a & b；
 * 运算规则：两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0。
 * 比如：129&128.
 * 129转换成二进制就是10000001，128转换成二进制就是10000000。从高位开始比较得到，得到10000000，即128.
 * <p>
 * ^= 位异或运算 ：
 * ^= 运算符和 += 这一类的运算符一样，拆解开就是 a = a^b；
 * 运算规则是：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。
 * 比如：8^11.
 * 8转为二进制是1000，11转为二进制是1011.从高位开始比较得到的是：0011.然后二进制转为十进制，就是Integer.parseInt(“0011”,2)=3;
 * 也就是说对同一个字符进行两次异或运算就会回到原来的值。
 * <p>
 * ~= 位非运算符 ：
 * 运算规则：如果位为0，结果是1，如果位为1，结果是0.
 * 比如：~37
 * 在Java中，所有数据的表示方法都是以补码的形式表示，如果没有特殊说明，Java中的数据类型默认是int,int数据类型的长度是8位，一位是四个字节，就是32字节，32bit.
 * 8转为二进制是100101.
 * 补码后为： 00000000 00000000 00000000 00100101
 * 取反为： 11111111 11111111 11111111 11011010
 * 因为高位是1，所以原码为负数，负数的补码是其绝对值的原码取反，末尾再加1。
 * 因此，我们可将这个二进制数的补码进行还原： 首先，末尾减1得反码：11111111 11111111 11111111 11011001 其次，将各位取反得原码：
 * 00000000 00000000 00000000 00100110，此时二进制转原码为38
 * 所以~37 = -38.
 */
public enum PermissionFeature {

    ADMIN("管理员"),
    USER("普通用户"),
    ADD("增加"),
    MODIFY("修改"),
    DELETE("删除"),
    QUERY("查询"),
    ANALYSE("分析"),
    STAT("统计");

    PermissionFeature(String name) {
        this.name = name;
        int ordinal = ordinal();
        mask = (1 << ordinal);
        System.out.println("初始化枚举值！ name： " + name + ",mask: " + mask + ",ordinal: " + ordinal);
    }

    public final int mask;
    public final String name;

    public final int getMask() {
        return mask;
    }

    public final String getName() {
        return name;
    }

    /**
     * 是否设置
     *
     * @param features
     * @param feature
     * @return
     */
    public static boolean isEnabled(int features, PermissionFeature feature) {
        boolean flag = (features & feature.mask) != 0;
        System.out.println("判断当前枚举是否符合：featureName: " + feature.name + ",features: " + features + ",mask: " + feature.mask + ",是否符合: "+flag);
        return flag;
    }

    public static int of(PermissionFeature[] features) {
        if (features == null) {
            return 0;
        }

        int value = 0;

        for (PermissionFeature feature : features) {
            System.out.println("转换前：feature = " + feature + ",feature.mask = " + feature.mask + ",value = " + value);
            value |= feature.mask;
            System.out.println("转换后：feature = " + feature + ",feature.mask = " + feature.mask + ",value = " + value);
        }
        return value;
    }

    public static int addFeature(int features, PermissionFeature feature) {
        return features |= feature.getMask();
    }

    /**
     * ~表示非运算符，就是将该数的所有二进制位全取反
     */
    public static int deleteFeature(int features, PermissionFeature feature) {
        return features &= ~feature.getMask();
    }
}
