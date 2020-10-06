package fr.hephaisto.havenatm;

import fr.hephaisto.havenatm.managers.Managers;
import org.bukkit.plugin.java.JavaPlugin;

public final class HavenAtm extends JavaPlugin {
    Managers managers = new Managers();
    @Override
    public void onEnable() {
        managers.load(this);
    }

    @Override
    public void onDisable() {
        managers.unload();
    }
}
