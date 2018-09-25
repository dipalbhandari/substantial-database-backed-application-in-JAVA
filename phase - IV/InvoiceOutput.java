import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.Iterator;


import java.lang.AutoCloseable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class InvoiceOutput{		
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/asaxena";


	static final String USER = "asaxena";
	static final String PASS = "Jotu5K";

	/*
	 * creating arrylist to store tax , fee , discount and total
	 */
	ArrayList<Double> sub = new ArrayList();
	ArrayList<Double> fee = new ArrayList();
	ArrayList<Double> taxes = new ArrayList();
	ArrayList<Double> discount = new ArrayList();
	ArrayList<Double> total = new ArrayList();
	int count =0,count2=0;
	int num=0;
	StringBuilder fl = new StringBuilder();  

	Reader fr = new Reader();


	/*
	 * This class design to print all the output in the formatted 
	 */
	public InvoiceOutput(InvoiceList list){

		String custName=null;
		String perName = null;
		String perName2 = null;
		String custType=null;
		String custCode=null;
		String custAddress=null;
		String add[]=new String[5];
		this.fl.append("============================\n");
		this.fl.append("Executive Summary Report\n");
		this.fl.append("============================\n");

		this.fl.append(String.format("%-10s %-40s %-25s %-10s %-10s %-10s %-10s %-10s\n","Invoice","Customer","SalesPerson","Subtotal","Fee","Taxes","Discount","Total"));

		ArrayList<Invoice> inv = fr.readInvoice();
		ArrayList<Customer> cust = fr.readCustomer();		
		ArrayList<Person> per = fr.readPerson();
		java.util.Iterator<Invoice> it =  list.iterator();
		while(it.hasNext()) {
			Invoice in =it.next();
			for(Customer c :cust) {

				if(in.getCustomerCode().equalsIgnoreCase(c.getCustomerCode())) {
					custName=c.getName()+"  ["+c.getType()+"]";
				}
			}
			for(Person p:per) {
				if(in.getSalesPersonCode().equalsIgnoreCase(p.getpersonCode())){
					perName=p.getfirstName()+" "+p.getlastName();
				}
			}
			System.out.println(custName + "     "+ perName);
		}

		java.util.Iterator<Invoice> it2 =  list.iterator();
		while(it2.hasNext()){
			Invoice in2 =it2.next();
			System.out.printf("\n %-5s %-10s","INVOICE",in2.getInvoiceCode()+"\n");
			this.fl.append(String.format("\n %-5s %-10s","INVOICE",in2.getInvoiceCode()+"\n"));
			this.fl.append("============================\n");
			System.out.println("============================\n");
			for(Person p:per) {
				if(in2.getSalesPersonCode().equalsIgnoreCase(p.getpersonCode())){
					perName=p.getfirstName()+" "+p.getlastName();

				}



				for(Customer c:cust) 
				{
					if(in2.getCustomerCode().equalsIgnoreCase(c.getCustomerCode()))
					{
						custName=c.getName();
						custType=c.getType();
						custCode=c.getCustomerCode();

					}
					if(p.getpersonCode().equals(c.getPersonCode())) {
						perName2=p.getlastName()+", " +p.getfirstName();
						add=p.getCustomerAddress();
					}
				}
			}
			//System.out.println(custName+"\n"+custType+"\n");
			System.out.printf("%-10s %-20s \n","SalesPerson",perName);
			System.out.println("Customer Info\n");
			System.out.printf("%2s %-10s %-1s \n","",custName,custCode);
			System.out.printf("%2s %-10s \n","",custType);
			System.out.println(perName2+"\n"+add[2]);
			System.out.println(add[1]+" "+ add[2]+" "+add[3]+"  "+add[4]);
			System.out.println("---------------------------");
			this.fl.append(String.format("%-10s %-20s \n","SalesPerson",perName));
		}




		this.fl.append("\n---------------------------\n");
		this.fl.append(String.format("%-10s %-50s %10s %10s %10s \n","Code", "Item" ,"Subtotal", "tax", "total"));
		java.util.Iterator<Invoice> it3 =  list.iterator();
		while(it2.hasNext()){
			InvCal2(it2.next());
		}
	}

	public void InvCal2(Invoice inv){
		count=0;
		double sum =0 ,sum2=0;
		double sumTax =0, sumTax2=0;
		double sumTotal =0 ,sumTotal2=0;
		//		String s1[] = inv.toString().split("\\+");
		double[] pr=getPrice(inv.getInvoiceCode());
		double[] qt=getQty(inv.getInvoiceCode());
		String[] type=getType(inv.getInvoiceCode());
		String[] sub=getSub(inv.getInvoiceCode());
		//		String s2[] = s1[4].split(",");
		String str []= inv.prod();
		int ticketNumber = 0;
		for(int i=0;i<pr.length;i++) {
			
			switch(type[i].charAt(0)){
			case 'M':{
				Movie m = new Movie(str[0]);
				sum=(m.subTotal(str[0],qt[i]));
				System.out.println(str[0]+"Movie");
				ticketNumber = (int)qt[i];
			}
		
			case 'P':{
				ParkingPass p = new ParkingPass(str[0]);
				sum=(p.subTotal(str[0],qt[i]));
				sumTax=p.getTax(str[0],qt[i]);
				System.out.println(str[0]+"Parking");
			}
			))-qty;
			
			case 'S':{
				Season s = new Season(str[0]);
				sum=(s.subTotal(str[0], qt[i]));
				System.out.println(str[0]+"Parking");
			}
			
			case 'R':{
				Refreshment r = new Refreshment(str[0]);
				
				System.out.println(str[0]+"Refreshment");
				if(ticketNumber==0) {
					sum=(r.subTotal(str[0], qt[i]));
					sumTax=((r.getTax(str[0],qt[i])));
				}else
					sum=((r.subTotal(str[0], qt[i]))-(0.05*(r.subTotal(str[0], qt[i]))));
					sumTax=((r.getTax(str[0], qt[i]))-(0.05*(r.getTax(str[0], qt[i]))));
					
			}
			
			}
			sumTotal=sum+sumTax+this.fee.get(count2)-this.discount.get(count2);
			this.fl.append(String.format("$%-10.2f $ %-10.2f $%-10.2f \n",sum,sumTax,sumTotal));
			sum2+=sum;
			sumTax2+=sumTax;
			sumTotal2+=sumTotal;

		}
		this.fl.append("=======================\n");
		this.fl.append(String.format("%-60s $%-10.2f $%-10.2f $%-10.2f \n","SubTotal",sum2,sumTax2,sumTotal2));

		
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
	public String [] getType(String code) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		String[] type= new String[100];
		int count=0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql="Select ProductType from Product\r\n" + 
					"Join Invoices on Product.InvoiceID=Invoices.InvoicesID where Invoices.InvoiceCode = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				type[count]=rs.getString("ProductType");
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
		return type;
	}
	public String [] getSub(String code) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		String[] sub= new String[100];
		int count=0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql="Select SubProduct from Product\r\n" + 
					"Join Invoices on Product.InvoiceID=Invoices.InvoicesID where Invoices.InvoiceCode = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				sub[count]=rs.getString("ProductType");
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
		return sub;
	}
}
