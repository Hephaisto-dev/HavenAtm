package fr.hephaisto.havenatm.managers;

import fr.hephaisto.havenatm.HavenAtm;
import fr.hephaisto.havenatm.menus.ATMGui;
import fr.hephaisto.havenatm.menus.DeposerGUI;
import fr.hephaisto.havenatm.menus.RetirerGUI;
import fr.hephaisto.havenatm.tools.GuiBuilder;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Managers {
    private static HavenAtm instance;
    private static Managers managers;
    public static Economy economy = null;
    private GuiManager guiManager;

    private Map<Class<? extends GuiBuilder>, GuiBuilder> registeredMenus;

    public void load(HavenAtm instance) {
        Managers.instance = instance;
        Managers.managers = this;
        this.guiManager = new GuiManager();

        registeredMenus = new HashMap<>();

        Bukkit.getConsoleSender().sendMessage("§aLe plugin Haven Atm se lance !");

        EventsManager.register(instance);

        setupEconomy();

        guiManager.addMenu(new ATMGui());
        guiManager.addMenu(new DeposerGUI());
        guiManager.addMenu(new RetirerGUI());
    }

    public void unload() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Le plugin Haven Atm se ferme !");
    }

    public static HavenAtm getInstance(){
        return instance;
    }

    public static Managers getManagers(){
        return managers;
    }

    public GuiManager getGuiManager() { return guiManager; }

    public Map<Class<? extends GuiBuilder>, GuiBuilder> getRegisteredMenus() { return registeredMenus; }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = instance.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public void takemoney (Player player, int amount,Material material){
        if (setupEconomy()) {
            if (economy.getBalance(player)>amount) {
                player.getInventory().addItem(new ItemStack(material));
                economy.withdrawPlayer(player, amount);
                player.sendMessage("§e§l[HavenBank]§l§e §2Transfert effectué");
            }
            else{
                player.sendMessage("§e§l[HavenBank]§l§e §4Vous n'avez pas assez pour effectuer cette transaction");
            }
        }
    }

    public void givemoney (Player player,int amount, Material material){
        if (setupEconomy()) {
            if (player.getInventory().contains(material)) {
                player.getInventory().removeItem(new ItemStack(material));
                economy.depositPlayer(player, amount);
                player.sendMessage("§e§l[HavenBank]§l§e §2Transfert effectué");
            } else {
                player.sendMessage("§e§l[HavenBank]§l§e §4Vous n'avez pas assez pour effectuer cette transaction");
            }
        }
    }
}
