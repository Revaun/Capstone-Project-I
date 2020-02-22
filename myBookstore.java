package myBookstore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;  
import java.sql.*;
import java.util.Scanner;

public class myBookstore {
	public static void main(String[] args) {
		// Continue displaying the menu until user enters 0 to exit 
		while(true) {
			System.out.println("Welcome to the Book Store:");
			System.out.println("1. Enter book");
			System.out.println("2. Update book");
			System.out.println("3. Delete book");
			System.out.println("4. Search books");
			System.out.println("0. Exit");
			System.out.print("Please enter a number: ");
			
			// Capture user input and run function based on the number chosen by user
			String menuScreen = JOptionPane.showInputDialog("Welcome to the Book Store:\n 1. Enter book\n 2. Update book\n 3. Delete book \n 4.Search books \n 0. Exit");
			int numberInput = Integer.parseInt(menuScreen);
			
			if (numberInput == 1) {
				// Get all details from user to store in the variables in enterBook
				String option1string = JOptionPane.showInputDialog("Please enter the ID of the book");
		        int option1ID = Integer.parseInt(option1string);
		        String option1Title = JOptionPane.showInputDialog("Please enter the title of the book");
		        String option1Author = JOptionPane.showInputDialog("Please enter the author of the book");
		        String option1Qtystring = JOptionPane.showInputDialog("Please enter the quantity of the book");
		        int option1Qty = Integer.parseInt(option1Qtystring);
		        enterBook(option1ID, option1Title, option1Author, option1Qty);
				}
			
			else if (numberInput == 2) {
				// Get the id of the book the user wishes to update
				String option2string = JOptionPane.showInputDialog("Please enter the ID of the book you want to update");
		        int option2Input = Integer.parseInt(option2string);
				updateBook(option2Input);
				}
			
			else if (numberInput == 3) {
				// Get the id of the book the user wishes to delete
				String option3string = JOptionPane.showInputDialog("Please enter the ID of the book");
		        int option3Input = Integer.parseInt(option3string);
				deleteBook(option3Input);
				}
			
			else if (numberInput == 4) {
				// Get the id of the book the user wishes to search
				String option4string = JOptionPane.showInputDialog("Please enter the ID of the book");
		        int option4Input = Integer.parseInt(option4string);
				searchBooks(option4Input);
				}
			
			else if (numberInput == 0) {
				// Exit the program should the user enter 0
				System.out.println("You have exited the Book Store");
				System.exit(0);
				}
			
			else {
				System.out.println("We do not recognize the input and therefore program will quit.");
				System.exit(0);
				}
			}
		}
	
	// Enter Book - Option 1
	public static void enterBook(int id, String title, String author, int qty) {
		try (
				// Allocate a database 'Connection' object
				Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "demo" );
				
				// Allocate a 'Statement' object in the Connection
				Statement stmt = conn.createStatement();
				
				) {
			
			// Enter a new book using the variables from enterBook
			String sqlInsert = "insert into books " 
			+ "values (" + id + "," + '"' + title + '"' + "," + '"' + author + '"' +  "," + qty + ")";
			
			// Run the sql code to enter the book
			stmt.executeUpdate (sqlInsert);
			JOptionPane.showMessageDialog(null, "The book has been added to the bookstore");
			
			} catch (SQLException ex) {
				ex.printStackTrace();
				}
		
		// Close the resources - Done automatically using try-with-resources
		}
	
	// Update Book - Option No 2
	public static void updateBook(int id) {
	try (
			// Allocate a database 'Connection' object
			Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "demo");
			
			// Allocate a 'Statement' object in the Connection
			Statement stmt = conn.createStatement();
			) {
		
		// Ask user what about the book they want to update and store variable
		String confirmationOption2 = JOptionPane.showInputDialog("What do you want to update? Please enter only one of these: id, title, author, qty:");
		
		// Update only the ID
		if (confirmationOption2.equalsIgnoreCase("id")){
			// Ask the user what the new id must be and store the new id as integer
			String option2IDstring = JOptionPane.showInputDialog("What would you like the new id to be?");
	        int option2ID = Integer.parseInt(option2IDstring);	
			
			// Declare this new id in the sql string
			String idUpdate = "update books set id = "+ option2ID + " where id =" + id;
			// Update the id using the sql string
			stmt.executeUpdate(idUpdate);
			// Show the updated book details
			String strSelect = "select * from books where id = '"+ option2ID +"'";
			ResultSet rset = stmt . executeQuery ( strSelect );
			while ( rset . next ()) {
				int bookId = rset.getInt("id");
				String title = rset . getString ( "title" );
				String author = rset . getString ("author");
				int qty = rset.getInt("qty");
				JOptionPane.showMessageDialog(null, "The updated record is now: "+ bookId + ": " + title + " - " + author + " - " + qty);
				}
			
			} 
		
		// Update only the title
		else if (confirmationOption2.equalsIgnoreCase("title")) {
			// Ask user for the new title	
			String option2Title = JOptionPane.showInputDialog("Please enter the title of the book you want to update");
			// Declare the new title in the sql string
			String idUpdate = "update books set title = '"+ option2Title + "' where id =" + id;
			// Update the title using the sql string
			stmt.executeUpdate(idUpdate);
			// Show the updated book details
			String strSelect = "select * from books where id = '"+ id +"'";
			ResultSet rset = stmt . executeQuery ( strSelect );
			while ( rset . next ()) {
				int bookId = rset.getInt("id");
				String title = rset . getString ( "title" );
				String author = rset . getString ("author");
				int qty = rset.getInt("qty");
				JOptionPane.showMessageDialog(null, "The updated record is now: "+ bookId + ": " + title + " - " + author + " - " + qty);
				}
			
			}
		
		// Update only the author
		else if (confirmationOption2.equalsIgnoreCase("author")) {
			// Ask who the author is
			String option2Author = JOptionPane.showInputDialog("Please enter the author of the book you want to update");
			// Declare the new author in the sql string
			String idUpdate = "update books set author = '"+ option2Author + "' where id =" + id;
			// Update the author using the sql string
			stmt.executeUpdate(idUpdate);
			// Show the updated book details
			String strSelect = "select * from books where id = '"+ id +"'";
			ResultSet rset = stmt . executeQuery ( strSelect );
			while ( rset . next ()) {
				int bookId = rset.getInt("id");
				String title = rset . getString ( "title" );
				String author = rset . getString ("author");
				int qty = rset.getInt("qty");
				JOptionPane.showMessageDialog(null, "The updated record is now: "+ bookId + ": " + title + " - " + author + " - " + qty);
				}
			
			}
		
		// Update only the quantity
		else if (confirmationOption2.equalsIgnoreCase("qty")) {
			// Ask what the new quantity is
			String option2QtyString = JOptionPane.showInputDialog("What would you like the new quantity to be?");
	        int option2Qty = Integer.parseInt(option2QtyString);
			// Declare the new quantity in the sql string
			String idUpdate = "update books set id = "+ option2Qty + " where id =" + id;
			// Update the new quantity using the sql string
			stmt.executeUpdate(idUpdate);
			// Show the updated book details
			String strSelect = "select * from books where id = '"+ option2Qty +"'";
			ResultSet rset = stmt . executeQuery ( strSelect );
			while ( rset . next ()) {
				int bookId = rset.getInt("id");
				String title = rset . getString ( "title" );
				String author = rset . getString ("author");
				int qty = rset.getInt("qty");
				JOptionPane.showMessageDialog(null, "The updated record is now: "+ bookId + ": " + title + " - " + author + " - " + qty);
				}
			
			}
		
		else {
			JOptionPane.showMessageDialog(null, "Sorry, we did not understand that input");
			}
		
		} catch ( SQLException ex ) {
			ex . printStackTrace ();
			}
	
	// Close the resources - Done automatically by try-with-resources
	}
	
	// Delete book - Option 3
	public static void deleteBook(int id) {
	try (
			// Allocate a database 'Connection' object
			Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "demo" );
			
			// Allocate a 'Statement' object in the Connection
			Statement stmt = conn.createStatement();
			) {
		
		// Find the book to be deleted		
		String sqlRecord = "select * from books where id = '"+ id +"'";
		ResultSet confirmDeletion = stmt . executeQuery ( sqlRecord );
		// Move the cursor to the next row, return false if no more row
		while (confirmDeletion.next ()) {
			String title = confirmDeletion.getString ("title");
			String author = confirmDeletion.getString ("author");
			//Show the book to be deleted and ask for confirmation
			JOptionPane.showMessageDialog(null, "The record to be deleted is: " + title + " - " + author );
			}
		
		// Confirm with user if they really want to delete the book and store the variable
		String confirmationOption3 = JOptionPane.showInputDialog("Are you sure you want to delete the record? - yes or no: ");
		
		if (confirmationOption3.equalsIgnoreCase("yes")){
			// Store the id of the book to be deleted in the sql string
			String sqlDelete = "delete from books where id = '"+ id +"'";
			// Delete the book
			stmt.executeUpdate(sqlDelete);
			// Confirm that the book has been deleted
			JOptionPane.showMessageDialog(null, "Record has been deleted.");
			}
		
		else if (confirmationOption3.equalsIgnoreCase("no")) {
			JOptionPane.showMessageDialog(null, "Record will not be deleted.");
			}
		
		} catch ( SQLException ex ) {
			ex . printStackTrace ();
			}
	
	// Close the resources - Done automatically by try-with-resources
	}
	
	// Search Books function - Option No 4
	public static void searchBooks(int id) {
	try (
			// Allocate a database 'Connection' object
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "demo" );
			// Allocate a 'Statement' object in the Connection
			Statement stmt = conn.createStatement();
			
			) {
		
		// Declare the book to be searched in the sql string
		String strSelect = "select * from books where id = '"+ id +"'";
		ResultSet rset = stmt . executeQuery ( strSelect );
		// Show the details of the book
		while ( rset . next ()) {
			String title = rset . getString ("title");
			String author = rset . getString ("author");
			JOptionPane.showMessageDialog(null, "A book was found! \n" + id + ": " + title + " - " + author);
			}
		
		} catch ( SQLException ex ) {
			ex . printStackTrace ();
			}
	
	// Close the resources - Done automatically by try-with-resources
	}
}