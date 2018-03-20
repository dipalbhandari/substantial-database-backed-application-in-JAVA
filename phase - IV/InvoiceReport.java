import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class InvoiceReport {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/asaxena";


	static final String USER = "asaxena";
	static final String PASS = "Jotu5K";
	static Reader read = new Reader();
	public static void main(String[] args) {
		InvoiceList list = new InvoiceList();

		ArrayList<Invoice> arr = read.readInvoice();
		ArrayList<Person> per = read.readPerson();
		ArrayList<Product> pro = read.readProduct();
		for(Product p: pro) {
			System.out.println(p);
		}
		for(Invoice a: arr) {
			list.add(a);
			
		}
		System.out.println("||||||||||||||||||||");
		InvoiceOutput oi = new InvoiceOutput(list);
		//list.print();


	}
}
