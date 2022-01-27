package model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a customer.
 */
public class Customer {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String emailRegex = "^(.+)@(.+).(.+)$";
    private final Pattern pattern = Pattern.compile(emailRegex);

    /**
     * Create a new object of customer.
     *
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param email the email of the customer
     */
    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("The email address you input is invalid.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "FirstName: " + firstName + " LastName: " + lastName + " || Email: " + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) obj;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}