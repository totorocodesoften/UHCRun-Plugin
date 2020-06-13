package com.totocode.uhcrun;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new UHCListener(this), this);
        super.onEnable();
    }
}
