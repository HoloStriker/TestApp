package main;

import database.CorrectSQLiteDBController;
import model.Book;
import xml.sax.CorrectSAXParser;
import xml.transformer.CorrectXMLTransformerController;
import xml.xmldoc.CorrectXMLDocBuilderController;

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

            CorrectSQLiteDBController incorrectDBC = CorrectSQLiteDBController.getInstance();
            CorrectSQLiteDBController correctDBC = CorrectSQLiteDBController.getInstance();

            incorrectDBC.initDBConnection();
            correctDBC.initDBConnection();

            boolean successfulLogIn = incorrectDBC.checkLogin(username, password);
            System.out.println(successfulLogIn);
            successfulLogIn = correctDBC.checkLogin(username, password);
            System.out.println(successfulLogIn);

            incorrectDBC.shutDownDBConnection();
            correctDBC.shutDownDBConnection();

            if(successfulLogIn){
                System.out.println("LogIn Successful");

                System.out.println("Enter the of what you want to do.\n1 - View books\n2 - View Students");
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
            System.err.println("Invalid Format!");
        }
    }

    private static void showEmployees() {
        CorrectXMLTransformerController controller = new CorrectXMLTransformerController();
        controller.loadEmployees();
    }

    private static void showStudents() {
        CorrectXMLDocBuilderController controller = new CorrectXMLDocBuilderController();
        //List<Student> students = controller.loadStudents();
        controller.loadStudents();
        //students.forEach(student -> System.out.println(student.getFirstName() + " " + student.getLastName()));
    }

    private static void showBooks() {
        CorrectSAXParser parser = new CorrectSAXParser();
        List<Book> books = parser.getBooks();
        books.forEach(book -> System.out.println(book.getTitle()));
    }
}
