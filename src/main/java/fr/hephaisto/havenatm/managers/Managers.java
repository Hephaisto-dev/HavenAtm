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

    public void givemoney (Player player,int amount, Material material,int number){
        if (setupEconomy()) {
            if (player.getInventory().contains(material)) {
                player.getInventory().removeItem(new ItemStack(material,number));
                economy.depositPlayer(player, amount*number);
                player.sendMessage("§e§l[HavenBank]§l§e §2Transfert effectué");
            } else {
                player.sendMessage("§e§l[HavenBank]§l§e §4Vous n'avez pas assez pour effectuer cette transaction");
            }
        }
    }

    public void givemoney(Player player,int amount,Material material){
        givemoney(player,amount,material,1);
    }

    public void deposertout(Player player){
        if (setupEconomy()){
            Material[] materials = {Material.DIRT,Material.STONE,Material.DIORITE,Material.COBBLESTONE,Material.OBSIDIAN};
            if (player.getInventory().contains(materials[0]) || player.getInventory().contains(materials[1]) ||player.getInventory().contains(materials[2]) ||player.getInventory().contains(materials[3]) ||player.getInventory().contains(materials[4])) {
                for (ItemStack stack : player.getInventory().getContents()) {
                    for (Material material : materials) {
                        if (stack==null){
                            continue;
                        }
                        else if (stack.getType() == material) {
                            if (material == Material.DIRT) {
                                givemoney(player, 1, material, stack.getAmount());
                            }
                            if (material == Material.STONE) {
                                givemoney(player, 5, material, stack.getAmount());
                            }
                            if (material == Material.DIORITE) {
                                givemoney(player, 10, material, stack.getAmount());
                            }
                            if (material == Material.COBBLESTONE) {
                                givemoney(player, 50, material, stack.getAmount());
                            }
                            if (material == Material.OBSIDIAN) {
                                givemoney(player, 100, material, stack.getAmount());
                            }
                        }
                    }
                }
            }
        }
    }
}
