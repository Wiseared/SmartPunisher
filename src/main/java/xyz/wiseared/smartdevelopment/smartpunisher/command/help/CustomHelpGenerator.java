package xyz.wiseared.smartdevelopment.smartpunisher.command.help;

import me.vaperion.blade.bukkit.command.BukkitUsageMessage;
import me.vaperion.blade.command.Command;
import me.vaperion.blade.context.Context;
import me.vaperion.blade.platform.HelpGenerator;
import me.vaperion.blade.util.PaginatedOutput;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import xyz.wiseared.smartdevelopment.smartpunisher.util.CC;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomHelpGenerator implements HelpGenerator {

    @NotNull
    @Override
    public List<String> generate(@NotNull Context context, @NotNull List<Command> commands) {
        commands = commands.stream().distinct().filter(c -> !c.isHidden()).collect(Collectors.toList());

        int originalCount = commands.size();
        commands = commands.stream().filter(c -> context.blade().getPermissionTester().testPermission(context, c))
                .collect(Collectors.toList());

        if (originalCount != 0 && commands.size() == 0) {
            return Collections.singletonList(ChatColor.RED + context.blade().getConfiguration().getDefaultPermissionMessage());
        }

        return new PaginatedOutput<Command>(10) {
            @Override
            public String formatErrorMessage(Error error, Object... args) {
                switch (error) {
                    case NO_RESULTS:
                        return ChatColor.RED + "No results found.";
                    case PAGE_OUT_OF_BOUNDS:
                        return ChatColor.RED + String.format("Page %d does not exist, valid range is 1 to %d.", args);
                }
                return null;
            }

            @Override
            public String getHeader(int page, int totalPages) {
                return CC.translate(CC.DARK_LINE);
            }

            @Override
            public String getFooter(int page, int totalPages) {
                return CC.translate("&8&m-------------- &a"+ page + "&7/&c" + totalPages  + "&8&m-------------");
            }

            @Override
            public String formatLine(Command result, int index) {
                String help = ChatColor.stripColor(result.getHelpMessage().ensureGetOrLoad(() -> new BukkitUsageMessage(result, false)).toString());
                return ChatColor.RED + help + (result.getDescription().isEmpty() ? "" : (ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + result.getDescription()));
            }
        }.generatePage(commands, parsePage(context.argument(0)));
    }

    private int parsePage(String argument) {
        if (argument == null) return 1;
        try {
            return Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}