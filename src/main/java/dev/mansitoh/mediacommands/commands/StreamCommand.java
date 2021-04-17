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

public class StreamCommand implements CommandExecutor {

    public StreamCommand(MediaCommands plugin){
        this.plugin = plugin;
        Bukkit.getPluginCommand("Stream").setExecutor(this);
    }
    private final MediaCommands plugin;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            utils.sendMessage(sender, "OnlyPlayers");
            return true;
        }
        if(args.length == 0){
            utils.sendMessage(sender,"StreamUsage");
            return true;
        }
        if(args.length == 1) {
            if (sender.hasPermission("MediaCommands.Streaming")) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    utils.sendListMessage(online, "StreamingMessage", "%Player%", sender.getName(), "%Link%", args[0]);
                }
                if (plugin.streamwebhook != null) {

                    String embedautor = plugin.settings.getConfig().getString("StreamingWebhook.Author").replace("%Player%", sender.getName());
                    String description = plugin.settings.getConfig().getString("StreamingWebhook.Description").replace("%Player%", sender.getName()).replace("%Link%", args[0]);
                    String title = plugin.settings.getConfig().getString("StreamingWebhook.Title").replace("%Player%", sender.getName());
                    String color = plugin.settings.getConfig().getString("RecordingWebhook.Color");
                    String username = plugin.settings.getConfig().getString("StreamingWebhook.Username").replace("%Player%", sender.getName());
                    String footermsg = plugin.settings.getConfig().getString("StreamingWebhook.Footer.Message").replace("%Player%", sender.getName());
                    String footerlink = plugin.settings.getConfig().getString("StreamingWebhook.Footer.Link").replace("%Player%", sender.getName());
                    String thumbnail = plugin.settings.getConfig().getString("StreamingWebhook.Thumbnail").replace("%Player%", sender.getName());
                    DiscordWebhook webhook = plugin.streamwebhook;
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
        }
        return false;
    }


}
