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

public class LFFCommand implements CommandExecutor {

    public LFFCommand(MediaCommands plugin){
        this.plugin = plugin;
        Bukkit.getPluginCommand("LFF").setExecutor(this);
    }
    private final MediaCommands plugin;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            utils.sendMessage(sender, "OnlyPlayers");
            return true;
        }

        if(args.length == 0) {
            if (sender.hasPermission("MediaCommands.LFF")) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    utils.sendListMessage(online, "LFFMessage", "%Player%", sender.getName(), "", "");
                }
                if (plugin.lffwebhook != null) {
                    String embedautor = plugin.settings.getConfig().getString("LFFWebhook.Author").replace("%Player%", sender.getName());
                    String description = plugin.settings.getConfig().getString("LFFWebhook.Description").replace("%Player%", sender.getName());
                    String title = plugin.settings.getConfig().getString("LFFWebhook.Title").replace("%Player%", sender.getName());
                    String color = plugin.settings.getConfig().getString("LFFWebhook.Color");
                    String username = plugin.settings.getConfig().getString("LFFWebhook.Username").replace("%Player%", sender.getName());
                    String footermsg = plugin.settings.getConfig().getString("LFFWebhook.Footer.Message").replace("%Player%", sender.getName());
                    String footerlink = plugin.settings.getConfig().getString("LFFWebhook.Footer.Link").replace("%Player%", sender.getName());
                    String thumbnail = plugin.settings.getConfig().getString("LFFWebhook.Thumbnail").replace("%Player%", sender.getName());
                    DiscordWebhook webhook = plugin.lffwebhook;
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