package core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


public class Launch {
    static int runLogicCounter = 0;
    static DataOperation dataOperation = new DataOperation();
    static String fileName = "autosave.hoi4";
    static Path filePath = Paths.get("C:\\Users\\user\\Documents\\Paradox Interactive\\Hearts of Iron IV\\save games\\" + fileName); // Change this to your file's path

    public static void main(String[] args) throws InterruptedException, IOException {
        String lastEditDate = dataOperation.getFileAttributs(filePath.toString());
        while(true) {
            Thread.sleep(120000);//120000
            String currentEditDate = dataOperation.getFileAttributs(filePath.toString());
            System.out.println("run..");

            if(!lastEditDate.equals(currentEditDate)) {
                System.out.println("run main logic...");
                runLogic();
//                currentDebug();
                lastEditDate = currentEditDate;
            }
        }
    }

    private static void currentDebug() throws IOException {
//        String file = "eniac_m5.csv";
//        String data = "";
//        data = data + (new ENIAC_M5(filePath, "USA").getDate() + ";");
//        String[] subject = {"CHI","GER","ENG","JAP","SOV","USA"};
//        data = data + methodEniac("CHI");
//        data = data + methodEniac("GER");
//        data = data + methodEniac("ENG");
//        data = data + methodEniac("JAP");
//        data = data + methodEniac("SOV");
//        data = data + methodEniac("USA");
//        dataOperation.writeDataToFile(file,dataOperation.readDataFromFile(file) + data + "");

        //===================2===============

        String data = "";
        String[] subject = {"CHI","GER","ENG","JAP","SOV","USA","ITA","ROM","BUL","HUN"};
        ENIAC_M6_ARMY eniac_m6_army = new ENIAC_M6_ARMY();
        for (int i = 0; i < subject.length; i++) {
            data = data + eniac_m6_army.topMethod(filePath, subject[i]) + ";";//debug
        }
//        System.out.println(data);
        dataOperation.writeDataToFile("eniac_m6_army.csv",dataOperation.readDataFromFile("eniac_m6_army.csv") + data + "");
    }

    private static void runLogic() throws IOException {
        String file = "eniac_m5.csv";
        String data = "";
        data = data + (new ENIAC_M5(filePath, "USA").getDate() + ";");
//        String[] subject = {"CHI","GER","ENG","JAP","SOV","USA"};
        String[] subject = {"CHI","GER","ENG","JAP","SOV","USA","ITA","ROM","BUL","HUN"};
        data = data + methodEniac("CHI");
        data = data + methodEniac("GER");
        data = data + methodEniac("ENG");
        data = data + methodEniac("JAP");
        data = data + methodEniac("SOV");
        data = data + methodEniac("USA");
        data = data + methodEniac("ITA");
        data = data + methodEniac("ROM");
        data = data + methodEniac("BUL");
        data = data + methodEniac("HUN");
        dataOperation.writeDataToFile(file,dataOperation.readDataFromFile(file) + data + "");

//        System.out.println();
//        System.out.println("EDVAC:");
        String fileEdvac = "edvac_m1.csv";
        data = "";
        data = data + (new ENIAC_M5(filePath, "USA").getDate() + ";");
//        String[] subject = {"CHI","GER","ENG","JAP","SOV","USA"};
        data = data + methodEdvac("CHI");
        data = data + methodEdvac("GER");
        data = data + methodEdvac("ENG");
        data = data + methodEdvac("JAP");
        data = data + methodEdvac("SOV");
        data = data + methodEdvac("USA");
        data = data + methodEdvac("ITA");
        data = data + methodEdvac("ROM");
        data = data + methodEdvac("BUL");
        data = data + methodEdvac("HUN");
        dataOperation.writeDataToFile(fileEdvac,dataOperation.readDataFromFile(fileEdvac) + data + "");

//        System.out.println();
//        System.out.println("FLEET:");
        data = "";
//        System.out.print(new ENIAC_M5(filePath, "USA").getDate() + ";");
        data = data + (new ENIAC_M5(filePath, "USA").getDate() + ";");
        ENIAC_M6_FLEET eniac_m6_fleet = new ENIAC_M6_FLEET();
        for (int i = 0; i < subject.length; i++) {
            data = data + eniac_m6_fleet.topMethod(filePath, subject[i]);//debug
        }
        dataOperation.writeDataToFile("eniac_m6_fleet.csv",dataOperation.readDataFromFile("eniac_m6_fleet.csv") + data + "");

        //Resourses

        data = "";
        data = data + (new ENIAC_M5(filePath, "USA").getDate() + ";");
        ENIAC_M6_RESOURCES eniac_m6_resourses = new ENIAC_M6_RESOURCES();
        ENIAC_M6_RESOURCES_2 eniac_m6_resourses_2 = new ENIAC_M6_RESOURCES_2();
        ENIAC_M6_RESOURCES_3 eniac_m6_resourses_3 = new ENIAC_M6_RESOURCES_3();
        for (int i = 0; i < subject.length; i++) {
            data = data + eniac_m6_resourses.topMethod(filePath, subject[i]);//debug
        }
        for (int i = 0; i < subject.length; i++) {
//
            data = data + eniac_m6_resourses_2.topMethod(filePath, subject[i]);//debug
        }
        for (int i = 0; i < subject.length; i++) {
//            data = data + eniac_m6_resourses.topMethod(filePath, subject[i]);//debug
            data = data + eniac_m6_resourses_3.topMethod(filePath, subject[i]);//debug
        }
        dataOperation.writeDataToFile("eniac_m6_resources.csv",dataOperation.readDataFromFile("eniac_m6_resources.csv") + data + "");


        //Army
        data = "";
        data = data + (new ENIAC_M5(filePath, "USA").getDate() + ";");
        ENIAC_M6_ARMY eniac_m6_army = new ENIAC_M6_ARMY();
        for (int i = 0; i < subject.length; i++) {
            data = data + eniac_m6_army.topMethod(filePath, subject[i]) + ";";//debug
        }
        dataOperation.writeDataToFile("eniac_m6_army.csv",dataOperation.readDataFromFile("eniac_m6_army.csv") + data + "");

        runLogicCounter++;
        System.out.println("runLogicCounter: " + runLogicCounter);
    }

//    private static void runLogic() {BACKUP!!!!
//        System.out.print(new ENIAC_M5(filePath, "USA").getDate() + ";");
//        String[] subject = {"CHI","GER","ENG","JAP","SOV","USA"};
//        methodEniac("CHI");
//        methodEniac("GER");
//        methodEniac("ENG");
//        methodEniac("JAP");
//        methodEniac("SOV");
//        methodEniac("USA");
//
//        System.out.println();
//        System.out.println("EDVAC:");
//        System.out.print(new ENIAC_M5(filePath, "USA").getDate() + ";");
//
//        methodEdvac("CHI");
//        methodEdvac("GER");
//        methodEdvac("ENG");
//        methodEdvac("JAP");
//        methodEdvac("SOV");
//        methodEdvac("USA");
//
//        System.out.println();
//        System.out.println("FLEET:");
//        System.out.print(new ENIAC_M5(filePath, "USA").getDate() + ";");
//
//        for (int i = 0; i < subject.length; i++) {
//            new ENIAC_M6_FLEET(filePath, subject[i]);//debug
//        }
//    }

    public static String methodEniac(String owner) {
        String data = "";
        ENIAC_M5 eniac_m5 = new ENIAC_M5(filePath, owner);

        data = data + (eniac_m5.parseInfrastructure(owner) + ";");
        data = data + (eniac_m5.parseArmsFactory(owner) + ";");
        data = data + (eniac_m5.parseIndustrialComplex(owner) + ";");
        data = data + (eniac_m5.parseAirBase(owner) + ";");
        data = data + (eniac_m5.parseDockyard(owner) + ";");
        return data;
//        System.out.print(eniac_m5.parseInfrastructure(owner) + ";");
//        System.out.print(eniac_m5.parseArmsFactory(owner) + ";");
//        System.out.print(eniac_m5.parseIndustrialComplex(owner) + ";");
//        System.out.print(eniac_m5.parseAirBase(owner) + ";");
//        System.out.print(eniac_m5.parseDockyard(owner) + ";");
    }

    public static String methodEdvac(String owner) {
        String data = "";
        EDVAC_M1 edvac_m1 = new EDVAC_M1(filePath, owner);

        data = data + (edvac_m1.parseInfrastructure(owner) + ";");
        data = data + (edvac_m1.parseArmsFactory(owner) + ";");
        data = data + (edvac_m1.parseIndustrialComplex(owner) + ";");
        data = data + (edvac_m1.parseAirBase(owner) + ";");
        data = data + (edvac_m1.parseDockyard(owner) + ";");
        return data;
    }

}
