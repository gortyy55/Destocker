package tn.CodeCommanders.test;

import tn.CodeCommanders.Entities.User;
import tn.CodeCommanders.Service.ServiceUser;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Create an instance of ServiceUser
        ServiceUser serviceUser = new ServiceUser();

        // Test adding a user
     /*   User newUser = new User();
        //newUser.setId(1);
        newUser.setEmail("test@example.com");
        newUser.setPassword("testpassword");
        newUser.setRole("user");
        newUser.setFirstname("John");
        newUser.setLastname("Doe");
        newUser.setAddress("123 Main St");
        newUser.setTelephone(123456789);
        newUser.setAnimal("Dog");
        newUser.setQuestion("What is your favorite color?");
        newUser.setBan(0);

        serviceUser.ajouter(newUser);
        }*/


        // Test getting a user by ID
        int userIdToRetrieve = 2;
        User retrievedUser = serviceUser.getOneByID(userIdToRetrieve);

        /*if (retrievedUser != null) {
            System.out.println("\nUser with ID " + userIdToRetrieve + ":");
            System.out.println(retrievedUser);
        } else {
            System.out.println("User with ID " + userIdToRetrieve + " not found.");
        }*/
       /* serviceUser.supprimer(retrievedUser);*/




        // Test updating a user
        retrievedUser.setFirstname("UpdatedFirstName");
        retrievedUser.setLastname("UpdatedLastName");
        serviceUser.modifier(retrievedUser);


        List<User> userList = serviceUser.getAll();
        System.out.println("All Users:");
        for (User user : userList) {
            System.out.println(user);
        }

        userList = serviceUser.getAll();
        System.out.println("\nAll Users after Deletion:");
        for (User user : userList) {
            System.out.println(user);
        }
    }



    }



