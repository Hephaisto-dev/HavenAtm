package fr.hephaisto.havenatm.menus;

import fr.hephaisto.havenatm.managers.Managers;
import fr.hephaisto.havenatm.tools.GuiBuilder;
import fr.hephaisto.havenatm.tools.ItemBuilder;
import fr.hephaisto.havenatm.tools.Vault;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ATMGui implements GuiBuilder {
    @Override
    public String name() {
        return "§ATM";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        ItemStack deposer = new ItemBuilder(Material.CHEST).setName("§aDéposer de l'argent").toItemStack();
        ItemStack retirer = new ItemBuilder(Material.CHEST).setName("§aRetirer de l'argent").toItemStack();
        ItemStack details = new ItemBuilder(Material.BOOK).setName("§aDétails").setLore("§9Vous avez&7: &6 "+ Vault.getVault().getEcon().getBalance(player) +" billets").toItemStack();
        inv.setItem(2,deposer);
        inv.setItem(4,retirer);
        inv.setItem(6,details);
    }

    @Override
    public void onClick(Player player, InventoryView inv, ItemStack current, int slot) {
        if (current.getItemMeta().getDisplayName().equals("§aDéposer de l'argent") && current.getType() == Material.CHEST){
            Managers.getManagers().getGuiManager().open(player, DeposerGUI.class);
        }
        if (current.getItemMeta().getDisplayName().equals("§aRetirer de l'argent") && current.getType() == Material.CHEST){
            Managers.getManagers().getGuiManager().open(player, RetirerGUI.class);
        }
    }
}
