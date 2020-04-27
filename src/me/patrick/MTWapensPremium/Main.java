package me.patrick.MTWapensPremium;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.saveConfig();
        getCommand("getwapens").setExecutor(new WapenFabriek(this));
        getCommand("getkogels").setExecutor(new KogelFabriek(this));
        this.getServer().getPluginManager().registerEvents(new WapenHandler(this), this);
    }

    @Override
    public void onDisable() {

    }
}
