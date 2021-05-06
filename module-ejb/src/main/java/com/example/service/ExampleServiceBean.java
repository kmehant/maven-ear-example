package com.example.service;

import javax.ejb.Stateless;

@Stateless
public class ExampleServiceBean implements ExampleService {
 
    @Override
    public String whoAmI() {
        return "i'm ExampleService";
    }

}