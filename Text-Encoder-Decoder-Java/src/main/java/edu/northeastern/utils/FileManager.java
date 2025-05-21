package edu.northeastern.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FileManager {
    private static final String RESOURCE_DIR = "ebook/";
    /*
    * This method reads files in resources/ebook folder and returns a hashmap with the file streams
    */
    public Map<String, InputStream> getFileStream() {
        String[] fileNames = {"book1.txt", "book2.txt", "book3.txt"};
        Map<String, InputStream> fileStreams = new HashMap<>();
        ClassLoader classLoader = FileManager.class.getClassLoader();

        for(String fileName : fileNames) {
            InputStream stream = classLoader.getResourceAsStream(RESOURCE_DIR + fileName);
            if(stream == null) {
                throw new IllegalStateException("File not found: " + fileName);
            }
            fileStreams.put(fileName, stream);
        }
        return fileStreams;
    }

    /*
     * Returns the String content of the passed input Stream (file)
     */
    public String readFile(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
