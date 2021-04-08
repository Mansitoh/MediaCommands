package dev.mansitoh.mediacommands.files;

import dev.mansitoh.mediacommands.MediaCommands;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class SettingsFile extends YamlConfiguration {

    private SettingsFile config;

    private Plugin plugin = (Plugin) MediaCommands.getInstance();

    private File configFile;

    public SettingsFile getConfig() {
        if (this.config == null)
            this.config = new SettingsFile();
        return this.config;
    }

    public File getFile() {
        return this.configFile;
    }

    private Plugin main() {
        return (Plugin) MediaCommands.getInstance();
    }

    public SettingsFile() {
        this.plugin = main();
        this.configFile = new File("plugins/MediaCommands/Settings.yml");
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
        this.plugin.saveResource("Settings.yml", false);
    }



    public void createFile() throws IOException {
        if (!this.configFile.exists()) {
            this.configFile.createNewFile();
            saveDefault();
            this.config.save(this.configFile);
        }
    }

}
