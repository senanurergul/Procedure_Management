package org.example;

import Classes.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Procedure> procedures = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getValidChoice(scanner);

            scanner.nextLine();

            switch (choice) {
                case 1:
                    Library.addProcedure(procedures, scanner);
                    break;
                case 2:
                    Library.displayProcedures(procedures);
                    break;
                case 3:
                    Library.searchProcedure(procedures, scanner);
                    break;
                case 4:
                    Library.updateProcedure(procedures, scanner);
                    break;
                case 5:
                    Library.deleteProcedure(procedures, scanner);
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Add procedure");
        System.out.println("2. Display procedures");
        System.out.println("3. Search procedure");
        System.out.println("4. Update procedure");
        System.out.println("5. Delete procedure");
        System.out.println("6. Exit");
    }

    private static int getValidChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
