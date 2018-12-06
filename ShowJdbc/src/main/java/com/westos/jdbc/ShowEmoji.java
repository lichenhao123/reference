package com.westos.jdbc;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import java.util.Collection;

public class ShowEmoji {
    public static void main(String[] args) {
        Collection<Emoji> all = EmojiManager.getAll();
        int i = 1;
        for (Emoji emoji : all) {
            System.out.print(emoji.getUnicode());
            if(i % 50==0) {
                System.out.println();
            }
            i++;
        }
    }
}
