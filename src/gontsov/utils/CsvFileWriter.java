package gontsov.utils;

import gontsov.Config;
import gontsov.Frame;

import java.io.FileWriter;
import java.io.IOException;

public class CsvFileWriter {
    Config conf;

    public CsvFileWriter(Config conf) {
        this.conf = conf;
    }

    private static final String DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "x1,x2,y1,y2,type,color,width";

    public void writeCsvFile(String fileName) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);

            for (Frame aFrame : conf.frame) {
                fileWriter.append(String.valueOf(aFrame.x1));
                fileWriter.append(DELIMITER);
                fileWriter.append(String.valueOf(aFrame.y1));
                fileWriter.append(DELIMITER);
                fileWriter.append(String.valueOf(aFrame.x2));
                fileWriter.append(DELIMITER);
                fileWriter.append(String.valueOf(aFrame.y2));
                fileWriter.append(DELIMITER);
                fileWriter.append(String.valueOf(aFrame.type));
                fileWriter.append(DELIMITER);
                fileWriter.append(String.valueOf(aFrame.color));
                fileWriter.append(DELIMITER);
                fileWriter.append(String.valueOf(aFrame.width));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }


            System.out.println("CSV file was created successfully");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
            }

        }
    }
}