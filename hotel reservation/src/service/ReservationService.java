package service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import api.AdminMenu;
import api.MainMenu;
import model.IRoom;
import model.Customer;
import model.Reservation;

public class ReservationService {

    private final static AdminMenu adminMenu = new AdminMenu();
    private final static MainMenu mainMenu = MainMenu.getInstance();

    public Collection<IRoom> roomList = new HashSet<>();
    public Collection<Reservation> reservations = new HashSet<>();

    private static ReservationService reservationService = null;

    public void addRoom(IRoom room) {
        roomList.add(room);
        System.out.println(room);
        System.out.println("The room is added successfully! ");
    }

    public IRoom getARoom(String roomNumber) {
        for (IRoom room : roomList) {
            if (roomNumber.equals(room.getRoomNumber())) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation r = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(r);
        return r;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();
        for (IRoom r: roomList){
            if (!isRoomReserved(r, checkInDate, checkOutDate)){
                availableRooms.add(r);
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        Collection<Reservation> customerReservation = new HashSet<>();
        for(Reservation reservation : reservations) {
            if (customer.equals(reservation.getCustomer())){
                customerReservation.add(reservation);
            }
        }
        if (customerReservation.isEmpty()) {
            System.out.println("You have not a reservation for now.");
            System.out.println();
        }
        for(Reservation r : customerReservation) {
            System.out.println(r);
        }
        return customerReservation;
    }

    private boolean isRoomReserved(IRoom room, Date checkInDate, Date checkOutDate) {
        if (reservations.isEmpty()) {
            return false;
        }

        for (Reservation r: reservations){
            IRoom reservedRoom = r.getRoom();
            if (reservedRoom.getRoomNumber().equals(room.getRoomNumber())){
                if (reservationConflictsWithRange(r, checkInDate, checkOutDate)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean reservationConflictsWithRange(Reservation reservation, Date checkInDate, Date checkOutDate){
        return !(checkOutDate.before(reservation.getCheckInDate()) || checkInDate.after(reservation.getCheckOutDate()));
    }

    public IRoom checkARoom(String roomNumber) throws ParseException {
        for (IRoom room : roomList) {
            if (!roomNumber.equals(room.getRoomNumber())) {
                return room;
            } else {
                System.out.println("This room has already been added");
                adminMenu.start();
                return null;
            }
        }
        return null;
    }

    public IRoom checkRoomNumber(String roomNumber) {
        Optional<IRoom> availableRoom = mainMenu.availableRooms.stream().filter(c -> roomNumber.equals(c.getRoomNumber())).findFirst();
        return availableRoom.orElse(null);
    }

    public void printAllReservation() {
        for (Reservation r : reservations){
            System.out.println(r);
        }
        if (reservations.isEmpty()) {
            System.out.println("There are no reservations for now.");
        }
    }

    public static ReservationService getInstance() {
        if(reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    String turnAlarmOn() {
        return "Turning the alarm on.";
    }

}
