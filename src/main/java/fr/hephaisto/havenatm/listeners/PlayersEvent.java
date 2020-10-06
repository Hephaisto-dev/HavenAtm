package fr.hephaisto.havenatm.listeners;

import fr.hephaisto.havenatm.managers.GuiManager;
import fr.hephaisto.havenatm.managers.Managers;
import fr.hephaisto.havenatm.tools.Vault;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayersEvent implements Listener {

    @EventHandler
    public void  onClic(PlayerInteractEvent e){
        Player player = e.getPlayer()
        Block b = e.getClickedBlock();
        Material m = b.getType();
        if (m==Material.DIRT){
            GuiManager gm = Managers.getManagers().getGuiManager();
            gm.open(e.getPlayer(),);
        }
    }

    on right click on dirt:

    open chest with 1 rows named " &aATM" to player

    wait 1 tick

    format slot 3 of player with chest named "&aRetirer de l'argent" to run [make player execute command "money take"]

    format slot 5 of player with chest named "&aDéposer de l'argent" to run [make player execute command "money deposit"]

    format slot 4 of player with book named "&aDétails" with lore "&9Vous avez&7: &6%{money.%player%}% billets" to be unstealable

    command /money [<text>] [<text>] [<text>] [<text>]:

    trigger:

            if arg 1 is "take":

            if arg 2 is not set:

    open chest with 1 rows named " &aRetirer de l'argent" to player

    wait 1 tick

    format slot 0 of player with dirt named "&a1 billet" to run [make player execute command "money take 1"]

    format slot 2 of player with dirt named "&a5 billet" to run [make player execute command "money take 5"]

    format slot 4 of player with dirt named "&a10 billet" to run [make player execute command "money take 10"]

    format slot 6 of player with dirt named "&a50 billet" to run [make player execute command "money take 50"]

    format slot 8 of player with dirt named "&a100 billet" to run [make player execute command "money take 100"]

            if arg 2 is set:

    set {_number} to arg 2

    set {money.%player%} to {money.%player%} parsed as number

if {money.%player%} is 0:

    message "Tu as %{money.%player%}% billets !"

            else:

            if arg 2 is "1":

            if {money.%player%} is greater than 0:

    add -1 to {money.%player%}

    give 1 dirt to player

else:

    message "Tu as seulement %{money.%player%}% sur ton compte bancaire"

            if arg 2 is "5":

            if {money.%player%} is greater than 4:

    add -5 to {money.%player%}

    give 5 dirt to player

else:

    message "Tu as seulement %{money.%player%}% sur ton compte bancaire"

            if arg 2 is "10":

            if {money.%player%} is greater than 9:

    add -10 to {money.%player%}

    give 10 dirt to player

else:

    message "Tu as seulement %{money.%player%}% sur ton compte bancaire"

            if arg 2 is "50":

            if {money.%player%} is greater than 49:

    add -50 to {money.%player%}

    give 50 dirt to player

else:

    message "Tu as seulement %{money.%player%}% sur ton compte bancaire"

            if arg 2 is "100":

            if {money.%player%} is greater than 99:

    add -100 to {money.%player%}

    give 100 dirt to player

else:

    message "Tu as seulement %{money.%player%}% sur ton compte bancaire"

            if arg 1 is "deposit":

            if arg 2 is not set:

    open chest with 1 rows named " &aDéposer de l'argent" to player

    wait 1 tick

    format slot 0 of player with dirt named "&a1 billet" to run [make player execute command "money deposit 1"]

    format slot 2 of player with dirt named "&a5 billet" to run [make player execute command "money deposit 5"]

    format slot 4 of player with dirt named "&a10 billet" to run [make player execute command "money deposit 10"]

    format slot 6 of player with dirt named "&a50 billet" to run [make player execute command "money deposit 50"]

    format slot 8 of player with dirt named "&a100 billet" to run [make player execute command "money deposit 100"]

            if arg 2 is set:

            if arg 2 is "1":

            if player has 1 dirt:

    remove 1 dirt from player

    add -1 to {money.%player%}

else:

    message "Tu n'as de billet de 1"

            if arg 2 is "5":

            if player has 5 dirt:

    remove 5 dirt from player

    add 5 to {money.%player%}

else:

    message "Tu n'as de billet de 5"

            if arg 2 is "10":

            if player has 10 dirt:

    remove 10 dirt from player

    add 10 to {money.%player%}

else:

    message "Tu n'as de billet de 10"

            if arg 2 is "50":

            if player has 50 dirt:

    remove 50 dirt from player

    add 50 to {money.%player%}

else:

    message "Tu n'as de billet de 50"

            if arg 2 is "100":

            if player has 100 dirt:

    remove 100 dirt from player

    add 100 to {money.%player%}

else:

    message "Tu n'as de billet de 100"

            if arg 1 is "add":

    set {money.%player%} to arg 2


}
