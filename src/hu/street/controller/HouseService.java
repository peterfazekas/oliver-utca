package hu.street.controller;

import hu.street.model.domain.House;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HouseService {


    private final List<House> houses;

    public HouseService(List<House> houses) {
        this.houses = houses;
    }

    /**
     * 2. feladat
     */
    public int getSoldHousesCount() {
        return houses.size();
    }

    /**
     * 3. feladat
     */
    public String getLastSoldHouseDetails() {
        House lastHouse = houses.get(houses.size() - 1);
        String side = lastHouse.isOdd() ? "páratlan" : "páros";
        return String.format("A %s oldalon adták el az utolsó telket.%nAz utolsó telek házszáma: %d", side, lastHouse.getNumber());
    }

    /**
     * 4. feladat
     */
    public int getSameFenceColorHouseFromOddSide() {
        List<House> housesFromOddSide = getHousesFromOddSide();
        int index = 0;
        for (int i = 1; i < housesFromOddSide.size(); i++) {
            String fenceColor = housesFromOddSide.get(i).getColor();
            String neighbourFenceColor = housesFromOddSide.get(i - 1).getColor();
            if (!fenceColor.equals(":") && !fenceColor.equals("#") && fenceColor.equals(neighbourFenceColor)) {
                index = i;
                break;
            }
        }
        return housesFromOddSide.get(index - 1).getNumber();
    }

    /**
     * 5. feladat
     */

    public String getHouseColor(int houseNumber) {
        return getHouseByNumber(houseNumber)
                .map(House::getColor)
                .orElse(" ");
    }

    public String getPossibleColor(int houseNumber) {
        Random random = new Random();
        List<String> possibleColors = getPossibleColors(houseNumber);
        return possibleColors.get(random.nextInt(possibleColors.size()));
    }

    private List<String> getPossibleColors(int houseNumber) {
        List<String> colors = getColors();
        colors.remove(getHouseColor(houseNumber));
        colors.remove(getHouseColor(houseNumber - 2));
        colors.remove(getHouseColor(houseNumber + 2));
        return colors;
    }

    private List<String> getColors() {
        return IntStream.rangeClosed('A', 'Z')
                .mapToObj(i -> Character.toString((char) i))
                .collect(Collectors.toList());
    }

    private Optional<House> getHouseByNumber(int houseNumber) {
        return houses.stream()
                .filter(i -> i.getNumber() == houseNumber)
                .findAny();
    }

    /**
     * 6. feladat
     */
    public List<String> getFenceDetail() {
        return List.of(getFenceColors(), getHouseNumbers());
    }

    private String getFenceColors() {
        return getHousesFromOddSide().stream()
                .map(this::createFenceColor)
                .collect(Collectors.joining());
    }

    private String createFenceColor(House house) {
        return house.getColor().repeat(house.getFenceLength());
    }

    private String getHouseNumbers() {
        return getHousesFromOddSide().stream()
                .map(this::createHouseNumber)
                .collect(Collectors.joining());
    }

    private String createHouseNumber(House house) {
        String houseNumber = Integer.toString(house.getNumber());
        return houseNumber + " ".repeat(house.getFenceLength() - houseNumber.length());
    }

    private List<House> getHousesFromOddSide() {
        return houses.stream()
                .filter(House::isOdd)
                .collect(Collectors.toList());
    }
}
