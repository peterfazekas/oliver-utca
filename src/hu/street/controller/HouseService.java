package hu.street.controller;

import hu.street.model.domain.House;

import java.util.List;

public class HouseService {


    private final List<House> houses;

    public HouseService(List<House> houses) {
        this.houses = houses;
    }

    public int getSoldHousesCount() {
        return houses.size();
    }

    public String getLastSoldHouseDetails() {
        House lastHouse = houses.get(houses.size() - 1);
        String side = lastHouse.isOdd() ? "páratlan" : "páros";
        return String.format("A %s oldalon adták el az utolsó telket.%nAz utolsó telek házszáma: %d", side, lastHouse.getNumber());
    }
}
