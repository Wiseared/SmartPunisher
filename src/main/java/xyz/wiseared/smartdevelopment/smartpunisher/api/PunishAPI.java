package xyz.wiseared.smartdevelopment.smartpunisher.api;

import litebans.api.Database;
import org.bukkit.OfflinePlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PunishAPI {

    public static int getIPBanCount(OfflinePlayer player, String banReason) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            int count = 1;
            String uuid = player.getUniqueId().toString();
            String query = "SELECT * FROM {bans} WHERE uuid=?";
            try (PreparedStatement st = Database.get().prepareStatement(query)) {
                st.setString(1, uuid);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        String reason = rs.getString("reason");
                        boolean ipban = rs.getBoolean("ipban");
                        if (ipban && reason.equalsIgnoreCase(banReason)) {
                            count++;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return count;
        });

        new Thread(task).start();

        return task.get();
    }

    public static int getKickCount(OfflinePlayer player, String banReason) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            int count = 1;
            String uuid = player.getUniqueId().toString();
            String query = "SELECT * FROM {kicks} WHERE uuid=?";
            try (PreparedStatement st = Database.get().prepareStatement(query)) {
                st.setString(1, uuid);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        String reason = rs.getString("reason");
                        if (reason.equalsIgnoreCase(banReason)) {
                            count++;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return count;
        });

        new Thread(task).start();

        return task.get();
    }

    public static int getMuteCount(OfflinePlayer player, String banReason) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            int count = 1;
            String uuid = player.getUniqueId().toString();
            String query = "SELECT * FROM {mutes} WHERE uuid=?";
            try (PreparedStatement st = Database.get().prepareStatement(query)) {
                st.setString(1, uuid);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        String reason = rs.getString("reason");
                        if (reason.equalsIgnoreCase(banReason)) {
                            count++;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return count;
        });

        new Thread(task).start();

        return task.get();
    }

    public static int getBanCount(OfflinePlayer player, String banReason) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            int count = 1;
            String uuid = player.getUniqueId().toString();
            String query = "SELECT * FROM {bans} WHERE uuid=?";
            try (PreparedStatement st = Database.get().prepareStatement(query)) {
                st.setString(1, uuid);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        String reason = rs.getString("reason");
                        boolean ipban = rs.getBoolean("ipban");
                        if (!ipban && reason.equalsIgnoreCase(banReason)) {
                            count++;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return count;
        });

        new Thread(task).start();

        return task.get();
    }
}