package main;

import database.CorrectSQLiteDBController;
import database.IncorrectSQLiteDBController;
import model.Book;
import xml.sax.IncorrectSAXParser;
import xml.transformer.IncorrectXMLTransformerController;
import xml.xmldoc.IncorrectXMLDocBuilderController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class AppMain {
    public static void main(String[] args) {
        System.out.println("Please enter your username and password.");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("username:");
        try {
            String username = br.readLine();
            System.out.print("password:");

            String password = br.readLine();

            IncorrectSQLiteDBController incorrectDBC = IncorrectSQLiteDBController.getInstance();
            CorrectSQLiteDBController correctDBC = CorrectSQLiteDBController.getInstance();

            incorrectDBC.initDBConnection();
            correctDBC.initDBConnection();

            boolean successfulLogIn = incorrectDBC.checkLogin(username, password);
            System.out.println("Ergebnis des unsicher implementierten Abgleichs: " + successfulLogIn);
            successfulLogIn = correctDBC.checkLogin(username, password);
            System.out.println("Ergebnis des sicher implementierten Abgleichs: " + successfulLogIn);

            incorrectDBC.shutDownDBConnection();
            correctDBC.shutDownDBConnection();

            if(successfulLogIn){
                System.out.println("LogIn Successful");

                System.out.println("Enter the of what you want to do.\n1 - View books\n2 - View students\n3 - View employees");
                int i = Integer.parseInt(br.readLine());
                switch (i){
                    case 1:
                        showBooks();
                        break;
                    case 2:
                        showStudents();
                        break;
                    case 3:
                        showEmployees();
                        break;
                }
            }
        } catch (IOException | SQLException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void showEmployees() {
        IncorrectXMLTransformerController controller = new IncorrectXMLTransformerController();
        controller.readEmployeesFromXML();
    }

    private static void showStudents() {
        IncorrectXMLDocBuilderController controller = new IncorrectXMLDocBuilderController();
        controller.readStudentsFromXML();
    }

    private static void showBooks() {
        IncorrectSAXParser parser = new IncorrectSAXParser();
        List<Book> books = parser.getBooks();
        books.forEach(book -> System.out.println(book.getTitle()));
    }
}
