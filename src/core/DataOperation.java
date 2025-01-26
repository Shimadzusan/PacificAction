package core;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class DataOperation {

    public String readDataFromFile(String fileName) throws FileNotFoundException, IOException {
        String result = "";
        String line;
        // defaultCharBufferSize = 8192; or 8k
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            while ((line = br.readLine()) != null) {
                result = result + line + "\n";
            }
        }
        return result;
    }

    public boolean writeDataToFile(String fileName, String text) throws IOException {
//		try (FileWriter fw = new FileWriter(fileName);
//			       BufferedWriter bw = new BufferedWriter(fw)) {
//			      bw.write(text);
//			      bw.newLine(); // add new line, System.lineSeparator()
//			  }

        // append mode
        try (FileWriter fw = new FileWriter(fileName, false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(text);
            bw.newLine();
        }
        return true;
    }

    public String getFileAttributs(String fileAddress) {
        // Replace with the path to your file
//        String filePath = "path/to/your/file.txt";

        Path path = Paths.get(fileAddress);
        String s = "";
        try {
            // Get the basic file attributes
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            // Get the last modified time
            long lastModifiedTime = attrs.lastModifiedTime().toMillis();

            // Convert to a readable format
            java.util.Date date = new java.util.Date(lastModifiedTime);
//            System.out.println("Last modified date of the file: " + date);
            s = date.toString();
        } catch (IOException e) {
            System.err.println("Error reading file attributes: " + e.getMessage());
        }
    return s;
    }

}