package model;

import java.util.Date;

/**
 * Represents a reservation.
 */
public class Reservation {

    private final Customer customer;
    public final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    /**
     * Create a new object of a reservation.
     *
     * @param customer the customer who make the reservation
     * @param room the room that be reserved
     * @param checkInDate the check in time
     * @param checkOutDate the check out time
     */
    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Customer: " + "\r\n" + customer + "\r\n"
                + "-----Room Information-----" + "\r\n" + room + "\r\n" + "Date: "
                + "\r\n"+ "---Check In: " + checkInDate + "\r\n" + "---Check Out: " + checkOutDate;
    }

}
