package api;

import model.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class MainMenu {

    private static MainMenu mainMenu = null;
    private final static AdminMenu adminMenu = new AdminMenu();
    private final static HotelResource hotelResource = HotelResource.getInstance();

    public Collection<IRoom> availableRooms = new HashSet<>();
    public Collection<IRoom> recommendRooms = new HashSet<>();

    public static void main(String[] args) throws ParseException {
        MainMenu main = new MainMenu();
        main.start();
    }

    public void start() throws ParseException {

        try {
            boolean canRun = true;

            while (canRun) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                int i = Integer.parseInt(scanner.next());

                switch (i) {
                    case 1:

                        Scanner sc = new Scanner(System.in);
                        System.out.println("--------------------Reservation System--------------------");
                        System.out.println("Please enter the date you would like to check in: (mm/dd/yyyy)");
                        String checkIn = sc.next();
                        Date inputCheckInDate = new SimpleDateFormat("MM/dd/yyyy").parse(checkIn);

                        System.out.println("Please enter the date you would like to check out: (mm/dd/yyyy)");
                        String checkOut = sc.next();
                        Date inputCheckOutDate = new SimpleDateFormat("MM/dd/yyyy").parse(checkOut);

                        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
                        Calendar calendar = Calendar.getInstance();
                        Date today = date.parse(date.format(calendar.getTime()));

                        if (inputCheckInDate.equals(today) || inputCheckInDate.after(today) && inputCheckOutDate.after(inputCheckInDate)) {
                            System.out.println("There are all available rooms below:");
                            availableRooms = hotelResource.findARoom(inputCheckInDate, inputCheckOutDate);

                            for (IRoom r : availableRooms) {
                                System.out.println(r);
                            }

                            if (availableRooms.isEmpty()) {

                                Calendar checkInDate = Calendar.getInstance();
                                checkInDate.setTime(inputCheckInDate);
                                checkInDate.add(Calendar.DAY_OF_YEAR, 7);

                                Calendar checkOutDate = Calendar.getInstance();
                                checkOutDate.setTime(inputCheckOutDate);
                                checkOutDate.add(Calendar.DAY_OF_YEAR, 7);

                                Date recommendCheckIn = checkInDate.getTime();
                                Date recommendCheckOut = checkOutDate.getTime();

                                System.out.println("There is not an available room during your expected time.");

                                System.out.println("--------------Recommendation Date--------------");
                                System.out.println("Check-in: " + recommendCheckIn);
                                System.out.println("Check-out: " + recommendCheckOut);

                                recommendRooms = hotelResource.findARoom(recommendCheckIn, recommendCheckOut);
                                for (IRoom rm : recommendRooms) {
                                    System.out.println(rm);
                                }

                                if (recommendRooms.isEmpty()) {
                                    System.out.println("No Rooms are available now");
                                    break;
                                }
                                break;
                            }
                        } else {
                            System.out.println("The date you input is invalid.");
                            break;
                        }

                        System.out.println("Do you wanna make a reservation? Please enter your answer. (Y/N)");
                        String s = sc.next();

                        while (!s.isEmpty()) {
                            if (s.equalsIgnoreCase("Y")) {
                                System.out.println("Do you have an account? Please enter your answer. (Y/N)");
                                s = sc.next();

                                while (!s.isEmpty()) {
                                    if (s.equalsIgnoreCase("Y")) {
                                        System.out.println("Enter the account email that you register with:");

                                        String email = sc.next();
                                        if (hotelResource.getCustomer(email) == null) {
                                            System.out.println("You have not registered yet.");
                                            break;
                                        }

                                        System.out.println("Which Room would you like to reserve? (Please enter the Room Number)");
                                        String inputRoomNumber = sc.next();
                                        IRoom room = hotelResource.getARoom(inputRoomNumber);
                                        hotelResource.bookARoom(email, room, inputCheckInDate, inputCheckOutDate);
                                        hotelResource.getCustomersReservations(email);
                                        System.out.println("You have reserved this room successfully!");
                                        break;

                                    } else if (s.equalsIgnoreCase("N")) {
                                        System.out.println("Please enter your First Name: ");
                                        String firstName = sc.next();

                                        System.out.println("Please enter Last Name: ");
                                        String lastName = sc.next();

                                        System.out.println("Email Address: ");
                                        String email = sc.next();

                                        hotelResource.checkCustomer(email);
                                        hotelResource.createACustomer(firstName, lastName, email);

                                        System.out.println("Which Room would you like to reserve? (Please enter the Room Number)");
                                        String roomNumber = sc.next();
                                        IRoom room = hotelResource.getARoom(roomNumber);
                                        hotelResource.bookARoom(email, room, inputCheckInDate, inputCheckOutDate);
                                        hotelResource.getCustomersReservations(email);
                                        System.out.println("You have reserved this room successfully!");
                                        break;

                                    } else if (s != "N" || s != "n" && s != "Y" || s != "y") {
                                        System.out.println("The answer you input is invalid. Please try again:");
                                        s = sc.next();
                                    }
                                }
                                break;
                            } else if (s.equalsIgnoreCase("N")) {
                                break;
                            } else if (s != "N" || s != "n" && s != "Y" || s != "y") {
                                System.out.println("The answer you input is invalid. Please try again:");
                                s = sc.next();
                            }
                        }
                        break;

                    case 2:
                        System.out.println("--------------------Reservation System--------------------");
                        System.out.println("What is your email address with the reservation?");
                        Scanner userEmail = new Scanner(System.in);
                        String email = userEmail.next();
                        hotelResource.getCustomersReservations(email);
                        break;

                    case 3:
                        System.out.println("--------------------Customer System--------------------");
                        System.out.println("Please enter your First Name:");
                        Scanner input = new Scanner(System.in);
                        String firstName = input.next();

                        System.out.println("Please enter Last Name: ");
                        String lastName = input.next();

                        System.out.println("Email Address: ");
                        String customerEmail = input.next();

                        if (hotelResource.checkCustomer(customerEmail) != null) {
                            break;
                        }
                        hotelResource.createACustomer(firstName, lastName, customerEmail);
                        break;

                    case 4:
                        System.out.println("--------------------Admin Menu--------------------");
                        adminMenu.start();
                        break;

                    case 5:
                        System.out.println("Thank you for using!");
                        canRun = false;
                        System.exit(0);
                        break;
                }
            }
        }catch(Exception e){
            System.out.println("The input is invalid, please try again.");
            start();
        }
    }

    public static MainMenu getInstance() {
        if(mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

}
