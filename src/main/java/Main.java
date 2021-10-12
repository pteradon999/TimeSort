package main.java;

import com.sun.jdi.IntegerValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

public static void main(String args[]) throws IOException {
    FileInputStream stream = null;
    Path pathb = Paths.get("LimitSortIII.java");
    String ath = String.valueOf(pathb.toAbsolutePath());
    ath = ath.replace("\\LimitSortIII.java", "\\src\\input\\");
    System.out.println(ath);
    int counts = (new File(ath)).list().length;
    File pathToFiles = new File(ath);
    String[] spth = pathToFiles.list();

    for(int i = 0; i < counts; ++i) {
        String LimitName = null;
        List<String> result = Files.readAllLines(Paths.get(ath + spth[i]));
        int PastIndex = 0;

        for(int ii = 0; ii <= result.size() - 1; ++ii) {
            if (((String)result.get(ii)).contains("PokerStars")) {
                if (LimitName != null) {
                    StringBuilder strb = new StringBuilder();

                    for(int c = 1; c + PastIndex < ii; ++c) {
                        strb.append((String)result.get(c + PastIndex - 1) + "\n");
                    }

                    strb.append("\n");
                    LimitWriter(strb.toString(), LimitName, ii - 1, PastIndex, i);
                }

                LimitName = result.get(ii).substring(result.get(ii).indexOf("-")+2);
                LimitName = LimitName.substring(LimitName.indexOf(" ")+1,LimitName.indexOf(":"));
                int Tmz = 0;
                Tmz = Integer.valueOf(LimitName)+3;
                if (Tmz > 24){
                    LimitName = String.valueOf(Tmz - 24+"_D+1");
                } else  LimitName = String.valueOf(Tmz);
                PastIndex = ii;
            }
        }
    }
}

    public static void LimitWriter(String ToWrite, String LimitName, Integer LastIndex, Integer NextIndex, Integer Filenum) throws IOException {
        Path path = Paths.get("LimitSortIII.java");
        String LimitPath = LimitName.replace(" ", "_");
        LimitPath = LimitPath.replace("/", "-");
        String oupath = String.valueOf(path.toAbsolutePath());
        oupath = oupath.replace("LimitSortIII.java", "src\\output\\");
        File outputfile = new File(oupath + LimitPath + ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile, true));
        writer.write(ToWrite);
        writer.flush();
        writer.close();
    }
}
