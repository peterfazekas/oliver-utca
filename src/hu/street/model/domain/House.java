package hu.street.model.domain;

public class House {

    private final boolean odd;
    private final int fenceLength;
    private final String color;
    private final int number;

    public House(boolean odd, int fenceLength, String color, int number) {
        this.odd = odd;
        this.fenceLength = fenceLength;
        this.color = color;
        this.number = number;
    }

    public boolean isOdd() {
        return odd;
    }

    public int getFenceLength() {
        return fenceLength;
    }

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }
}
