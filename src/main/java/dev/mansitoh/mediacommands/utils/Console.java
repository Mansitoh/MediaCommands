package dev.mansitoh.mediacommands.utils;

import org.bukkit.Bukkit;

public class Console {

    public static void log(String s){
        Bukkit.getConsoleSender().sendMessage(utils.chat(s));
    }
}
