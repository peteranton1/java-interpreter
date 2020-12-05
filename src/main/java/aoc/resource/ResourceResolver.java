package aoc.resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceResolver {

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    public InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    public List<String> getResourceAsLines(String inputFile) throws Exception {
        List<String> outList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getFileFromResourceAsStream(inputFile)))) {
            String line;
            while((line = br.readLine()) != null) {
                outList.add(line);
            }
        }
        return outList;
    }
}
