

import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;



public class Library {
	
	public static final int LIBRARY_OWNER_ID = 0; // ownerId of the library is 0. i.e. not rented.  
	private final String BookListURL = "./books.dat";
	private final String UserListURL = "./users.dat";

	
	private ArrayList<Book> bookList;

	
	Library(){ //constructor
		bookList = new ArrayList<Book>();

	}
	
	
	//void saveBooks() throws IOException{
		
	//	FileOutputStream fout = new FileOutputStream(BookListURL);
	//	ObjectOutputStream oos = new ObjectOutputStream(fout);
	//	oos.writeObject(bookList);

	//}
	
	
	

	

	 
	
	
	
	
		
}
