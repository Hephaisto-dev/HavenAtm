package fr.hephaisto.havenatm.listeners;

import fr.hephaisto.havenatm.managers.GuiManager;
import fr.hephaisto.havenatm.managers.Managers;
import fr.hephaisto.havenatm.menus.ATMGui;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayersEvent implements Listener {

    @EventHandler
    public void onClic(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();
            if (block.getType() == Material.INFESTED_STONE) {
                GuiManager gm = Managers.getManagers().getGuiManager();
                gm.open(e.getPlayer(), ATMGui.class);
            }
        }
    }
}
