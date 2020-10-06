package fr.hephaisto.havenatm.menus;

import fr.hephaisto.havenatm.tools.GuiBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ATMGui implements GuiBuilder {
    @Override
    public String name() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void contents(Player player, Inventory inv) {

    }

    @Override
    public void onClick(Player player, InventoryView inv, ItemStack current, int slot) {

    }
}
