package core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class Converter_3 {
    static int runLogicCounter = 0;
    static DataOperation dataOperation = new DataOperation();
    static String fileName = "autosave.hoi4";
    //    static Path filePath = Paths.get("C:\\Users\\user\\Documents\\Paradox Interactive\\Hearts of Iron IV\\save games\\" + fileName); // Change this to your file's path
    static Path filePath = Paths.get("D:\\projectAlpha\\" + fileName);

    public static void main(String[] args) throws InterruptedException, IOException {
        try {
            List<String> lines = Files.lines(filePath).collect(Collectors.toList());
            String result = String.join(System.lineSeparator(), lines);
            result = result.replaceAll("\t", "?");
            String[] arrayData = result.split("\n");
            for (int i = 0; i < arrayData.length; i++) {
                if(!arrayData[i].contains("?") && !arrayData[i].contains("\"") && !arrayData[i].contains(" ")) {
                    System.out.println(arrayData[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


