package org.fundamental.service.memory;

import org.fundamental.exception.FileNotCreatedException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl {

    public static String createFile(String filename) throws IOException {
        File file = new File(filename);
        if(file.exists()) return filename;
        else {
            if(file.createNewFile()) return filename;
        }
        throw new IOException();
    }

    public static List<String> readFile(String filename) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            List<String> result = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            reader.close();
            return result;
}

    public static void clearFile(String filename) throws IOException {
            FileWriter writer = new FileWriter(filename);
            writer.write("");
            writer.close();
            System.out.println("File content cleared.");
    }

    public static List<String> writeToFile(String filename, String content) throws IOException {
        FileWriter writer = new FileWriter(filename, true);
        writer.write(content + "\n");
        writer.close();
        return readFile(filename);
    }
}
