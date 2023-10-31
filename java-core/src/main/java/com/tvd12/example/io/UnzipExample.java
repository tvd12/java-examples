package com.tvd12.example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipExample {
    public static void main(String[] args) {
        String zipFilePath = "hack.zip"; // Specify the path to the zip file
        String outputFolderPath = "."; // Specify the path to the folder where you want to unzip the files

        try {
            File outputFolder = new File(outputFolderPath);
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }

            FileInputStream fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);

            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                File entryFile = new File(outputFolderPath, entryName);

                if (zipEntry.isDirectory()) {
                    // Create directory if it doesn't exist
                    entryFile.mkdirs();
                } else {
                    // Create parent directories for the file
                    File parent = entryFile.getParentFile();
                    if (parent != null) {
                        parent.mkdirs();
                    }

                    // Write the file
                    FileOutputStream fos = new FileOutputStream(entryFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fos.close();
                }

                zis.closeEntry();
            }

            zis.close();
            fis.close();

            System.out.println("Zip file successfully extracted to " + outputFolderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
