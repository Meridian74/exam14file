package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        Map<String, Integer> competitorsAndResults = readAllDatasFromFile(fileName);
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(competitorsAndResults.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        Collections.reverse(entries);
        return namesFromSortedEntries(entries);
    }

    private static List<String> namesFromSortedEntries(List<Map.Entry<String, Integer>> entries) {
        List<String> sortedNames = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedNames.add(entry.getKey());
        }
        return sortedNames;
    }

    private static Map<String, Integer> readAllDatasFromFile(String fileName) {
        Map<String, Integer> readedDatas = new HashMap<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Path.of(fileName),
                StandardCharsets.UTF_8)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNext()) {
                String[] datas = scanner.nextLine().split(",");
                String name = datas[0];
                int points = 0;
                for (int i = 1; i < datas.length; i++) {
                    points += Integer.parseInt(datas[i]);
                }
                readedDatas.put(name, points);
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return readedDatas;
    }

}
