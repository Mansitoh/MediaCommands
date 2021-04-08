package dev.mansitoh.mediacommands;

import club.minnced.discord.webhook.WebhookClient;
import dev.mansitoh.mediacommands.commands.RecordingCommand;
import dev.mansitoh.mediacommands.commands.StreamCommand;
import dev.mansitoh.mediacommands.commands.VideoCommand;
import dev.mansitoh.mediacommands.files.MessagesFile;
import dev.mansitoh.mediacommands.files.SettingsFile;
import dev.mansitoh.mediacommands.utils.Console;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class MediaCommands extends JavaPlugin {


    private static MediaCommands instance;
    public MessagesFile messagesfile;
    public static WebhookClient recordwebhook;
    public static WebhookClient streamwebhook;
    public static WebhookClient videowebhook;

    public SettingsFile settings;
    /*
    
    ███╗░░░███╗███████╗██████╗░██╗░█████╗░  ░█████╗░░█████╗░███╗░░░███╗███╗░░░███╗░█████╗░███╗░░██╗██████╗░░██████╗
    ████╗░████║██╔════╝██╔══██╗██║██╔══██╗  ██╔══██╗██╔══██╗████╗░████║████╗░████║██╔══██╗████╗░██║██╔══██╗██╔════╝
    ██╔████╔██║█████╗░░██║░░██║██║███████║  ██║░░╚═╝██║░░██║██╔████╔██║██╔████╔██║███████║██╔██╗██║██║░░██║╚█████╗░
    ██║╚██╔╝██║██╔══╝░░██║░░██║██║██╔══██║  ██║░░██╗██║░░██║██║╚██╔╝██║██║╚██╔╝██║██╔══██║██║╚████║██║░░██║░╚═══██╗
    ██║░╚═╝░██║███████╗██████╔╝██║██║░░██║  ╚█████╔╝╚█████╔╝██║░╚═╝░██║██║░╚═╝░██║██║░░██║██║░╚███║██████╔╝██████╔╝
    ╚═╝░░░░░╚═╝╚══════╝╚═════╝░╚═╝╚═╝░░╚═╝  ░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░╚═════╝░
    
        */
    @Override
    public void onEnable() {
       Console.log("&a███╗░░░███╗███████╗██████╗░██╗░█████╗░  ░█████╗░░█████╗░███╗░░░███╗███╗░░░███╗░█████╗░███╗░░██╗██████╗░░██████╗");
       Console.log("&a████╗░████║██╔════╝██╔══██╗██║██╔══██╗  ██╔══██╗██╔══██╗████╗░████║████╗░████║██╔══██╗████╗░██║██╔══██╗██╔════╝");
       Console.log("&a██╔████╔██║█████╗░░██║░░██║██║███████║  ██║░░╚═╝██║░░██║██╔████╔██║██╔████╔██║███████║██╔██╗██║██║░░██║╚█████╗░");
       Console.log("&a██║╚██╔╝██║██╔══╝░░██║░░██║██║██╔══██║  ██║░░██╗██║░░██║██║╚██╔╝██║██║╚██╔╝██║██╔══██║██║╚████║██║░░██║░╚═══██╗");
       Console.log("&a██║░╚═╝░██║███████╗██████╔╝██║██║░░██║  ╚█████╔╝╚█████╔╝██║░╚═╝░██║██║░╚═╝░██║██║░░██║██║░╚███║██████╔╝██████╔╝");
       Console.log("&a╚═╝░░░░░╚═╝╚══════╝╚═════╝░╚═╝╚═╝░░╚═╝  ░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░╚═════╝░");
       Console.log("&aDᴇᴠᴇʟᴏᴘᴇᴅ ʙʏ Mᴀɴsɪᴛᴏʜ");
       Console.log("&bTᴡɪᴛᴛᴇʀ: @Mansitoh_Py");
       Console.log("&5Dɪsᴄᴏʀᴅ: Mansitoh#9540");
       Console.log("&3Pᴀʏᴘᴀʟ: https://paypal.me/Mansitoh");
       Console.log("&f&lVᴇʀsɪᴏɴ 1.0.0");
       Console.log("&6Sᴘɪɢᴏᴛ: https://www.spigotmc.org/resources/media-commands.91042/");
       Console.log("&7Sᴛᴀᴛᴜs: &aEɴᴀʙʟɪɴɢ");
       messagesfile = new MessagesFile();
       settings = new SettingsFile();
        try {
            settings.createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            messagesfile.createFile();
        } catch (IOException e) {
        }
        new RecordingCommand(this);
        new StreamCommand(this);
        new VideoCommand(this);

        if(settings.getConfig().getBoolean("RecordingWebhook.Enabled") == true) {
            String webhookurl = messagesfile.getConfig().getString("RecordingWebhook.WebhookURL");
            recordwebhook = WebhookClient.withUrl(webhookurl);
        }else{
            recordwebhook = null;
        }

        if(settings.getConfig().getBoolean("StreamingWebhook.Enabled") == true) {
            String webhookurl = messagesfile.getConfig().getString("StreamingWebhook.WebhookURL");
            streamwebhook = WebhookClient.withUrl(webhookurl);
        }else{
            streamwebhook = null;
        }

        if(settings.getConfig().getBoolean("VideoWebhook.Enabled") == true) {
            String webhookurl = messagesfile.getConfig().getString("VideoWebhook.WebhookURL");
            videowebhook = WebhookClient.withUrl(webhookurl);
        }else{
            videowebhook = null;
        }
    }

    @Override
    public void onDisable() {
        Console.log("&a███╗░░░███╗███████╗██████╗░██╗░█████╗░  ░█████╗░░█████╗░███╗░░░███╗███╗░░░███╗░█████╗░███╗░░██╗██████╗░░██████╗");
        Console.log("&a████╗░████║██╔════╝██╔══██╗██║██╔══██╗  ██╔══██╗██╔══██╗████╗░████║████╗░████║██╔══██╗████╗░██║██╔══██╗██╔════╝");
        Console.log("&a██╔████╔██║█████╗░░██║░░██║██║███████║  ██║░░╚═╝██║░░██║██╔████╔██║██╔████╔██║███████║██╔██╗██║██║░░██║╚█████╗░");
        Console.log("&a██║╚██╔╝██║██╔══╝░░██║░░██║██║██╔══██║  ██║░░██╗██║░░██║██║╚██╔╝██║██║╚██╔╝██║██╔══██║██║╚████║██║░░██║░╚═══██╗");
        Console.log("&a██║░╚═╝░██║███████╗██████╔╝██║██║░░██║  ╚█████╔╝╚█████╔╝██║░╚═╝░██║██║░╚═╝░██║██║░░██║██║░╚███║██████╔╝██████╔╝");
        Console.log("&a╚═╝░░░░░╚═╝╚══════╝╚═════╝░╚═╝╚═╝░░╚═╝  ░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░╚═════╝░");
        Console.log("&aDᴇᴠᴇʟᴏᴘᴇᴅ ʙʏ Mᴀɴsɪᴛᴏʜ");
        Console.log("&bTᴡɪᴛᴛᴇʀ: @Mansitoh_Py");
        Console.log("&5Dɪsᴄᴏʀᴅ: Mansitoh#9540");
        Console.log("&3Pᴀʏᴘᴀʟ: https://paypal.me/Mansitoh");
        Console.log("&f&lVᴇʀsɪᴏɴ 1.0.0");
        Console.log("&6Sᴘɪɢᴏᴛ: https://www.spigotmc.org/resources/media-commands.91042/");
        Console.log("&7Sᴛᴀᴛᴜs: &cDɪsᴀʙʟɪɴɢ");
    }


    public MediaCommands(){
        instance = this;
    }

    public static MediaCommands getInstance() {
        return instance;
    }



}
