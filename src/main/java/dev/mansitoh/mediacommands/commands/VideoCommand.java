package dev.mansitoh.mediacommands.commands;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import dev.mansitoh.mediacommands.MediaCommands;
import dev.mansitoh.mediacommands.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                int color = plugin.settings.getConfig().getInt("VideoWebhook.Color");
                WebhookEmbedBuilder embed = new WebhookEmbedBuilder().setAuthor(new WebhookEmbed.EmbedAuthor(embedautor, null, null)).setDescription(description).setTitle(new WebhookEmbed.EmbedTitle(title, null)).setColor(color);
                plugin.videowebhook.send(embed.build());
                plugin.videowebhook.close();
            }
        }
        return false;
    }


}
