package model;

public enum RoomType {
    SINGLE("Single"),
    DOUBLE("Double");

    public final String label;

    RoomType(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
    @Override
    public String toString() {
        return label;
    }
}
