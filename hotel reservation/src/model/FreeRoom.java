package model;

/**
 * Represents a free room.
 */
public class FreeRoom extends Room {

    /**
     * Create a new object of a free room. The price is equal to 0.
     *
     * @param roomNumber the room number of the room
     * @param roomType the type of the room
     * @param isFree a boolean to judge whether the room is free or not
     */
    public FreeRoom (String roomNumber, RoomType roomType, boolean isFree) {
        super(roomNumber, 0.0, roomType, true);
    }

    @Override
    public String toString() {
        return "This room is for free. You do not need to pay for it.";
    }
}
