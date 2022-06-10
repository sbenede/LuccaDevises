package com.lucca.devise.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class FileUtilsTest {

    @Test
    void parseFileTest() throws IOException {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("exchanges.txt");
        if (in != null) {
            File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            tempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                //copy stream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            // test file content size
            List<String> lines = FileUtils.parseFile(tempFile);
            Assertions.assertEquals(8, lines.size());
        }

        // Catched exception will return empty list
        List<String> lines = FileUtils.parseFile(new File(""));
        Assertions.assertEquals(0, lines.size());
    }
}
