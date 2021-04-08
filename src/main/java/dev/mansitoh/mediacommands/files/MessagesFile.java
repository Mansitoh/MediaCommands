package dev.mansitoh.mediacommands.files;

import dev.mansitoh.mediacommands.MediaCommands;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class MessagesFile extends YamlConfiguration {





    private MessagesFile config;

    private Plugin plugin = (Plugin) MediaCommands.getInstance();

    private File configFile;

    public MessagesFile getConfig() {
        if (this.config == null)
            this.config = new MessagesFile();
        return this.config;
    }

    public File getFile() {
        return this.configFile;
    }

    private Plugin main() {
        return (Plugin) MediaCommands.getInstance();
    }

    public MessagesFile() {
        this.plugin = main();
        this.configFile = new File("plugins/MediaCommands/Messages.yml");
        saveDefault();
        reload();
    }

    public void reload() {
        try {
            load(this.configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            save(this.configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDefault() {
        this.plugin.saveResource("Messages.yml", false);
    }



    public void createFile() throws IOException {
        if (!this.configFile.exists()) {
            this.configFile.createNewFile();
            saveDefault();
            this.config.save(this.configFile);
        }
    }

}