package core;

import util.DataOperation;

import java.io.IOException;

public class Converter {

    public static void main(String[] args) throws IOException {
        DataOperation dataOperation = new DataOperation();
        String text = dataOperation.readDataFromFile("C:\\Users\\tokug\\IdeaProjects\\PacificAction\\division.txt");

        String[] arrayText = text.split("\\n");

        System.out.println(arrayText.length);
        System.out.println();

        String resultText = "";

//        for (int i = 0; i < arrayText.length; i++) {
//            if(arrayText[i].contains("id") && arrayText[i].contains(" type = ")) {
//                resultText = resultText + arrayText[i].replaceAll(" type = ", ",\n type = ");
////                resultText = resultText + "\n";
//                continue;
//            }
//            resultText = resultText + arrayText[i];
//
//
//        }

        for (int i = 0; i < arrayText.length; i++) {
            if(arrayText[i].contains("=")) {
                resultText = resultText + "\"" + arrayText[i].replaceAll("=", "\":\"") + "\",\n";
                continue;
            }
//            if(arrayText[i].contains("id") && arrayText[i].contains(" type = ")) {
//                resultText = resultText + arrayText[i].replaceAll(" type = ", "\n type = ");
//                continue;
//            }
            resultText = resultText + arrayText[i];
//            resultText = resultText.replaceAll(" ", "");
//            resultText = resultText.replaceAll("\"\\{\",", "{");
//            resultText = resultText.replaceAll("\"\"", "\"");
//            resultText = resultText.replaceAll("\"\\{\\}\"", "{}");
//            resultText = resultText.replaceAll("},", "}");
//            resultText = "{" + resultText + "}";
        }
        resultText = resultText.replaceAll(" ", "");
        resultText = resultText.replaceAll("\"\\{\",", "{");
        resultText = resultText.replaceAll("\"\"", "\"");
        resultText = resultText.replaceAll("\"\\{\\}\"", "{}");
        resultText = resultText.replaceAll("},", "}");

        //*************************

        String[] resultText_2 = resultText.split("\n");
        String resultText_3 = "";

        for (int i = 0; i < resultText_2.length; i++) {
            if(resultText_2[i].contains("id") && resultText_2[i].contains("type")) {
                resultText_2[i] = resultText_2[i].replaceAll("type:\"", ("123" + "\n"));
                continue;
            }
            resultText_3 = resultText_3 + resultText_2[i] + "\n";

        }

        System.out.println(resultText_3);
    }
}
