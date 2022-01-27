package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * Represents the customer service.
 */
public class CustomerService {

    public static Collection<Customer> customers = new HashSet<>();

    private static CustomerService customerService = null;

    /**
     * Create a new object of the customer service.
     */
    private CustomerService() {

    }

    public void addCustomer(String firstName, String lastName, String email) {
        customers.add(new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail) {
        Optional<Customer> customer = customers.stream().filter(c -> customerEmail.equals(c.getEmail())).findFirst();
        return customer.orElse(null);
    }

    public Collection<Customer> getAllCustomers() {
        return customers;
    }

    public Customer checkCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(customerEmail)) {
                System.out.println("The customer with this email address is already existed.");
                return customer;
            }
        }
        return null;
    }

    public static CustomerService getInstance() {
        if(customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

}

