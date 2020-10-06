package fr.hephaisto.havenatm.managers;

import fr.hephaisto.havenatm.HavenAtm;
import fr.hephaisto.havenatm.tools.GuiBuilder;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class Managers {
    private static HavenAtm instance;
    private static Managers managers;
    private GuiManager guiManager;

    private Map<Class<? extends GuiBuilder>, GuiBuilder> registeredMenus;

    public void load(HavenAtm instance) {
        instance = instance;
        managers = this;
        this.guiManager = new GuiManager();

        registeredMenus = new HashMap<>();

        instance.saveDefaultConfig();

        Bukkit.getConsoleSender().sendMessage("§aLe serveur se lance !");

        EventsManager.register(instance);

        //todo guiManager.addMenu(new MenuTutoriel());
    }

    public void unload() {
        Bukkit.getConsoleSender().sendMessage("§cLe serveur se ferme !");
    }

    public static HavenAtm getInstance(){
        return instance;
    }

    public static Managers getManagers(){
        return managers;
    }

    public GuiManager getGuiManager() { return guiManager; }

    public Map<Class<? extends GuiBuilder>, GuiBuilder> getRegisteredMenus() { return registeredMenus; }
}
