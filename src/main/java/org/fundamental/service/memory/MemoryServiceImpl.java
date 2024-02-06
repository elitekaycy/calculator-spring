package org.fundamental.service.memory;

import java.io.IOException;
import java.util.List;

public class Memory {
    private static double memory = 0;
    private static String MEMORY_CURRENT = "";
    private static final String MEMORY_NAME = "calculator";

    public double getMemory() {
        return memory;
    }
    public void setMemory(double newMemory) {
        memory = newMemory;
    }
    public void setMemoryCurrent(String content) {
        MEMORY_CURRENT = content;
    }

    public String getMemoryCurrent() {
        return MEMORY_CURRENT;
    }
    public List<String> getHistory() throws IOException {
        return FileServiceImpl.readFile(MEMORY_NAME);
    }

    public List<String> addToHistory(String content) throws IOException {
        return FileServiceImpl.writeToFile(MEMORY_NAME, content);
    }
    public void clearMemory() throws IOException {
        FileServiceImpl.clearFile(MEMORY_NAME);
    }

}
