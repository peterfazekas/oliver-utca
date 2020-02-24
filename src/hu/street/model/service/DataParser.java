package hu.street.model.service;

import hu.street.model.domain.House;

import java.util.List;
import java.util.stream.Collectors;

public class DataParser {

    private static int oddNumber = -1;
    private static int evenNumber = 0;

    public List<House> parse(List<String> lines) {
        return lines.stream()
                .map(this::createHouse)
                .collect(Collectors.toList());

    }

    private House createHouse(String line) {
        String[] items = line.split(" ");
        boolean odd = getValue(items[0]) == 1;
        int fenceLength = getValue(items[1]);
        String color = items[2];
        int number;
        if (odd) {
            number = oddNumber+=2;
        } else {
            number = evenNumber+=2;
        }
        return new House(odd, fenceLength, color, number);

    }

    private int getValue(String text) {
        return Integer.parseInt(text);
    }
}
