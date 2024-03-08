package tn.CodeCommanders.test;

import tn.CodeCommanders.Service.ServiceUser;

import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static final String CURRENCY = "TND";
    public static void main(String[] args) {

        ServiceUser userService = new ServiceUser();

        // Appel de la fonction countUsersByRole pour obtenir le nombre d'utilisateurs par rôle
        Map<String, Integer> roleCountMap = userService.countUsersByRole();

        // Affichage des résultats
        for (Map.Entry<String, Integer> entry : roleCountMap.entrySet()) {
            System.out.println("Role: " + entry.getKey() + ", Nombre d'utilisateurs: " + entry.getValue());
        }

        }
    }
