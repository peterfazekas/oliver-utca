package hu.street.model.service;

import hu.street.model.domain.House;

import java.awt.*;
import java.awt.dnd.DropTarget;
import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;

    public DataApi(FileReader fileReader, DataParser dataParser) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
    }

    public List<House> getHouses(String input) {
        return dataParser.parse(fileReader.read(input));
    }
}
