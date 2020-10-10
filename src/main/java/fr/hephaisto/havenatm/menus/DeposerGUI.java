package fr.hephaisto.havenatm.menus;

import fr.hephaisto.havenatm.managers.Managers;
import fr.hephaisto.havenatm.tools.GuiBuilder;
import fr.hephaisto.havenatm.tools.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class DeposerGUI implements GuiBuilder {
    ItemStack un = new ItemBuilder(Material.DIRT).setName("§a1").toItemStack();
    ItemStack cinq = new ItemBuilder(Material.DIRT).setName("§a5").toItemStack();
    ItemStack dix = new ItemBuilder(Material.DIRT).setName("§a10").toItemStack();
    ItemStack cinquante = new ItemBuilder(Material.DIRT).setName("§a50").toItemStack();
    ItemStack cent = new ItemBuilder(Material.DIRT).setName("§a100").toItemStack();
    @Override
    public String name() {
        return "§aDéposer de l'argent";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        inv.setItem(0,un);
        inv.setItem(2,cinq);
        inv.setItem(4,dix);
        inv.setItem(6,cinquante);
        inv.setItem(8,cent);
    }

    @Override
    public void onClick(Player player, InventoryView inv, ItemStack current, int slot) {
        Managers m = Managers.getManagers();
        if (current.getItemMeta().getDisplayName().equals("§a1") && current.getType() == Material.DIRT){
            m.givemoney(player,1);
        }
        if (current.getItemMeta().getDisplayName().equals("§a5") && current.getType() == Material.DIRT){
            m.givemoney(player,5);
        }
        if (current.getItemMeta().getDisplayName().equals("§a10") && current.getType() == Material.DIRT){
            m.givemoney(player,10);
        }
        if (current.getItemMeta().getDisplayName().equals("§a50") && current.getType() == Material.DIRT){
            m.givemoney(player,50);
        }
        if (current.getItemMeta().getDisplayName().equals("§a100") && current.getType() == Material.DIRT){
            m.givemoney(player,100);
        }
    }
}
