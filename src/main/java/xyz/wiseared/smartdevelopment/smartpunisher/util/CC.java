package xyz.wiseared.smartdevelopment.smartpunisher.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CC {

    public static final String LINE = translate("&7&m---------------------------");
    public static final String DARK_LINE = translate("&8&m---------------------------");

    public static String translate(final String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static List<String> translate(final List<String> lines) {
        final List<String> toReturn = new ArrayList<>();
        for (final String line : lines) {
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return toReturn;
    }

    public static void console(String msg) {
        Bukkit.getConsoleSender().sendMessage(translate(msg));
    }
}