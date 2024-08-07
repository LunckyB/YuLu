package com.yulu.common.tools;

public class VerifyUtils extends org.apache.commons.lang3.StringUtils {
    private static final String NULLSTR = ""; // 空字符串

    // 判断一个字符串是否为空串
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    // 判断一个字符串是否为非空串
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    // 判断一个对象是否为空
    public static boolean isNull(Object object) {
        return object == null;
    }

    // 判断一个对象是否非空
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }
}
