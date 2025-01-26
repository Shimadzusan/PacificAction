package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ENIAC_M6_FLEET {
    //String result = "date;jap_arm_factory;jap_industrial_complex;jap_air_base;usa_arm_factory;usa_industrial_complex;usa_air_base";
    String date =  "";
    String owner = "";
    String[] arrayOnlyStates;
    public String topMethod(Path filePath, String owner) {
        this.owner = owner;
        String finalData = "";
        try {
            List<String> lines = Files.lines(filePath).collect(Collectors.toList());
            String result = String.join(System.lineSeparator(), lines);
            //System.out.println(result.length()); // Print or process as needed
            String[] arrayCountry = result.split("instances_counter");
            //cached_navy_strength
            String states = arrayCountry[0];

            for (int i = 0; i < arrayCountry.length; i++) {
                String[] arrayCountryTwo = arrayCountry[i].split("\\r\\n");
//                System.out.println(arrayCountryTwo[arrayCountryTwo.length - 2]); //..GER={
                if(arrayCountryTwo[arrayCountryTwo.length - 2].contains(owner + "={")) {
//                    parseFleet(arrayCountry[i + 1]);
                    finalData = finalData + parseFleet(arrayCountry[i + 1]);
                    finalData = finalData + parseConvoy(arrayCountry[i + 1]);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalData;
    }

    private String parseConvoy(String countryData) {
        String data = "";
        String[] arrayCountryData = countryData.split("\\r\\n");
        HashMap<String, String> map = new HashMap<String, String>();
        String[] fleetUnit = {"available"};
        for (int i = 0; i < arrayCountryData.length; i++) {
//            if(arrayCountryData[i].contains("equipment_production={")) {
            if(arrayCountryData[i].contains("nukes={")) {


//                System.out.println(arrayCountryData[i + 2].trim());
//                System.out.println(arrayCountryData[i + 3].trim());
//                System.out.println(arrayCountryData[i + 4].trim());
//                System.out.println(arrayCountryData[i + 5].trim());
//                System.out.println(arrayCountryData[i + 6].trim());
//                System.out.println(arrayCountryData[i + 7].trim());
//                System.out.println(arrayCountryData[i + 8].trim());

                try {
                    if (arrayCountryData[i + 10].contains("=")) {
//                        System.out.println("debug++");
//                        System.out.println(arrayCountryData[i + 10]);
                        String[] arrayForMap = arrayCountryData[i + 10].trim().split("=");
                        map.put(arrayForMap[0], arrayForMap[1]);
                    }

                    if (arrayCountryData[i + 3].contains("=")) {
                        String[] arrayForMap = arrayCountryData[i + 3].trim().split("=");
                        map.put(arrayForMap[0], arrayForMap[1]);
                    }

//                    if (arrayCountryData[i + 4].contains("=")) {
//                        String[] arrayForMap = arrayCountryData[i + 4].trim().split("=");
//                        map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
//                    }
//
//                    if (arrayCountryData[i + 5].contains("=")) {
//                        String[] arrayForMap = arrayCountryData[i + 5].trim().split("=");
//                        map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
//                    }
//
//                    if (arrayCountryData[i + 6].contains("=")) {
//                        String[] arrayForMap = arrayCountryData[i + 6].trim().split("=");
//                        map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
//                    }
//
//                    if (arrayCountryData[i + 7].contains("=")) {
//                        String[] arrayForMap = arrayCountryData[i + 7].trim().split("=");
//                        map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
//                    }
//
//                    if (arrayCountryData[i + 8].contains("=")) {
//                        String[] arrayForMap = arrayCountryData[i + 8].trim().split("=");
//                        map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
//                    }
                }catch (NumberFormatException e) {
                    //System.out.println("NumberFormatException in class ENIAC_M6_FLEET");
                }
            }
        }
//        System.out.println("++");
//        System.out.println(map.size());

//        System.out.println("owner: " + owner);
//        System.out.println();
        for (int i = 0; i < fleetUnit.length; i++) {
//            System.out.print(map.get(fleetUnit[i]) + ";");
            data = data + map.get(fleetUnit[i]) + ";";
        }
//        System.out.println();

        return data;
    }

    public String getDate() {
        return this.date;
    }

    public String parseFleet(String countryData) {
        String data = "";
        String[] arrayCountryData = countryData.split("\\r\\n");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String[] fleetUnit = {"carrier","battleship","battle_cruiser","heavy_cruiser","light_cruiser","destroyer","submarine"};
        for (int i = 0; i < arrayCountryData.length; i++) {
            if(arrayCountryData[i].contains("cached_navy_strength=")) {
//                System.out.println(arrayCountryData[i + 2].trim());
//                System.out.println(arrayCountryData[i + 3].trim());
//                System.out.println(arrayCountryData[i + 4].trim());
//                System.out.println(arrayCountryData[i + 5].trim());
//                System.out.println(arrayCountryData[i + 6].trim());
//                System.out.println(arrayCountryData[i + 7].trim());
//                System.out.println(arrayCountryData[i + 8].trim());

            try {
                if (arrayCountryData[i + 2].contains("=")) {
                    String[] arrayForMap = arrayCountryData[i + 2].trim().split("=");
                    map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
                }

                if (arrayCountryData[i + 3].contains("=")) {
                    String[] arrayForMap = arrayCountryData[i + 3].trim().split("=");
                    map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
                }

                if (arrayCountryData[i + 4].contains("=")) {
                    String[] arrayForMap = arrayCountryData[i + 4].trim().split("=");
                    map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
                }

                if (arrayCountryData[i + 5].contains("=")) {
                    String[] arrayForMap = arrayCountryData[i + 5].trim().split("=");
                    map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
                }

                if (arrayCountryData[i + 6].contains("=")) {
                    String[] arrayForMap = arrayCountryData[i + 6].trim().split("=");
                    map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
                }

                if (arrayCountryData[i + 7].contains("=")) {
                    String[] arrayForMap = arrayCountryData[i + 7].trim().split("=");
                    map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
                }

                if (arrayCountryData[i + 8].contains("=")) {
                    String[] arrayForMap = arrayCountryData[i + 8].trim().split("=");
                    map.put(arrayForMap[0], Integer.parseInt(arrayForMap[1]));
                }
            }catch (NumberFormatException e) {
                //System.out.println("NumberFormatException in class ENIAC_M6_FLEET");
            }
            }
        }
//        System.out.println("++");
//        System.out.println(map.size());

//        System.out.println("owner: " + owner);
//        System.out.println();
        for (int i = 0; i < fleetUnit.length; i++) {
//            System.out.print(map.get(fleetUnit[i]) + ";");
            data = data + map.get(fleetUnit[i]) + ";";
        }
//        System.out.println();

        return data;
    }

    public String getResult() {
        return "";
    }
}
