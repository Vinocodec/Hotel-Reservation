package api;

import java.util.Collection;
import java.util.List;

import model.IRoom;
import model.Customer;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {

    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String Email) {
        return customerService.getCustomer(Email);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        if(reservationService.roomList.isEmpty()) {
            System.out.println("There are no rooms been added for now.");
        }
        return reservationService.roomList;
    }

    public Collection<Customer> getAllCustomers() {
        if (customerService.customers.isEmpty()) {
            System.out.println("There are no customers been added for now.");
        }
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }

}