package gontsov.utils;

import gontsov.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CsvFileReader {
    Config conf;

    public CsvFileReader(Config conf) {
        this.conf = conf;
    }

    private static final String DELIMITER = ";";

    private final int X1 = 0;
    private final int Y1 = 1;
    private final int X2 = 2;
    private final int Y2 = 3;
    private final int TYPE = 4;
    private final int COLOR = 5;
    private final int WIDTH = 6;

    public Color toColor(String str) {
        Scanner sc = new Scanner(str);
        sc.useDelimiter("\\D+");

        return new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }

    public void readCsvFile(String fileName) {

        BufferedReader fileReader = null;

        try {
            String line;
            fileReader = new BufferedReader(new FileReader(fileName));
            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(DELIMITER);
                if (tokens.length > 0) {
                    conf.frame.add(new gontsov.Frame(
                            Integer.parseInt(tokens[X1]),
                            Integer.parseInt(tokens[Y1]),
                            Integer.parseInt(tokens[X2]),
                            Integer.parseInt(tokens[Y2]),
                            Integer.parseInt(tokens[TYPE]),
                            toColor(tokens[COLOR]),
                            Integer.parseInt(tokens[WIDTH])
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader");
                e.printStackTrace();
            }
        }

    }
}