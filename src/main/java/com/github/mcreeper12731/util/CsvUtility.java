package com.github.mcreeper12731.util;

import com.github.mcreeper12731.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtility {

    /**
     * Retrieve only certain data columns from a CSV file
     * @param fileName name of the CSV file in the resources folder
     * @param requiredData string of space separated field names. These should match the order in which they appear in the CSV file
     * @return each line is represented by one list of strings. Each list contains the requested data in the order it appears in 'requiredData'
     */
    public static List<List<String>> getData(String fileName, String requiredData) {
        URL url = Main.class.getResource("/" + fileName);
        if (url == null) throw new RuntimeException("File " + fileName + " not found!");
        File file = new File(url.getFile());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String rawHeader = reader.readLine();

            // Remove UTF8_BOM character if file begins with it
            if (rawHeader.startsWith("\uFEFF")) {
                rawHeader = rawHeader.substring(1);
            }
            String[] header = rawHeader.split(",");

            int[] dataIndices = CsvUtility.parseHeader(header, requiredData.split(" "));
            List<List<String>> data = new ArrayList<>();
            reader.lines().forEach(line -> {
                String[] stopData = line.split(",");
                ArrayList<String> list = new ArrayList<>(dataIndices.length);
                Arrays.stream(dataIndices).forEach(index -> list.add(stopData[index]));
                data.add(list);
            });

            return data;

        } catch (IOException exception) {
            throw new RuntimeException("File 'stops.txt' not found");
        }
    }

    /**
     * Find indices of required data fields
     * @param header first line of the csv file, split into an array
     * @param requiredData array of required data fields
     * @return array of indices of required data fields
     */
    public static int[] parseHeader(String[] header, String[] requiredData) {
        int[] dataIndices = new int[requiredData.length];

        int dataIndex = 0;
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(requiredData[dataIndex])) {
                dataIndices[dataIndex++] = i;
                if (dataIndex >= requiredData.length) break;
            }
        }

        return dataIndices;
    }

}
