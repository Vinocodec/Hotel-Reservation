package test;

import model.Customer;

public class Tester {
    public static void main(String[] args) throws IllegalAccessException {

        Customer customer = new Customer("first", "last", "j@domain.com");

        System.out.println(customer);

        Customer test = new Customer("first", "last", "email");

    }
}
