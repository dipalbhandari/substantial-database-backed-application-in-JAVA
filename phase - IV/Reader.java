import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Reader {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/asaxena";


	static final String USER = "asaxena";
	static final String PASS = "Jotu5K";

	public ArrayList<Person> readPerson(){
		ArrayList<Person> personList = new ArrayList<Person>();	
		Connection conn = null;
		Statement stmt = null,stmt2=null;
		PreparedStatement ps = null,ps2=null;

		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			String sql = "select * from Country join Address on Country.AddressID = Address.AddressID\n" + 
					"Join Persons on Address.PersonID = Persons.PersonID ";
			ps = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);
			String sql2 = "select * from Email Join Persons on Email.PersonID= Persons.PersonID where PersonCode = ?";

			while(rs.next()) {

				String code = rs.getString("PersonCode");
				String lastName = rs.getString("LastName");
				String firstName = rs.getString("FirstName");
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String zipCode = rs.getString("Zip");
				String country = rs.getString("CountryName");
				String emailAddress="";
				ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, code);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()) {
					emailAddress += rs2.getString("EmailName")+",";
				}
				Address address = new Address(street , city, state , zipCode , country );
				Person per = new Person(code , lastName , firstName ,address , emailAddress );
			//	System.out.println(per);
				personList.add(per);
			}
			conn.close();
			rs.close();
			ps.close();
		//	ps2.close();
			stmt.close();
			stmt2.close();


		}catch(SQLException se){
			se.printStackTrace();}
		catch(Exception e){

			e.printStackTrace();
		}
		return personList;
	}
	public ArrayList<Customer> readCustomer(){
		ArrayList<Customer> customerList = new ArrayList<Customer>();	
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;

		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql="select * from Customer join Address on Customer.CustomerID = Address.CustomerID\n" + 
					"join Persons on  Persons.PersonID=Customer.PersonId\n" + 
					"join Country on  Country.AddressID = Address.AddressID" ;
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String custcode =  rs.getString("CustomerCode");
				String type = rs.getString("CustomerType");
				String personCode= rs.getString("PersonCode");
				String name = rs.getString("CustomerName");
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String zipCode = rs.getString("Zip");
				String country = rs.getString("CountryName");

				Address address = new Address(street , city, state , zipCode , country );

				if(type.equals("G")) {
					Customer cust =new General(custcode,"General",personCode,name, address );
					customerList.add(cust);
				}
				else {
					Customer cust =new Student(custcode,"Student",personCode,name, address );
					customerList.add(cust);
				}	
				


			}
			conn.close();
			rs.close();
			ps.close();
			stmt.close();
		}catch(SQLException se){
			se.printStackTrace();}
		catch(Exception e){

			e.printStackTrace();
		}
		return customerList;
	}

	public ArrayList<Invoice> readInvoice(){
		ArrayList<Invoice> InvoiceList = new ArrayList<Invoice>();	
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null,ps2=null;

		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql="Select * from Invoices join Customer on Customer.CustomerID=Invoices.CustomerID\r\n" + 
					"Join Persons on Customer.PersonID = Persons.PersonID";
			ps = conn.prepareStatement(sql);
			String sql2 = "select * from Product Join Invoices on Product.InvoiceID= Product.ProductID where InvoiceCode = ?";

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String code =  rs.getString("InvoiceCode");
				String custCode = rs.getString("CustomerCode");
				String personCode= rs.getString("PersonCode");
				ArrayList<SubProducts> ProductList = new ArrayList<SubProducts>();	
				ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, code);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()) {
					if(rs.getString("SubProduct")==null){
						SubProducts sub= new SubProducts(rs2.getString("ProductCode"),rs2.getString("Quantity"));
						ProductList.add(sub);
					}
					else {
						SubProducts sub = new SubProducts(rs2.getString("ProductCode"),rs2.getString("Quantity"),rs2.getString("SubProduct"));
						ProductList.add(sub);
					}
				}
				Invoice Inv = new Invoice(code,custCode,personCode,rs.getString("InvoiceDate"),ProductList);
				InvoiceList.add(Inv);
			}
			conn.close();
			rs.close();
			ps.close();
			ps2.close();
			stmt.close();
		}catch(SQLException se){
			se.printStackTrace();}
		catch(Exception e){

			e.printStackTrace();
		}
		return InvoiceList;
	}

	public ArrayList<Product> readProduct(){
		ArrayList<Product> Products = new ArrayList<Product>();
		Connection conn = null;
		Statement stmt = null,stmt2=null;
		//PreparedStatement ps = null,ps2=null;

		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			String sql="Select *from Products \r\n" + 
					"join Product on Products.ProductID=Product.ProductID\r\n" + 
					"Join Invoices on Product.InvoiceID = Invoices.InvoicesID;";
			String sql2 ="select * from Product";
			String sql3 ="Select *from Products \r\n" + 
					"	join Product on Products.ProductID=Product.ProductID\r\n" + 
					"	Join Invoices on Product.InvoiceID = Invoices.InvoicesID\r\n" + 
					"   join Address on Address.AddressID=Products.AddressID\r\n" + 
					"	join Country on Country.AddressID=Address.AddressID;";
			
			ResultSet rs2 = stmt.executeQuery(sql2);
			while(rs2.next()){
				if(rs2.getString("ProductType").equalsIgnoreCase("M")) {
					ResultSet rs = stmt2.executeQuery(sql3);
					rs.next();
					Address address = new Address(rs.getString("Street") , rs.getString("City"), rs.getString("State")
							, rs.getString("Zip") , rs.getString("CountryName") );
					Movie m = new Movie(rs.getString("ProductCode"), rs.getString("ProductType"), rs.getString("MovieTime"),
							rs.getString("ProductName"),address,rs.getString("ScreenNo"),rs.getString("Price"));
					Products.add(m); 
					rs.close();
				}
				else if(rs2.getString("ProductType").equalsIgnoreCase("S")) {
					ResultSet rs = stmt2.executeQuery(sql);
					rs.next();
					Season s = new Season(rs.getString("ProductCode"),rs.getString("ProductType"), rs.getString("ProductName"),
							rs.getString("StartDate"),rs.getString("EndDate"),rs.getString("Price"));
					Products.add(s);
					rs.close();
				}else if(rs2.getString("ProductType").equalsIgnoreCase("P")) {
					ResultSet rs = stmt2.executeQuery(sql);
					rs.next();
					ParkingPass p = new ParkingPass(rs.getString("ProductCode"),rs.getString("ProductType"),rs.getString("Price"));
					Products.add(p);
					rs.close();
				}
				else {
					ResultSet rs = stmt2.executeQuery(sql);
					rs.next();
					Refreshment r = new Refreshment(rs.getString("ProductCode"),rs.getString("ProductType"), rs.getString("ProductName"),rs.getString("Price"));
					Products.add(r);
					rs.close();
				}
				
			}
			conn.close();
			rs2.close();
			stmt.close();
			stmt2.close();
		}catch(SQLException se){
			se.printStackTrace();}
		catch(Exception e){

			e.printStackTrace();
		}
		return Products;
	}

}
