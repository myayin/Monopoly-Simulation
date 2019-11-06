package entity;

public class Piece {

    public enum Color {

        RED("Red", 0xFF_FF0000),
        GREEN("Green", 0xFF_00FF00),
        BLUE("Blue", 0xFF_0000FF),
        BLACK("Black", 0xFF_000000),
        CYAN("Cyan", 0xFF_00FFFF);

        private String name;
        private int hexColor;

        Color(String name, int hexColor) {
            this.name = name;
            this.hexColor = hexColor;
        }

        public String getName() {
            return name;
        }

        public int getHexColor() {
            return hexColor;
        }

    }

    private Color color;
    private int currentLocation;

    public Piece(Color color) {
        this.color = color;
        this.currentLocation = 0;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Color getColor() {
        return color;
    }

}