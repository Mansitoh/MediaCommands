package dev.mansitoh.mediacommands.commands;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import dev.mansitoh.mediacommands.MediaCommands;
import dev.mansitoh.mediacommands.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                    int color = plugin.settings.getConfig().getInt("StreamingWebhook.Color");
                    WebhookEmbedBuilder embed = new WebhookEmbedBuilder().setAuthor(new WebhookEmbed.EmbedAuthor(embedautor, null, null)).setDescription(description).setTitle(new WebhookEmbed.EmbedTitle(title, null)).setColor(color);
                    plugin.streamwebhook.send(embed.build());
                    plugin.streamwebhook.close();
                }
            }
        }
        return false;
    }


}
