package dev.mansitoh.mediacommands.commands;


import dev.mansitoh.mediacommands.MediaCommands;
import dev.mansitoh.mediacommands.utils.DiscordWebhook;
import dev.mansitoh.mediacommands.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;

public class VideoCommand implements CommandExecutor {

    public VideoCommand(MediaCommands plugin){
        this.plugin = plugin;
        Bukkit.getPluginCommand("Video").setExecutor(this);
    }
    public static MediaCommands plugin;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            utils.sendMessage(sender, "OnlyPlayers");
            return true;
        }
        if(args.length == 0){
            utils.sendMessage(sender,"VideoUsage");
            return true;
        }
        if(sender.hasPermission("MediaCommands.Video")){
            for(Player online : Bukkit.getOnlinePlayers()){
                utils.sendListMessage( online, "VideoMessage", "%Player%", sender.getName(), "%Link%", args[0]);
            }
            if(plugin.videowebhook != null) {
                String embedautor = plugin.settings.getConfig().getString("VideoWebhook.Author").replace("%Player%", sender.getName());
                String description = plugin.settings.getConfig().getString("VideoWebhook.Description").replace("%Player%", sender.getName()).replace("%Link%", args[0]);
                String title = plugin.settings.getConfig().getString("VideoWebhook.Title").replace("%Player%", sender.getName());
                String color = plugin.settings.getConfig().getString("VideoWebhook.Color");
                String username = plugin.settings.getConfig().getString("VideoWebhook.Username").replace("%Player%", sender.getName());
                String footermsg = plugin.settings.getConfig().getString("VideoWebhook.Footer.Message").replace("%Player%", sender.getName());
                String footerlink = plugin.settings.getConfig().getString("VideoWebhook.Footer.Link").replace("%Player%", sender.getName());
                String thumbnail = plugin.settings.getConfig().getString("VideoWebhook.Thumbnail").replace("%Player%", sender.getName());
                DiscordWebhook webhook = plugin.recordwebhook;
                webhook.setUsername(username);
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setAuthor(embedautor, null,null)
                        .setDescription(description)
                        .setColor(Color.getColor(color))
                        .setTitle(title)
                        .setThumbnail(thumbnail)
                        .setFooter(footermsg, footerlink)
                );
                try {
                    webhook.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


}
