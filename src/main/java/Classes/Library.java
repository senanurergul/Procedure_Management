package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Library {

    public static void addProcedure(List<Procedure> procedures, Scanner scanner) {
        System.out.println("Enter the procedure name:");
        String procedureName = scanner.nextLine();
        System.out.println("Do you want to use current date? Please answer 'yes' or 'no':");
        String answer = scanner.nextLine();
        String procedureDate = null;
        if (answer.equalsIgnoreCase("yes")) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            procedureDate = formatter.format(new Date());
        } else if (answer.equalsIgnoreCase("no")) {
            System.out.println("Please enter the date like 12/02/2022:");
            String inputDate = scanner.nextLine();
            procedureDate = validateDate(inputDate);
            if (procedureDate == null) {
                System.out.println("Invalid date format. Please use the correct format.");
            }
        } else {
            System.out.println("Invalid choice. Please restart the program and enter 'yes' or 'no':");
        }
        System.out.println("Enter the procedure author:");
        String procedureAuthor = scanner.nextLine();
        System.out.println("Do you want to enter a file path or a direct description? (Enter 'path' or 'description')");
        String fileOrDescription = scanner.nextLine();
        String procedureDescription = null;
        if (fileOrDescription.equalsIgnoreCase("path")) {
            System.out.println("Please enter the file path:");
            String filePath = scanner.nextLine();
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("File does not exist. Please try again.");
            }

            procedureDescription = readFileContent(filePath);
        } else if (fileOrDescription.equalsIgnoreCase("description")) {
            System.out.println("Please enter your description (type 'END' on a new line to finish):");
            StringBuilder descriptionBuilder = new StringBuilder();
            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("END")) {
                descriptionBuilder.append(line).append("\n");
            }
            procedureDescription = descriptionBuilder.toString();
        } else {
            System.out.println("Invalid choice. Please restart the program and enter 'path' or 'description'.");
        }
        Procedure procedure = new Procedure(procedureName, procedureDate, procedureAuthor, procedureDescription);
        procedures.add(procedure);
        System.out.println("Procedure added successfully!");
    }

    public static void displayProcedures(List<Procedure> procedures) {
        if (procedures.isEmpty()) {
            System.out.println("No procedures available.");
            return;
        }

        for (Procedure procedure : procedures) {
            System.out.println(procedure);
        }
    }

    public static void searchProcedure(List<Procedure> procedures, Scanner scanner) {
        System.out.println("Enter the procedure to search (at least 3 letters):");
        String searchTerm = scanner.nextLine();
        if (searchTerm.length() < 3) {
            System.out.println("Please enter at least 3 letters.");
        } else {
            List<String> results = searchProcedures(procedures, searchTerm);
            if (results.isEmpty()) {
                System.out.println("No procedures found.");
            } else {
                System.out.println("Found procedures:");
                for (String result : results) {
                    System.out.println(result);
                }
            }
        }
    }

    public static void updateProcedure(List<Procedure> procedures, Scanner scanner) {
        if (procedures.isEmpty()) {
            System.out.println("There is no procedure to update.");
        } else {
            System.out.println("Enter the name of the procedure to update:");
            String oldProcedureName = scanner.nextLine();
            Procedure procedureToUpdate = getProcedureByName(procedures, oldProcedureName);

            if (procedureToUpdate == null) {
                System.out.println("Procedure is not found!");
            } else {
                System.out.println("What do you want to change? (name/date/author/path/description)");
                String preference = scanner.nextLine();

                switch (preference.toLowerCase()) {
                    case "name":
                        System.out.println("Enter new name:");
                        String newName = scanner.nextLine();
                        updateProcedure(procedureToUpdate, newName, null, null, null);
                        break;
                    case "date":
                        System.out.println("Enter new date:");
                        String newDate = scanner.nextLine();
                        updateProcedure(procedureToUpdate, null, newDate, null, null);
                        break;
                    case "author":
                        System.out.println("Enter new author:");
                        String newAuthor = scanner.nextLine();
                        updateProcedure(procedureToUpdate, null, null, newAuthor, null);
                        break;
                    case "path":
                        System.out.println("Enter new file path:");
                        String newPath = scanner.nextLine();
                        String newContent = readFileContent(newPath);
                        updateProcedure(procedureToUpdate, null, null, null, newContent);
                        break;
                    case "description":
                        System.out.println("Enter new description:");
                        String newDescription = scanner.nextLine();
                        updateProcedure(procedureToUpdate, null, null, null, newDescription);
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }

                System.out.println("Procedure updated successfully!");
            }
        }
    }

    public static void deleteProcedure(List<Procedure> procedures, Scanner scanner) {
        System.out.println("Enter procedure name to delete:");
        String name = scanner.nextLine();

        Iterator<Procedure> iterator = procedures.iterator();
        while (iterator.hasNext()) {
            Procedure procedure = iterator.next();
            if (procedure.getProcedureName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Procedure deleted successfully.");
                return;
            }
        }
        System.out.println("Procedure not found.");
    }

    public static String validateDate(String inputDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        try {
            Date date = formatter.parse(inputDate);
            return formatter.format(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static List<String> searchProcedures(List<Procedure> procedures, String searchTerm) {
        List<String> results = new ArrayList<>();
        for (Procedure procedure : procedures) {
            if (procedure.getProcedureName().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(procedure.getProcedureName());
            }
        }
        return results;
    }

    public static void updateProcedure(Procedure procedure, String newName, String newDate, String newAuthor, String newDescription) {
        if (newName != null) {
            procedure.setProcedureName(newName);
        }
        if (newDate != null) {
            procedure.setProcedureDate(newDate);
        }
        if (newAuthor != null) {
            procedure.setProcedureAuthor(newAuthor);
        }
        if (newDescription != null) {
            procedure.setProcedureDescription(newDescription);
        }
    }

    public static String readFileContent(String path) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static Procedure getProcedureByName(List<Procedure> procedures, String name) {
        for (Procedure procedure : procedures) {
            if (procedure.getProcedureName().equalsIgnoreCase(name)) {
                return procedure;
            }
        }
        return null;
    }
}
