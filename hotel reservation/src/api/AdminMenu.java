package api;

import model.Room;
import model.IRoom;
import model.RoomType;
import model.Customer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static AdminMenu adminMenu = null;
    public final static MainMenu mainMenu = new MainMenu();
    private final static AdminResource adminResource = new AdminResource();
    private final static HotelResource hotelResource = new HotelResource();

    public static void main(String[] args) throws ParseException {
        AdminMenu admin = new AdminMenu();
        admin.start();
    }

    public void start() throws ParseException {

        boolean canRun = true;

        Scanner scanner = new Scanner(System.in);

        while (canRun) {

            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. back to Main Menu");
            int i = Integer.parseInt(scanner.next());

            switch (i) {

                case 1:

                    System.out.println("--------------------Customer System--------------------");
                    Collection<Customer> customers = adminResource.getAllCustomers();
                    for (Customer customer : customers) {
                        System.out.println(customer);
                    }
                    break;

                case 2:

                    System.out.println("--------------------All Rooms--------------------");
                    Collection<IRoom> roomList = adminResource.getAllRooms();
                    for (IRoom r : roomList) {
                        System.out.println(r);
                    }
                    break;

                case 3:

                    System.out.println("--------------------All Reservations--------------------");
                    adminResource.displayAllReservations();
                    break;

                case 4:

                    System.out.println("--------------------Add Room--------------------");
                    boolean keepAdding = true;
                    while (keepAdding) {

                        System.out.println("Please add a room number: ");
                        Scanner sc = new Scanner(System.in);
                        String roomNumber = sc.next();

                        hotelResource.checkARoom(roomNumber);
                        System.out.println("Set the price for this room: ");
                        Double price = sc.nextDouble();

                        System.out.println("Set the room type:: ");
                        System.out.println("1: single bed");
                        System.out.println("2: double bed");
                        int j = sc.nextInt();

                        while (canRun) {
                            if (j == 1) {
                                RoomType type = RoomType.SINGLE;
                                IRoom room = new Room(roomNumber, price, type, false);
                                List<IRoom> rooms = new ArrayList<>();
                                rooms.add(room);
                                adminResource.addRoom(rooms);
                                break;
                            } else if (j == 2) {
                                RoomType type = RoomType.DOUBLE;
                                IRoom room = new Room(roomNumber, price, type, false);
                                List<IRoom> rooms = new ArrayList<>();
                                rooms.add(room);
                                adminResource.addRoom(rooms);
                                break;
                            } else {
                                System.out.println("The answer you input is invalid. Please try again.");
                                j = sc.nextInt();
                            }
                        }

                        System.out.println("Would you like to add another room? Please enter the answer: (Y/N)");
                        String a = sc.next();
                        while (!a.isEmpty()) {
                            if (a.equalsIgnoreCase("Y")) {
                                break;
                            } else if (a.equalsIgnoreCase("N")) {
                                keepAdding = false;
                                break;
                            } else if (a != "N" || a != "n" && a != "Y" || a != "y") {
                                System.out.println("The answer you input is invalid. Please try again.");
                                a = sc.next();
                            }
                        }
                    }

                case 5:

                    System.out.println("< Already back to the Main Menu >");
                    System.out.println("-----------------------------------------------------------");
                    mainMenu.start();

            }


        }


    }

}