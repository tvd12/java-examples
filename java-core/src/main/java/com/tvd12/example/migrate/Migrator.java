package com.tvd12.example.migrate;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class Migrator {

    @Data
    public static class Record {
        private long id;
        // data
    }

    public static void main(String[] args) {
        int limit = 300;
        long lastMigratedId = getLastMigratedId();
        Logger logger = LoggerFactory.getLogger(Migrator.class);
        logger.info("migration start");
        while (true) {
            try {
                logger.info(
                    "get records to migrate, limit: {}, last id: {}",
                    limit,
                    lastMigratedId
                );
                List<Record> records = getRecordsToMigrateFromDatabaseA(
                    lastMigratedId,
                    limit
                );
                logger.info(
                    "done get records to migrate, records.size: {}",
                    records.size()
                );
                saveRecordsToDatabaseB(records);
                logger.info("saved records");
                if (records.size() < limit) {
                    break;
                }
                saveLastMigratedId(records.get(records.size() - 1).getId());
            } catch (Exception e) {
                logger.error("migrate failed", e);
                break;
            }
        }
        logger.info("migration done");
    }

    private static List<Record> getRecordsToMigrateFromDatabaseA(
        long lastMigratedId,
        int limit
    ) {
        return Collections.emptyList();
    }

    private static void saveRecordsToDatabaseB(List<Record> records) {
    }

    private static void saveLastMigratedId(long id) {
    }

    private static long getLastMigratedId() {
        return 0;
    }
}
