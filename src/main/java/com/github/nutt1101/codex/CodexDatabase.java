package com.github.nutt1101.codex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CodexDatabase {

    private final ExecutorService worker = Executors.newSingleThreadExecutor(runnable -> {
        Thread thread = new Thread(runnable, "CatchBall-Codex");
        thread.setDaemon(true);
        return thread;
    });

    private final Logger logger;
    private final String url;
    private final String username;
    private final String password;
    private final String table;

    private Connection connection;

    public CodexDatabase(Logger logger, String url, String username, String password, String table) {
        this.logger = logger;
        this.url = url;
        this.username = username;
        this.password = password;
        this.table = table;
    }

    public void initialise() {
        worker.execute(() -> {
            try {
                run(connection -> {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute("CREATE TABLE IF NOT EXISTS `" + table + "` ("
                                + "`uuid` CHAR(36) NOT NULL,"
                                + "`entry_id` VARCHAR(96) NOT NULL,"
                                + "`unlocked_at` BIGINT NOT NULL,"
                                + "`caught` INT NOT NULL DEFAULT 0,"
                                + "PRIMARY KEY (`uuid`, `entry_id`)"
                                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
                    }
                });
            } catch (SQLException exception) {
                logger.log(Level.SEVERE, "Failed to create the codex table.", exception);
            }
        });
    }

    public void load(UUID uuid, Consumer<Map<String, CodexEntry>> callback) {
        worker.execute(() -> {
            Map<String, CodexEntry> unlocked = new HashMap<>();
            try {
                run(connection -> {
                    unlocked.clear();
                    try (PreparedStatement statement = connection.prepareStatement(
                            "SELECT `entry_id`, `unlocked_at`, `caught` FROM `" + table + "` WHERE `uuid`=?")) {
                        statement.setString(1, uuid.toString());
                        try (ResultSet results = statement.executeQuery()) {
                            while (results.next()) {
                                unlocked.put(results.getString(1),
                                        new CodexEntry(results.getLong(2), results.getInt(3)));
                            }
                        }
                    }
                });
            } catch (SQLException exception) {
                logger.log(Level.WARNING, "Failed to load the codex for " + uuid + ".", exception);
            }
            callback.accept(unlocked);
        });
    }

    public void record(UUID uuid, String entryId) {
        worker.execute(() -> {
            try {
                run(connection -> {
                    try (PreparedStatement statement = connection.prepareStatement(
                            "INSERT INTO `" + table + "` (`uuid`, `entry_id`, `unlocked_at`, `caught`)"
                                    + " VALUES (?, ?, ?, 1) ON DUPLICATE KEY UPDATE `caught` = `caught` + 1")) {
                        statement.setString(1, uuid.toString());
                        statement.setString(2, entryId);
                        statement.setLong(3, System.currentTimeMillis());
                        statement.executeUpdate();
                    }
                });
            } catch (SQLException exception) {
                logger.log(Level.WARNING, "Failed to persist codex catch " + entryId + " for " + uuid + ".", exception);
            }
        });
    }

    public void unlockAll(UUID uuid, Collection<String> entryIds) {
        if (entryIds.isEmpty()) {
            return;
        }
        worker.execute(() -> {
            try {
                run(connection -> {
                    try (PreparedStatement statement = connection.prepareStatement(
                            "INSERT IGNORE INTO `" + table + "` (`uuid`, `entry_id`, `unlocked_at`, `caught`)"
                                    + " VALUES (?, ?, ?, 0)")) {
                        long now = System.currentTimeMillis();
                        for (String entryId : entryIds) {
                            statement.setString(1, uuid.toString());
                            statement.setString(2, entryId);
                            statement.setLong(3, now);
                            statement.addBatch();
                        }
                        statement.executeBatch();
                    }
                });
            } catch (SQLException exception) {
                logger.log(Level.WARNING, "Failed to unlock the codex for " + uuid + ".", exception);
            }
        });
    }

    public void clear(UUID uuid) {
        worker.execute(() -> {
            try {
                run(connection -> {
                    try (PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM `" + table + "` WHERE `uuid`=?")) {
                        statement.setString(1, uuid.toString());
                        statement.executeUpdate();
                    }
                });
            } catch (SQLException exception) {
                logger.log(Level.WARNING, "Failed to clear the codex for " + uuid + ".", exception);
            }
        });
    }

    public void close() {
        worker.shutdown();
        try {
            if (!worker.awaitTermination(10, TimeUnit.SECONDS)) {
                worker.shutdownNow();
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        closeQuietly();
    }

    private void run(SqlTask task) throws SQLException {
        try {
            task.execute(connection());
        } catch (SQLException first) {
            closeQuietly();
            task.execute(connection());
        }
    }

    private Connection connection() throws SQLException {
        if (connection == null || !connection.isValid(2)) {
            closeQuietly();
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    private void closeQuietly() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException ignored) {
        }
        connection = null;
    }

    private interface SqlTask {

        void execute(Connection connection) throws SQLException;
    }
}
