package com.westos.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestFetch {
    public static void main(String[] args) throws IOException {
        String s = Utils.fetch("https://tieba.baidu.com/p/2256306796?red_tag=1781367364").get();
        Files.write(Paths.get("d:\\1.html"), s.getBytes(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
    }
}
