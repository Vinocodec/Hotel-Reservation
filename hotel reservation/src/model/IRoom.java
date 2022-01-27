package model;

/**
 * Represents a room.
 */
public interface IRoom {

    /**
     * Return a string to represent the number.
     *
     * @return the number of the room
     */
    String getRoomNumber();

    /**
     * Return the price of the room.
     *
     * @return the price of the room
     */
    Double getRoomPrice();

    /**
     * Return the type of the room.
     *
     * @return e type of the room
     */
    RoomType getRoomType();

    /**
     * Return true if the room is free.
     *
     * @return true if the room is free
     */
    boolean isFree();
}