package hu.street;

import hu.street.controller.HouseService;
import hu.street.model.domain.House;
import hu.street.model.service.*;

import java.util.Scanner;

public class App {

    private final HouseService houseService;
    private final Console console;
    private final FileWriter fileWriter;

    private App() {
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        houseService = new HouseService(dataApi.getHouses("kerites.txt"));
        console = new Console(new Scanner(System.in));
        fileWriter = new FileWriter("utcakep.txt");
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2.feladat");
        System.out.println("Az eladott telkek száma " + houseService.getSoldHousesCount());
        System.out.println("3. feladat:");
        System.out.println(houseService.getLastSoldHouseDetails());
        System.out.println("4. feladat:");
        System.out.println("A szomszédossal egyezik a kerítés színe: " + houseService.getSameFenceColorHouseFromOddSide());
        System.out.println("5. feladat:");
        System.out.print("Adjon meg egy házszámot: ");
        int houseNumber = console.readInt();
        System.out.println("A kerítés színe / állapota: " + houseService.getHouseColor(houseNumber));
        System.out.println("Egy lehetséges festési szín: " + houseService.getPossibleColor(houseNumber));
        fileWriter.write(houseService.getFenceDetail());
    }
}
