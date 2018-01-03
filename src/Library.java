


import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;



public class Library {
	
	public static final int LIBRARY_OWNER_ID = 0; // ownerId of the library is 0. i.e. not rented.  
	private final String BookListURL = "./books.dat";
	private final String UserListURL = "./users.dat";

	
	private ArrayList<Book> bookList;
	private ArrayList<User> userList;
	
	Library(){ //constructor
		bookList = new ArrayList<Book>();
		userList = new ArrayList<User>();
	}
	
	
	void saveBooks() throws IOException{
		
		FileOutputStream fout = new FileOutputStream(BookListURL);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(bookList);

	}
	
	void saveUsers() throws IOException{
		
		FileOutputStream fout = new FileOutputStream(UserListURL);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(userList);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	void loadBooks() throws Exception{
		
		FileInputStream fis = new FileInputStream(BookListURL);
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    bookList = (ArrayList<Book>)ois.readObject();
	    ois.close(); 
	}
	
	@SuppressWarnings("unchecked")
	void loadUsers() throws Exception{
		
		FileInputStream fis = new FileInputStream(UserListURL);
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    userList = (ArrayList<User>)ois.readObject();
	    ois.close();
	}
	 
	boolean addUser(User user){
		return userList.add(user);
	}
	
	boolean loginCheck(String userName, String password){
		Iterator<User> userItr = userList.iterator();
		while(userItr.hasNext()){
			User tempUser = userItr.next();
			if(userName.equals(tempUser.getUserName()) && password.equals(tempUser.getPassword())){
				return true;
			}//end if
		}//end while
		return false;
		
	}//login check 
	
	
	User login(String userName, String password){
		
		User currentUser = null; //initialize
		Iterator<User> userItr = userList.iterator();
		while(userItr.hasNext()){
			User tempUser = userItr.next();
			if(tempUser.getUserName().equals(userName) && tempUser.getPassword().equals(password)){
				currentUser = tempUser;
				break;
			}//if login successful
		}//while
		return currentUser; //return null if login failed.
	}
	
	
	
		
}
