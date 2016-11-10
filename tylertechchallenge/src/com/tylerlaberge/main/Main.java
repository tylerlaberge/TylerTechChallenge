package com.tylerlaberge.main;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String input_file_path = args[0];
        String output_file_path = args[1];
        try (
                BufferedReader reader = new BufferedReader(new FileReader(input_file_path));
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_file_path), "utf-8"));
        ) {
            String line = reader.readLine();
            while(line !=null)
            {
                writer.write(line);
                line = reader.readLine();
            }
        }
    }
}
