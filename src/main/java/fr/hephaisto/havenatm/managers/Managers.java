package fr.hephaisto.havenatm.managers;

import fr.hephaisto.havenatm.HavenAtm;
import fr.hephaisto.havenatm.menus.ATMGui;
import fr.hephaisto.havenatm.menus.DeposerGUI;
import fr.hephaisto.havenatm.menus.RetirerGUI;
import fr.hephaisto.havenatm.tools.GuiBuilder;
import fr.hephaisto.havenatm.tools.Vault;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

        Bukkit.getConsoleSender().sendMessage("§aLe plugin Haven Atm se lance !");

        EventsManager.register(instance);

        guiManager.addMenu(new ATMGui());
        guiManager.addMenu(new DeposerGUI());
        guiManager.addMenu(new RetirerGUI());
    }

    public void unload() {
        Bukkit.getConsoleSender().sendMessage("§aLe plugin Haven Atm se ferme !");
    }

    public static HavenAtm getInstance(){
        return instance;
    }

    public static Managers getManagers(){
        return managers;
    }

    public GuiManager getGuiManager() { return guiManager; }

    public Map<Class<? extends GuiBuilder>, GuiBuilder> getRegisteredMenus() { return registeredMenus; }

    public void takemoney (Player player,int amount){
        if(amount == 100){
            player.getInventory().addItem(new ItemStack(Material.DIRT, 64));
            player.getInventory().addItem(new ItemStack(Material.DIRT, 36));
        }
        else {
            player.getInventory().addItem(new ItemStack(Material.DIRT, amount));
        }
        Vault.withdraw(player,amount);
        player.sendMessage("Tu as maintenant "+Vault.getVault().getEcon().getBalance(player));
    }

    public void givemoney (Player player,int amount){
        if (player.getInventory().getItemInMainHand().getType().equals(Material.DIRT) && player.getInventory().getItemInMainHand().getAmount() >= amount){
            if (amount == 100){
                player.getInventory().removeItem(new ItemStack(Material.DIRT,64),new ItemStack(Material.DIRT,36));
            }
            else{
                player.getInventory().remove(new ItemStack(Material.DIRT,amount));
            }
            Vault.deposit(player,amount);
            player.sendMessage("Tu as maintenant "+Vault.getVault().getEcon().getBalance(player));
        }
    }
}
