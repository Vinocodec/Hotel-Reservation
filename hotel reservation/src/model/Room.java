package model;

/**
 * Represents a room.
 */
public class Room implements IRoom {

    private final String roomNumber;
    protected final Double price;
    private final RoomType roomType;
    private final boolean isFree;

    /**
     * Represent a new object of a room.
     *
     * @param roomNumber the number of the room
     * @param price the price of the room
     * @param roomType the type of the room
     * @param isFree true if the room is free
     */
    public Room(String roomNumber, Double price, RoomType roomType, boolean isFree) {
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.isFree = isFree;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public String toString() {
        return "RoomNumber:" + roomNumber + " || " + "price:" + price + " || " + "RoomType:" + roomType;
    }

}
