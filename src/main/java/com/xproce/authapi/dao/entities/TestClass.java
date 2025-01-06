package com.xproce.authapi.dao.entities;

public class TestClass {
    Customer customer = Customer.builder()
            .email("Ayoub@gmail.com")
            .password("123456")
            .role("user")
            .build();

    CustomerAuth customerAuth = CustomerAuth.builder().customer(customer).build();


    TestClass(){
        customerAuth.getPassword();
        customerAuth.getUsername();
    }



    public static void main(String[] args) {
        new TestClass();
    }
}
