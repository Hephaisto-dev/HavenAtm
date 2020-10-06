package fr.hephaisto.havenatm.managers;

import fr.hephaisto.havenatm.HavenAtm;
import fr.hephaisto.havenatm.listeners.PlayersEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventsManager {
    public static void register(HavenAtm instance) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayersEvent(), instance);
        pm.registerEvents(new GuiManager(), instance);
    }
}
