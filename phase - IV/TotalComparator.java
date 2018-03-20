import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

public class TotalComparator implements Comparator<Invoice>{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/asaxena";


	static final String USER = "asaxena";
	static final String PASS = "Jotu5K";

	@Override
	public int compare(Invoice inv1 , Invoice inv2) {
		if(inv1!=null&&inv2!=null) {
			return (int) (subTotal(inv1)-subTotal(inv2));
		}
		else return 0;
	}
	public double subTotal(Invoice in) {
		double pr[] =getPrice(in.getInvoiceCode());
		double qt[] =getQty(in.getInvoiceCode());
		double sub=0;
		for(int i=0;i<qt.length;i++) {
			sub+=pr[i]*qt[i];
		}
		return sub;	
	}
	public double [] getPrice(String code) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		double[] price = new double[100];
		int count=0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql="Select Price from Products \r\n" + 
					"join Product on Products.ProductID = Product.ProductID\r\n" + 
					"Join Invoices on Product.InvoiceID= Invoices.InvoicesID where Invoices.InvoiceCode = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				price[count]=rs.getDouble("Price");
				count++;
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
		return price;
	}
	public double [] getQty(String code) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		double[] qty= new double[100];
		int count=0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql="Select Quantity from Product\r\n" + 
					"Join Invoices on Product.InvoiceID=Invoices.InvoicesID where Invoices.InvoiceCode = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				qty[count]=rs.getDouble("Quantity");
				count++;
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
		return qty;
	}
}
