package main.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringJoiner;

public class CSVReader {
    String filepath;
    String separator;
    int[][] data;
    public CSVReader(String filepath, String separator){
        this.filepath = filepath;
        this.separator = separator;
    }

    public int[][] read() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = br.readLine();
            data = new int[Integer.parseInt(line)][Integer.parseInt(line)];
            int i = 0;
            while ((line = br.readLine()) != null) {
                data[i] = Arrays.stream(line.split(separator)).mapToInt(Integer::parseInt).toArray();
                i++;
            }
        }
        return data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CSVReader.class.getSimpleName() + "[", "]")
                .add("data=" + Arrays.deepToString(data))
                .toString();
    }

    public static void main(String[] args) {
        try{
            CSVReader test = new CSVReader("resources/test_5x5.csv",",");
            test.read();
            System.out.println(test);

        } catch (IOException e){
            System.out.println("Nie odnaleziono pliku");
        }
    }

}
