package com.tvd12.example.common;

import java.io.File;

public class DiskUsage {
    public static void main(String[] args) {
        File file = new File("/");
        long freeSpace = file.getFreeSpace();
        System.out.println("Free space on the disk: " + formatSize(freeSpace));
    }

    private static String formatSize(long bytes) {
        final long kilo = 1024;
        final long mega = kilo * kilo;
        final long giga = mega * kilo;

        if (bytes >= giga) {
            return String.format("%.2f GB", (double) bytes / giga);
        } else if (bytes >= mega) {
            return String.format("%.2f MB", (double) bytes / mega);
        } else if (bytes >= kilo) {
            return String.format("%.2f KB", (double) bytes / kilo);
        } else {
            return bytes + " bytes";
        }
    }
}