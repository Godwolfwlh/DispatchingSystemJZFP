package com.yhzhcs.dispatchingsystemjzfp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具
 *
 * Created by Luhuai Wu on 2018-3-14.
 */
public class ValidateUtil {

    private ValidateUtil() {
        // 防止被实例化
    }

    /**
     * 字符串是否符合正则表达式的规则
     *
     * @param text 匹配文本
     * @param format 匹配规则
     * @return true匹配成功 flase 匹配失败
     */
    private static boolean isMatches(String text, String format) {
        Pattern pattern = Pattern.compile(format);
        Matcher m = pattern.matcher(text);
        return m.matches();
    }

    /**
     * 匹配帐号类型是否正确
     *
     * @param str 帐号
     * @return true=符合 false=不符合
     */
    public static boolean isAccount(String str) {
        String format = "[a-zA-Z0-9]{0,20}";
        return isMatches(str, format);
    }

    /**
     * 匹配密码是否符合要求
     *
     * @param money 密码字符串
     * @return true=符合 false=不符合
     */
    public static boolean isPassWord(String money) {
        String regex = "(^[1-9][0-9]{0,7}(\\.[0-9]{0,2})?)|(^0(\\.[0-9]{0,2})?)";
        return isMatches(money, regex);
    }

}
