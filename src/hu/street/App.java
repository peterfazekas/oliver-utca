package hu.street;

import hu.street.controller.HouseService;
import hu.street.model.domain.House;
import hu.street.model.service.Console;
import hu.street.model.service.DataApi;
import hu.street.model.service.DataParser;
import hu.street.model.service.FileReader;

import java.util.Scanner;

public class App {

    private final HouseService houseService;
    private final Console console;

    private App() {
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        houseService = new HouseService(dataApi.getHouses("kerites.txt"));
        console = new Console(new Scanner(System.in));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2.feladat");
        System.out.println("Az eladott telkek száma " + houseService.getSoldHousesCount());
        System.out.println("3. feladat:");
        System.out.println(houseService.getLastSoldHouseDetails());
    }
}
