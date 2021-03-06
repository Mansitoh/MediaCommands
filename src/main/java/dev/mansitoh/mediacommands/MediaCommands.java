package dev.mansitoh.mediacommands;

import dev.mansitoh.mediacommands.commands.LFFCommand;
import dev.mansitoh.mediacommands.utils.DiscordWebhook;
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
    public static DiscordWebhook recordwebhook;
    public static DiscordWebhook streamwebhook;
    public static DiscordWebhook videowebhook;

    public SettingsFile settings;
    public DiscordWebhook lffwebhook;

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
       Console.log("&f&lVᴇʀsɪᴏɴ 1.1.3");
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
        new LFFCommand(this);

        if(settings.getConfig().getBoolean("RecordingWebhook.Enabled") == true) {
            String webhookurl = messagesfile.getConfig().getString("RecordingWebhook.WebhookURL");
            recordwebhook = new DiscordWebhook(webhookurl);
        }else{
            recordwebhook = null;
        }

        if(settings.getConfig().getBoolean("StreamingWebhook.Enabled") == true) {
            String webhookurl = messagesfile.getConfig().getString("StreamingWebhook.WebhookURL");
            streamwebhook = new DiscordWebhook(webhookurl);
        }else{
            streamwebhook = null;
        }

        if(settings.getConfig().getBoolean("VideoWebhook.Enabled") == true) {
            String webhookurl = messagesfile.getConfig().getString("VideoWebhook.WebhookURL");
            videowebhook = new DiscordWebhook(webhookurl);
        }else{
            videowebhook = null;
        }
        if(settings.getConfig().getBoolean("LFFWebhook.Enabled") == true) {
            String webhookurl = messagesfile.getConfig().getString("LFFWebhook.WebhookURL");
            lffwebhook = new DiscordWebhook(webhookurl);
        }else{
            lffwebhook = null;
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
        Console.log("&f&lVᴇʀsɪᴏɴ 1.1.3");
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
