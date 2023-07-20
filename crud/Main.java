package crud;
import java.util.*;
import java.sql.*;

public class Main {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";

	public static void main(String[] args) throws SQLException{
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","admin");
		Statement st = con.createStatement();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int choice = 999;
			System.out.println("\nSelect operation-\n 1. View books\n 2. Add new book\n 3. Update book data\n 4. Delete a book entry\n 0. Exit");
			System.out.print("~ User choice: ");
			String input = sc.nextLine();
			try {choice = Integer.parseInt(input);}
			catch(NumberFormatException e) {System.out.println("! Warning: Enter a valid choice");}
			switch(choice) {
			case 0:
				exitConsole();
				sc.close();
				System.exit(0);
			case 1:
				try {
					String view_query = "select * from allbooks";
					ResultSet res = st.executeQuery(view_query);
					if(!res.next()) System.out.println(ANSI_RED+"! Warning: Empty table"+ANSI_RESET);
					else {
						System.out.println("\nAll Books: ");
						do {
							System.out.println("---["+res.getInt(1)+", "+res.getString(2)+", "+res.getString(3)+", "+res.getString(4)+", "+res.getInt(5)+"]");
						}while (res.next());
					}
				}
				catch(SQLException e) {System.out.println(ANSI_RED+"! Warning: Table doesn't exist"+ANSI_RESET);}
				break;
				
			case 2:
				String insert_query = "insert into allbooks (title,author,category,publish_year) values(?,?,?,?)";
				PreparedStatement psti = con.prepareStatement(insert_query);
				System.out.print("\nAdd new book data-\nTitle: ");
				try {
					String title = sc.nextLine();
					System.out.print("Author: ");
					String author = sc.nextLine();
					System.out.print("Category: ");
					String category = sc.nextLine();
					System.out.print("Publish year: ");
					int publish_year = Integer.parseInt(sc.nextLine());
					Book newbook = new Book(title,author,category,new Date(1,1,publish_year));
					psti.setString(1,newbook.getTitle());
					psti.setString(2,newbook.getAuthor());
					psti.setString(3,newbook.getCategory());
					psti.setInt(4,newbook.getPublishyr());
					int ex = psti.executeUpdate();
					if(ex!=0) System.out.println(ANSI_GREEN+"! Update: "+ex+" row(s) affected"+ANSI_RESET);
					else if(ex==0) System.out.println(ANSI_YELLOW+"Failed to insert new entry"+ANSI_RESET);
					else System.out.println(ANSI_YELLOW+"Unknown error occured"+ANSI_RESET);
				}catch(NumberFormatException e) {System.out.println(ANSI_RED+"! Warning: Invalid date input format\n"+ANSI_RESET);}
				break;
				
			case 3:
				int update_choice = 999;
				while(update_choice!=0) {
					update_choice = 999;
					System.out.println("\tSelect update field option-\n\t 1.Title\n\t 2.Author\n\t 3.Category\n\t 4.Publish year\n\t 0.Cancel");
					System.out.print("\t~ User choice: ");
					try {update_choice = Integer.parseInt(sc.nextLine());}
					catch(NumberFormatException e) {System.out.println(ANSI_RED+"! Warning: Enter option number"+ANSI_RESET);}
					String update_query = "";
					switch(update_choice) {
						case 0:
							System.out.println(ANSI_RED+"\t\t! Warning: Operation cancelled"+ANSI_RESET);
							break;
						case 1:
							update_query = "update allbooks set title=? where title=? and author=?";
							PreparedStatement pstu1 = con.prepareStatement(update_query);
							System.out.print("\tTitle: ");
							String ttl_prev = sc.nextLine();
							System.out.print("\tAuthor: ");
							String ttl_a = sc.nextLine();
							System.out.print("\tSet title: ");
							String ttl_new = sc.nextLine();
							pstu1.setString(1, ttl_new);
							pstu1.setString(2, ttl_prev);
							pstu1.setString(3, ttl_a);
							int ex1 = pstu1.executeUpdate();
							if(ex1!=0) System.out.println(ANSI_GREEN+"\tUpdate: "+ex1+" row(s) affected\n"+ANSI_RESET);
							else if(ex1==0) System.out.println(ANSI_YELLOW+"\t\tNo such entries found. Check if provided values exist in table\n"+ANSI_RESET);
							else System.out.println(ANSI_YELLOW+"\t\tUnknown error occured"+ANSI_RESET);
							break;
						case 2:
							update_query = "update allbooks set author=? where title=? and author=?";
							PreparedStatement pstu2 = con.prepareStatement(update_query);
							System.out.print("\tTitle: ");
							String auth_t = sc.nextLine();
							System.out.print("\tAuthor: ");
							String auth_prev = sc.nextLine();
							System.out.print("\tSet author: ");
							String auth_new = sc.nextLine();
							pstu2.setString(1, auth_new);
							pstu2.setString(2, auth_t);
							pstu2.setString(3, auth_prev);
							System.out.println(pstu2);
							int ex2 = pstu2.executeUpdate();
							if(ex2!=0) System.out.println(ANSI_GREEN+"\tUpdate: "+ex2+" row(s) affected\n"+ANSI_RESET);
							else if(ex2==0) System.out.println(ANSI_YELLOW+"\t\tNo such entries found. Check if provided values exist in table\n"+ANSI_RESET);
							else System.out.println(ANSI_YELLOW+"\t\tUnknown error occured"+ANSI_RESET);
							break;
						case 3:
							update_query = "update allbooks set category=? where title=? and author=?";
							PreparedStatement pstu3 = con.prepareStatement(update_query);
							System.out.print("\tTitle: ");
							String catg_t = sc.nextLine();
							System.out.print("\tAuthor: ");
							String catg_a = sc.nextLine();
							System.out.print("\tSet category: ");
							String catg_new = sc.nextLine();
							pstu3.setString(1, catg_new);
							pstu3.setString(2, catg_t);
							pstu3.setString(3, catg_a);
							int ex3 = pstu3.executeUpdate();
							if(ex3!=0) System.out.println(ANSI_GREEN+"\tUpdate: "+ex3+" row(s) affected\n"+ANSI_RESET);
							else if(ex3==0) System.out.println(ANSI_YELLOW+"\t\tNo such entries found. Check if provided values exist in table\n"+ANSI_RESET);
							else System.out.println(ANSI_YELLOW+"\t\tUnknown error occured"+ANSI_RESET);
							break;
						case 4:
							update_query = "update allbooks set publish_year=? where title=? and author=?";
							PreparedStatement pstu4 = con.prepareStatement(update_query);
							System.out.print("\tTitle: ");
							String pby_t = sc.nextLine();
							System.out.print("\tAuthor: ");
							String pby_a = sc.nextLine();
							System.out.print("\tSet publish year: ");
							String pby_new = sc.nextLine();
							pstu4.setString(1, pby_new);
							pstu4.setString(2, pby_t);
							pstu4.setString(3, pby_a);
							int ex4 = pstu4.executeUpdate();
							if(ex4!=0) System.out.println(ANSI_GREEN+"\tUpdate: "+ex4+" row(s) affected\n"+ANSI_RESET);
							else if(ex4==0) System.out.println(ANSI_YELLOW+"\t\tNo such entries found. Check if provided values exist in table\n"+ANSI_RESET);
							else System.out.println(ANSI_YELLOW+"\t\tUnknown error occured"+ANSI_RESET);
							break;
						default:
							System.out.println(ANSI_RED+"! Warning: Select update option from menu below"+ANSI_RESET);
							continue;
					}
				}
				break;
				
			case 4:
				String delete_query = "delete from allbooks where title=? and author=?";
				PreparedStatement pstd = con.prepareStatement(delete_query);	
				System.out.print("Title: ");
				String del_t = sc.nextLine();
				System.out.print("Author: ");
				String del_a = sc.nextLine();
				pstd.setString(1,del_t);
				pstd.setString(2,del_a);
				int ex = pstd.executeUpdate();
				if(ex!=0) System.out.println(ANSI_GREEN+"Update: "+ex+" row(s) affected\n"+ANSI_RESET);
				else if(ex==0) System.out.println(ANSI_YELLOW+"No such entries found. Check if provided values exist in table\n"+ANSI_RESET);
				else System.out.println(ANSI_YELLOW+"Unknown error occured"+ANSI_RESET);
				break;
				
			default:
				System.out.println(ANSI_RED+"! Warning: Select a choice from menu below"+ANSI_RESET);
				break;
			}
			
				
		}
	}
	
	public static void exitConsole(){
		try {
			Thread.sleep(200);
			System.out.print(ANSI_GREEN+".");
			Thread.sleep(200);
			System.out.print(".");
			Thread.sleep(200);
			System.out.print(".");
			Thread.sleep(500);
			System.out.println("Exit successful"+ANSI_RESET);
		}catch(InterruptedException e) {}
	}

}
