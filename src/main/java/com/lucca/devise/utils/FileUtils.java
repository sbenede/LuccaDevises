package com.lucca.devise.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    /**
     * Parse file to string list
     *
     * @param file file to parse
     * @return list of string lines
     */
    public static List<String> parseFile(File file) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader b = new BufferedReader(new FileReader(file))) {
            String readLine;

            while ((readLine = b.readLine()) != null) {
                lines.add(readLine);
            }
        } catch (IOException e) {
            System.err.println("Impossible de lire le fichier");
        }

        return lines;
    }
}
