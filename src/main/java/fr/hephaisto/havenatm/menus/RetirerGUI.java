package fr.hephaisto.havenatm.menus;

import fr.hephaisto.havenatm.managers.Managers;
import fr.hephaisto.havenatm.tools.GuiBuilder;
import fr.hephaisto.havenatm.tools.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class RetirerGUI implements GuiBuilder {
    ItemStack un = new ItemBuilder(Material.ORANGE_DYE).setName("§a5").toItemStack();
    ItemStack cinq = new ItemBuilder(Material.GREEN_DYE).setName("§a10").toItemStack();
    ItemStack dix = new ItemBuilder(Material.BROWN_DYE).setName("§a20").toItemStack();
    ItemStack cinquante = new ItemBuilder(Material.PURPLE_DYE).setName("§a50").toItemStack();
    ItemStack cent = new ItemBuilder(Material.CYAN_DYE).setName("§a100").toItemStack();
    ItemStack cinquecent = new ItemBuilder(Material.YELLOW_DYE).setName("§a500").toItemStack();
    @Override
    public String name() {
        return "§aRetirer de l'argent";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        inv.setItem(0,un);
        inv.setItem(1,cinq);
        inv.setItem(3,dix);
        inv.setItem(5,cinquante);
        inv.setItem(7,cent);
        inv.setItem(8,cinquecent);
    }

    @Override
    public void onClick(Player player, InventoryView inv, ItemStack current, int slot) {
        Managers m = Managers.getManagers();
        if (current.getItemMeta().getDisplayName().equals("§a5") && current.getType() == Material.ORANGE_DYE){
            m.takemoney(player,5,Material.ORANGE_DYE);
        }
        if (current.getItemMeta().getDisplayName().equals("§a10") && current.getType() == Material.GREEN_DYE){
            m.takemoney(player,10,Material.GREEN_DYE);
        }
        if (current.getItemMeta().getDisplayName().equals("§a20") && current.getType() == Material.BROWN_DYE){
            m.takemoney(player,20,Material.BROWN_DYE);
        }
        if (current.getItemMeta().getDisplayName().equals("§a50") && current.getType() == Material.PURPLE_DYE){
            m.takemoney(player,50,Material.PURPLE_DYE);
        }
        if (current.getItemMeta().getDisplayName().equals("§a100") && current.getType() == Material.CYAN_DYE){
            m.takemoney(player,100,Material.CYAN_DYE);
        }
        if (current.getItemMeta().getDisplayName().equals("§a500") && current.getType() == Material.YELLOW_DYE){
            m.takemoney(player,500,Material.YELLOW_DYE);
        }
    }
}
