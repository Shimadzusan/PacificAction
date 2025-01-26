package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ENIAC_M6_ARMY {
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
                    finalData = finalData + parseDivision(arrayCountry[i + 1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalData;
    }

    public String getDate() {
        return this.date;
    }

    public String parseDivision(String countryData) {
        String data = "";
        int divisionCounter = 0;
        String[] arrayCountryData = countryData.split("\\r\\n");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String[] fleetUnit = {"carrier","battleship","battle_cruiser","heavy_cruiser","light_cruiser","destroyer","submarine"};
        for (int i = 0; i < arrayCountryData.length; i++) {
            if(arrayCountryData[i].contains("division={")) {
                divisionCounter++;
            }
        }

//        for (int i = 0; i < fleetUnit.length; i++) {
////            System.out.print(map.get(fleetUnit[i]) + ";");
//            data = data + map.get(fleetUnit[i]) + ";";
//        }
//        System.out.println();

        return data + divisionCounter;
    }

    public String getResult() {
        return "";
    }
}
