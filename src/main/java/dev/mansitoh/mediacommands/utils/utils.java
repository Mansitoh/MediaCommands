package dev.mansitoh.mediacommands.utils;

import dev.mansitoh.mediacommands.MediaCommands;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class utils {

    private static final MediaCommands plugin = MediaCommands.getInstance();

    public static String chat (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void sendMessage(CommandSender sender, String s){
        FileConfiguration config = plugin.messagesfile.getConfig();
        String message = config.getString(s);
        if(message != null) {
            sender.sendMessage(utils.chat(message.replace("%Prefix%", utils.getPrefix())));
        }else{
            Console.log("&cCan't Send \""+s+"\" to "+sender.getName()+" because is empty");
        }
    }

    private static String getPrefix() {
        FileConfiguration config = plugin.messagesfile.getConfig();

        String prefix = config.getString("Prefix");
        if (prefix != null) {
            return prefix;
        }else{
            return utils.chat("&4&lError!");
        }
    }

    public static void sendListMessage(Player sender, String s, String toreplace, String replaced, String toreplace2, String replaced2){
        FileConfiguration config = plugin.messagesfile.getConfig();
        ArrayList<String> list = (ArrayList<String>) config.getStringList(s);
        if(list != null) {
            for(String message : list) {
                sender.sendMessage(utils.chat(message.replace("%Prefix%", utils.getPrefix()).replace(toreplace, replaced)).replace(toreplace2, replaced2));
            }
        }else{
            Console.log("&cCan't Send \""+s+"\" to "+sender.getName()+" because is empty");
        }
    }

}
