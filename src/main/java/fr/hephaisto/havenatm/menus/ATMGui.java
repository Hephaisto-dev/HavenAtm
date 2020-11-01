package fr.hephaisto.havenatm.menus;

import fr.hephaisto.havenatm.managers.Managers;
import fr.hephaisto.havenatm.tools.GuiBuilder;
import fr.hephaisto.havenatm.tools.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ATMGui implements GuiBuilder {
    @Override
    public String name() {
        return "§aATM";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        ItemStack deposer = new ItemBuilder(Material.CHEST).setName("§aDéposer de l'argent").toItemStack();
        ItemStack retirer = new ItemBuilder(Material.CHEST).setName("§aRetirer de l'argent").toItemStack();
        ItemStack tout = new ItemBuilder(Material.BARRIER).setName("§aTout déposer").toItemStack();
        inv.setItem(2,tout);
        inv.setItem(3,deposer);
        inv.setItem(5,retirer);
        }

    @Override
    public void onClick(Player player, InventoryView inv, ItemStack current, int slot) {
        if (current.getItemMeta().getDisplayName().equals("§aDéposer de l'argent") && current.getType() == Material.CHEST){
            Managers.getManagers().getGuiManager().open(player, DeposerGUI.class);
        }
        if (current.getItemMeta().getDisplayName().equals("§aRetirer de l'argent") && current.getType() == Material.CHEST){
            Managers.getManagers().getGuiManager().open(player, RetirerGUI.class);
        }
        if (current.getItemMeta().getDisplayName().equals("§aTout déposer") && current.getType() == Material.BARRIER){
            Managers.getManagers().deposertout(player);
        }
    }
}
