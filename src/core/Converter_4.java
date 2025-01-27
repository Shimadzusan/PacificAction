package core;

import core.DataOperation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class Converter_4 {
    static int runLogicCounter = 0;
    static DataOperation dataOperation = new DataOperation();
    static String fileName = "USA_1943.hoi4";
    //    static Path filePath = Paths.get("C:\\Users\\user\\Documents\\Paradox Interactive\\Hearts of Iron IV\\save games\\" + fileName); // Change this to your file's path
    static Path filePath = Paths.get("D:\\projectAlpha\\" + fileName);

    public static void main(String[] args) throws InterruptedException, IOException {
        try {
            List<String> lines = Files.lines(filePath).collect(Collectors.toList());
            String result = String.join(System.lineSeparator(), lines);

            String[] arrayData = result.split("\r\n");
            String operativeData = "";
            int caseOne = 0;
            int caseTwo = 0;
            int caseThree = 0;
            int caseFour = 0;
//            for (int i = 0; i < arrayData.length; i++) {
//                if(arrayData[i].contains("{") && arrayData[i].contains("}")) {
//                    caseOne++;
//                }
//
//                if(arrayData[i].contains("{") && !arrayData[i].contains("}")) {
//                    caseTwo++;
//                }
//
//                if(!arrayData[i].contains("{") && !arrayData[i].contains("}")) {
//                    caseThree++;
//                }
//
//                if(!arrayData[i].contains("{") && arrayData[i].contains("}")) {
//                    caseFour++;
//                }
//            }

            System.out.println("one " + caseOne + " two " + caseTwo + " three " + caseThree + " four " + caseFour);
            System.out.println("total: " + (caseOne + caseTwo + caseThree + caseFour));

            System.out.println(arrayData.length);

            //===========stage 2
            int curlyBrace = 0;
            String element = "";
            String totalResult = "";

            for (int i = 0; i < 300000; i++) {

                if(!arrayData[i].contains("{") && !arrayData[i].contains("}") && curlyBrace == 0) {
                    if(element.length() > 0)System.out.println(element);
                    System.out.println(arrayData[i]);
                    element = "";
                    continue;
                }

                if(arrayData[i].contains("{") && !arrayData[i].contains("}") && curlyBrace == 0) {
                    if(element.length() > 0)System.out.println(element);
                    System.out.println(arrayData[i]);
                    element = "";
                    curlyBrace = curlyBrace + 1;
                    continue;
                }

                if(arrayData[i].contains("{") && !arrayData[i].contains("}") && curlyBrace > 0) {
//                    System.out.println("++");
                    curlyBrace = curlyBrace + 1;
                    element = element + arrayData[i] + ";";
                    continue;
                }


                if(!arrayData[i].contains("{") && arrayData[i].contains("}") && curlyBrace > 0) {
//                    System.out.println("--");
                    curlyBrace = curlyBrace - 1;
                    element = element + arrayData[i] + ";";
                    continue;
                }

                if(!arrayData[i].contains("{") && !arrayData[i].contains("}") && curlyBrace > 0) {
//                    System.out.println(arrayData[i]);
                    element = element + arrayData[i] + ";";
                    continue;
                }
//                System.out.println("cb: " + curlyBrace);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


