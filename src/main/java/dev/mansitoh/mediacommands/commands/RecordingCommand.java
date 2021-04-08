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

public class RecordingCommand implements CommandExecutor {

    public RecordingCommand(MediaCommands plugin){
        this.plugin = plugin;
       Bukkit.getPluginCommand("rec").setExecutor(this);
    }
    private final MediaCommands plugin;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            utils.sendMessage(sender, "OnlyPlayers");
            return true;
        }

        if(args.length == 0) {
            if (sender.hasPermission("MediaCommands.Recording")) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    utils.sendListMessage(online, "RecordingMessage", "%Player%", sender.getName(), "", "");
                }
                if (plugin.recordwebhook != null) {
                    String embedautor = plugin.settings.getConfig().getString("RecordingWebhook.Author").replace("%Player%", sender.getName());
                    String description = plugin.settings.getConfig().getString("RecordingWebhook.Description").replace("%Player%", sender.getName());
                    String title = plugin.settings.getConfig().getString("RecordingWebhook.Title").replace("%Player%", sender.getName());
                    int color = plugin.settings.getConfig().getInt("RecordingWebhook.Color");
                    WebhookEmbedBuilder embed = new WebhookEmbedBuilder().setAuthor(new WebhookEmbed.EmbedAuthor(embedautor, null, null)).setDescription(description).setTitle(new WebhookEmbed.EmbedTitle(title, null)).setColor(color);
                    plugin.recordwebhook.send(embed.build());
                    plugin.recordwebhook.close();
                }
            }
        }
        return false;
    }


}
