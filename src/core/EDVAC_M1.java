package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class EDVAC_M1 {
    String result = "date;jap_arm_factory;jap_industrial_complex;jap_air_base;usa_arm_factory;usa_industrial_complex;usa_air_base";
    String date =  "";
    String owner = "";
    String[] arrayOnlyStates;
    public EDVAC_M1(Path filePath, String owner) {
        this.owner = owner;
        try {
            List<String> lines = Files.lines(filePath).collect(Collectors.toList());
            String result = String.join(System.lineSeparator(), lines);
            //System.out.println(result.length()); // Print or process as needed
            String[] arrayCountry = result.split("instances_counter");
            String states = arrayCountry[0];

            //extract date
            states.indexOf("player=\"USA\"");
            this.date = states.substring(50,60);
//            System.out.println(date);

            String[] arrayStates = states.split("states=\\{");
            String statesOnly = arrayStates[1].split("equipments=\\{")[0];
//            System.out.printf(String.valueOf(statesOnly.contains("[\d]")));
            arrayOnlyStates = statesOnly.split("[\\d]=\\{");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getDate() {
        return this.date;
    }

    public int parseAirBase(String owner) {
        int buildingsCounter = 0;
        for (int i = 0; i < arrayOnlyStates.length; i++) {
            if(arrayOnlyStates[i].contains("owner=\"" + owner + "\"") && arrayOnlyStates[i].contains("air_base")) {
//                System.out.println(arrayOnlyStates[i]);
//                System.out.println("=====");
                String[] elementOperationArray;
                String[] operationalArray = arrayOnlyStates[i].split("\\r\\n");
                for (int j = 0; j < operationalArray.length; j++) {
                    if(operationalArray[j].contains("air_base")) {
                        elementOperationArray = (operationalArray[j + 3]).trim().split(" ");
                        for (int k = 0; k < elementOperationArray.length; k++) {
                            try {
                                int value = Integer.parseInt(elementOperationArray[k].trim());
//                                buildingsCounter++;
                                buildingsCounter = buildingsCounter + value;
                                //                            System.out.println(value);
                            } catch (NumberFormatException e) {

                            }
                        }
                    }

                }
            }
        }
        return buildingsCounter;
    }

    public int parseIndustrialComplex(String owner) {
        int buildingsCounter = 0;
        for (int i = 0; i < arrayOnlyStates.length; i++) {
            if(arrayOnlyStates[i].contains("owner=\"" + owner + "\"") && arrayOnlyStates[i].contains("industrial_complex")) {
//                System.out.println(arrayOnlyStates[i]);
//                System.out.println("=====");
                String[] elementOperationArray;
                String[] operationalArray = arrayOnlyStates[i].split("\\r\\n");
                for (int j = 0; j < operationalArray.length; j++) {
                    if(operationalArray[j].contains("industrial_complex")) {
                        elementOperationArray = (operationalArray[j + 3]).trim().split(" ");
                        for (int k = 0; k < elementOperationArray.length; k++) {
                            try {
                                int value = Integer.parseInt(elementOperationArray[k].trim());
//                                buildingsCounter++;
                                buildingsCounter = buildingsCounter + value;
                                //                            System.out.println(value);
                            } catch (NumberFormatException e) {

                            }
                        }
                    }

                }
            }
        }
        return buildingsCounter;
    }
    public int parseArmsFactory(String owner) {
        int buildingsCounter = 0;
        for (int i = 0; i < arrayOnlyStates.length; i++) {
            if(arrayOnlyStates[i].contains("owner=\"" + owner + "\"") && arrayOnlyStates[i].contains("arms_factory")) {
//                System.out.println(arrayOnlyStates[i]);
//                System.out.println("=====");
                int begin = arrayOnlyStates[i].indexOf("arms_factory");
                int end = arrayOnlyStates[i].indexOf("industrial_");
                if(begin > 0 && begin < end) {
                    String[] sss = arrayOnlyStates[i].substring(begin, end).replaceAll(" ", "\r\n").split("\r\n");

                    for (int j = 0; j < sss.length; j++) {
                        try {
                            int value = Integer.parseInt(sss[j].trim());
//                            buildingsCounter++;
                            buildingsCounter = buildingsCounter + value;
//                            System.out.println(value);
                        } catch (NumberFormatException e) {

                        }
                    }
//                    System.out.println(buildingsCounter);
                }
            }
        }
        return buildingsCounter;
    }

    public int parseInfrastructure(String owner) {
        int buildingsCounter = 0;
        int totalValue = 0;
        for (int i = 0; i < arrayOnlyStates.length; i++) {
            if(arrayOnlyStates[i].contains("owner=\"" + owner + "\"") && arrayOnlyStates[i].contains("infrastructure")) {
//                System.out.println(arrayOnlyStates[i]);
//                System.out.println("=====");
                String[] elementOperationArray;
                String[] operationalArray = arrayOnlyStates[i].split("\\r\\n");
                for (int j = 0; j < operationalArray.length; j++) {
                    if(operationalArray[j].contains("infrastructure")) {
                        elementOperationArray = (operationalArray[j + 3]).trim().split(" ");
                        for (int k = 0; k < elementOperationArray.length; k++) {
                            try {
                                int value = Integer.parseInt(elementOperationArray[k].trim());
//                                System.out.println("value: " + value);
//                                totalValue = totalValue + value;
                                buildingsCounter = buildingsCounter + value;
                                //                            System.out.println(value);
                            } catch (NumberFormatException e) {

                            }
                        }
                    }

                }
            }
        }
        return buildingsCounter;
    }

    public int parseDockyard(String owner) {
        int buildingsCounter = 0;
        for (int i = 0; i < arrayOnlyStates.length; i++) {
            if(arrayOnlyStates[i].contains("owner=\"" + owner + "\"") && arrayOnlyStates[i].contains("dockyard")) {
//                System.out.println(arrayOnlyStates[i]);
//                System.out.println("=====");
                String[] elementOperationArray;
                String[] operationalArray = arrayOnlyStates[i].split("\\r\\n");
                for (int j = 0; j < operationalArray.length; j++) {
                    if(operationalArray[j].contains("dockyard")) {
                        elementOperationArray = (operationalArray[j + 3]).trim().split(" ");
                        for (int k = 0; k < elementOperationArray.length; k++) {
                            try {
                                int value = Integer.parseInt(elementOperationArray[k].trim());
//                                System.out.println("value: " + value);
//                                buildingsCounter++;
                                buildingsCounter = buildingsCounter + value;
                                //                            System.out.println(value);
                            } catch (NumberFormatException e) {

                            }
                        }
                    }

                }
            }
        }
        return buildingsCounter;
    }

    public String getResult() {
        return "";
    }
}
