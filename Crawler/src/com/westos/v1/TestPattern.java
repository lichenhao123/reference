package com.westos.v1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
    public static void main(String[] args) {
        // 正则
        Pattern pattern = Pattern.compile("<img class=\"BDE_Image\" src=\"(.*?)\"");
        // 假设原始的字符串是这样
        String str = "<img class=\"BDE_Image\" src=\"1.png\"> <img class=\"BDE_Image\" src=\"2.png\">";
        // 匹配
        Matcher matcher = pattern.matcher(str);
        // 如果有下一个
        while (matcher.find()) {
            // 取匹配的 组1， 即正则中用() 括起来的那部分
            System.out.println(matcher.group(1));
        }
        // 输出
        // 1.png
        // 2.png
    }
}
