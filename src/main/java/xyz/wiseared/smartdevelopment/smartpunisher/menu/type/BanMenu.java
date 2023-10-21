package xyz.wiseared.smartdevelopment.smartpunisher.menu.type;

import xyz.wiseared.smartdevelopment.smartpunisher.SmartPunisher;
import xyz.wiseared.smartdevelopment.smartpunisher.api.PunishAPI;
import xyz.wiseared.smartdevelopment.smartpunisher.util.CC;
import xyz.wiseared.smartdevelopment.smartpunisher.util.ItemBuilder;
import xyz.wiseared.smartdevelopment.smartpunisher.util.menu.Menu;
import xyz.wiseared.smartdevelopment.smartpunisher.util.menu.buttons.Button;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class BanMenu extends Menu {

    private final SmartPunisher plugin;
    private final OfflinePlayer target;

    public BanMenu(SmartPunisher plugin, Player player, OfflinePlayer target) {
        super(player, CC.translate(plugin.getConfig().getString("BAN_MENU.TITLE")
                .replace("%player%", target.getName())), plugin.getConfig().getInt("BAN_MENU.SIZE") * 9);

        this.plugin = plugin;
        this.target = target;
    }

    @Override
    public Map<Integer, Button> getButtons() {
        final Map<Integer, Button> buttons = new HashMap<>();

        for (String string : plugin.getConfig().getConfigurationSection("BAN_MENU.ITEMS").getKeys(false)) {

            int offence;
            try {
                offence = PunishAPI.getBanCount(target, plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".DEFAULT.REASON"));
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (plugin.getConfig().get("BAN_MENU.ITEMS." + string + ".OFFENCES." + offence) == null) {
                ItemBuilder item = new ItemBuilder(Material.valueOf(plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".DEFAULT.MATERIAL")));

                item.name(CC.translate(plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".DEFAULT.DISPLAYNAME")));

                List<String> loreList = new ArrayList<>();
                for (String lore : plugin.getConfig().getStringList("BAN_MENU.ITEMS." + string + ".DEFAULT.LORE")) {
                    loreList.add(lore
                            .replace("%reason%", plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".DEFAULT.REASON"))
                            .replace("%command%", plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".DEFAULT.COMMAND"))
                            .replace("%player%", target.getName()));
                }
                item.lore(CC.translate(loreList));

                item.data((short) plugin.getConfig().getInt("BAN_MENU.ITEMS." + string + ".DEFAULT.DATA"));

                buttons.put(plugin.getConfig().getInt("BAN_MENU.ITEMS." + string + ".DEFAULT.SLOT"),
                        new Button(item.build())
                                .setClickAction(event -> {
                                    event.setCancelled(true);
                                    player.closeInventory();

                                    player.performCommand(plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".DEFAULT.COMMAND")
                                            .replace("%player%", target.getName()));
                                }));
            } else {
                ItemBuilder item = new ItemBuilder(Material.valueOf(plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".OFFENCES." + offence + ".MATERIAL")));

                item.name(CC.translate(plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".OFFENCES." + offence + ".DISPLAYNAME")));

                List<String> loreList = new ArrayList<>();
                for (String lore : plugin.getConfig().getStringList("BAN_MENU.ITEMS." + string + ".OFFENCES." + offence + ".LORE")) {
                    loreList.add(lore
                            .replace("%reason%", plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".OFFENCES." + offence + ".REASON"))
                            .replace("%command%", plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".OFFENCES." + offence + ".COMMAND"))
                            .replace("%player%", target.getName()));
                }
                item.lore(CC.translate(loreList));

                item.data((short) plugin.getConfig().getInt("BAN_MENU.ITEMS." + string + ".OFFENCES." + offence + ".DATA"));

                buttons.put(plugin.getConfig().getInt("BAN_MENU.ITEMS." + string + ".DEFAULT.SLOT"),
                        new Button(item.build())
                                .setClickAction(event -> {
                                    event.setCancelled(true);
                                    player.closeInventory();

                                    player.performCommand(plugin.getConfig().getString("BAN_MENU.ITEMS." + string + ".OFFENCES." + offence + ".COMMAND")
                                            .replace("%player%", target.getName()));
                                }));
            }
        }

        return buttons;
    }
}