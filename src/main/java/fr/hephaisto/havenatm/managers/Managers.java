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

    public Economy getEcon() {
        return economy;
    }

    public void takemoney (Player player, int amount){
        if (setupEconomy()) {
            if (economy.getBalance(player)>amount) {
                if (amount == 100) {
                    player.getInventory().addItem(new ItemStack(Material.DIRT, 64));
                    player.getInventory().addItem(new ItemStack(Material.DIRT, 36));
                } else {
                    player.getInventory().addItem(new ItemStack(Material.DIRT, amount));
                }
                economy.withdrawPlayer(player, amount);
                player.sendMessage("Tu as maintenant " + economy.getBalance(player));
            }
            else{
                player.sendMessage("Vous n'avez pas assez pour effectuer cette transaction");
            }
        }
    }

    public void givemoney (Player player,int amount){
        if (checkHasNumber(player,amount)){
            if (amount == 100){
                player.getInventory().removeItem(new ItemStack(Material.DIRT,64),new ItemStack(Material.DIRT,36));
            }
            else {
                player.getInventory().removeItem(new ItemStack(Material.DIRT,amount));
            }
            economy.depositPlayer(player,amount);
            player.sendMessage("Tu as maintenant "+ economy.getBalance(player));
        }
        else{
            player.sendMessage("Vous n'avez pas assez pour effectuer cette transaction");
        }
    }

    public boolean checkHasNumber (Player player,int amount){
        if(setupEconomy()) {
            int number = 0;
            for (ItemStack i : player.getInventory().getContents()) {
                if (!i.getType().equals(Material.AIR) && i.getType().equals(Material.DIRT)) {
                    number += i.getAmount();
                }
            }
            return number >= amount;
        }
        return false;
    }
}
