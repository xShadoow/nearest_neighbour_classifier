import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVFileReader {

    private static final String LINE_SEPERATOR = ",";

    public static CSVEntry[] readCSVFile(String filePath, final int maxLines, final int dimensions) {

        CSVEntry[] csvEntries = new CSVEntry[maxLines];

        try {

            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine(); //skip first line

            int linesRead = 0;
            String currentLine = "";
            String[] tmpArray;

            while((currentLine = bufferedReader.readLine()) != null && maxLines > linesRead) {
                tmpArray = currentLine.split(LINE_SEPERATOR);
                float[] datapoints = new float[dimensions];
                int classification = Integer.parseInt(tmpArray[tmpArray.length-1]);

                for(int i = 0; i < (tmpArray.length-1); i++) {
                    datapoints[i] = Float.parseFloat(tmpArray[i]);
                }

                csvEntries[linesRead] = new CSVEntry(datapoints, classification);
                linesRead++;
            }

            bufferedReader.close();
            return csvEntries;

        } catch (IOException exception) {
            exception.printStackTrace();
            System.err.println("Error while reading csv file!");
            return null;
        }

    }

}
