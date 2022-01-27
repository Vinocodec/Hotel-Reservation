package api;

import model.IRoom;
import model.Customer;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    private static HotelResource hotelResource = null;

    public Customer getCustomer(String Email) {
        return customerService.getCustomer(Email);
    }

    public void createACustomer(String firstName, String lastName, String email) {
        customerService.addCustomer(firstName, lastName, email);
        System.out.println("Your Account has been created successfully!");
        System.out.println("-----------------------------------------------------------");
    }

    public IRoom getARoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

//    public boolean checkReservation(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
//        Customer customer = customerService.getCustomer(customerEmail);
//    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomerReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationService.findRooms(checkInDate, checkOutDate);
    }

    public Customer checkCustomer(String Email) {
        return customerService.checkCustomer(Email);
    }

    public IRoom checkARoom(String roomNumber) throws ParseException {
        return reservationService.checkARoom(roomNumber);
    }

    public IRoom checkRoomNumber(String roomNumber) {
        return reservationService.checkRoomNumber(roomNumber);
    }

    public static HotelResource getInstance() {
        if(hotelResource == null) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

}
