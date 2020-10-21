package de.hskempten.tabulang.tokenizer;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Utils {

    public static String getFileContent(String filename)
            throws IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));
        StringBuilder s = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            s.append(line).append("\n");
            line = reader.readLine();
        }
        return s.toString();
    }


}
