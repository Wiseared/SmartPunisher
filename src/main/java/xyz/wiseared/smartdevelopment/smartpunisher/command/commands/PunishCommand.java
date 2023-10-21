package xyz.wiseared.smartdevelopment.smartpunisher.command.commands;

import lombok.AllArgsConstructor;
import me.vaperion.blade.annotation.argument.Name;
import me.vaperion.blade.annotation.argument.Sender;
import me.vaperion.blade.annotation.command.Command;
import me.vaperion.blade.annotation.command.Description;
import me.vaperion.blade.annotation.command.Permission;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.wiseared.smartdevelopment.smartpunisher.SmartPunisher;
import xyz.wiseared.smartdevelopment.smartpunisher.menu.PunishMenu;
import xyz.wiseared.smartdevelopment.smartpunisher.util.Message;

@AllArgsConstructor
public class PunishCommand extends Message {

    private SmartPunisher plugin;

    @Command("punish")
    @Description("Punish command")
    @Permission("punishgui.punish")
    public final void punishCommand(@Sender CommandSender sender, @Name("target") OfflinePlayer target) {
        if (!(sender instanceof Player)) {
            sendOnlyPlayer(sender);
            return;
        }

        Player player = (Player) sender;

        new PunishMenu(plugin, player, target).updateMenu();
    }
}