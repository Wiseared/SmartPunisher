package xyz.wiseared.smartdevelopment.smartpunisher.menu;

import xyz.wiseared.smartdevelopment.smartpunisher.SmartPunisher;
import xyz.wiseared.smartdevelopment.smartpunisher.util.CC;
import xyz.wiseared.smartdevelopment.smartpunisher.util.ItemBuilder;
import xyz.wiseared.smartdevelopment.smartpunisher.util.menu.Menu;
import xyz.wiseared.smartdevelopment.smartpunisher.menu.type.BanMenu;
import xyz.wiseared.smartdevelopment.smartpunisher.menu.type.IPBanMenu;
import xyz.wiseared.smartdevelopment.smartpunisher.menu.type.KickMenu;
import xyz.wiseared.smartdevelopment.smartpunisher.menu.type.MuteMenu;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.wiseared.smartdevelopment.smartpunisher.util.menu.buttons.Button;

import java.util.HashMap;
import java.util.Map;

public class PunishMenu extends Menu {

    private final SmartPunisher plugin;
    private final OfflinePlayer target;

    public PunishMenu(SmartPunisher plugin, Player player, OfflinePlayer target) {
        super(player, CC.translate(plugin.getConfig().getString("PUNISH_MENU.TITLE").replace("%player%", target.getName())),
                plugin.getConfig().getInt("PUNISH_MENU.SIZE") * 9);

        this.plugin = plugin;
        this.target = target;
    }

    @Override
    public Map<Integer, Button> getButtons() {
        final Map<Integer, Button> buttons = new HashMap<>();

        for (String string : plugin.getConfig().getConfigurationSection("PUNISH_MENU.ITEMS").getKeys(false)) {
            buttons.put(plugin.getConfig().getInt("PUNISH_MENU.ITEMS." + string + ".SLOT"),
                    new Button(new ItemBuilder(Material.valueOf(plugin.getConfig().getString("PUNISH_MENU.ITEMS." + string + ".MATERIAL")))
                            .name(CC.translate(plugin.getConfig().getString("PUNISH_MENU.ITEMS." + string + ".DISPLAYNAME")))
                            .data((short) plugin.getConfig().getInt("PUNISH_MENU.ITEMS." + string + ".DATA"))
                            .lore(CC.translate(plugin.getConfig().getStringList("PUNISH_MENU.ITEMS." + string + ".LORE")))
                            .build())
                            .setClickAction(event -> {
                                event.setCancelled(true);
                                player.closeInventory();

                                switch (string) {
                                    case "IPBAN":
                                        new IPBanMenu(plugin, player, target).updateMenu();
                                        break;
                                    case "BAN":
                                        new BanMenu(plugin, player, target).updateMenu();
                                        break;
                                    case "MUTE":
                                        new MuteMenu(plugin, player, target).updateMenu();
                                        break;
                                    case "KICK":
                                        new KickMenu(plugin, player, target).updateMenu();
                                        break;
                                }
                            }));
        }

        return buttons;
    }
}