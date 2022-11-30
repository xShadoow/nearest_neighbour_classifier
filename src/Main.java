import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String filePath = "C://Users//sandr//Downloads//iris.csv";
        CSVEntry[] csvEntries = CSVFileReader.readCSVFile(filePath, 150, 4);
        System.out.println("File read. lines: " + csvEntries.length);
        System.out.println("Datapoints: " + Arrays.toString(csvEntries[csvEntries.length - 1].getDatapoints()));
        System.out.println("Classification: " + csvEntries[csvEntries.length-1].getClassification());
    }
}