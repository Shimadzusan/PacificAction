package core;

import util.DataOperation;

import java.io.IOException;

public class Converter_2 {

    public static void main(String[] args) throws IOException {
        DataOperation dataOperation = new DataOperation();
        String text = dataOperation.readDataFromFile("C:\\Users\\tokug\\IdeaProjects\\PacificAction\\division.txt");

        String[] arrayText = text.split("\\n");

        System.out.println(arrayText.length);
        System.out.println();

        String resultText = "";

        for (int i = 0; i < arrayText.length; i++) {
            System.out.println(arrayText[i]);

        }

//        System.out.println(resultText);
    }
}
