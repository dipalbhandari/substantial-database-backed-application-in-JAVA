import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Movie extends Tickets {
	Reader fr = new Reader();
	//initailizing the instance variable 
	private	String dateTime;
	private String movieName;
	private Address address;
	private String screenNo;
	private String pricePerUnit;


	//creating constructor 
	public Movie(String code, String type, String dateTime, String movieName, Address address, String screenNo, String pricePerUnit) {	
		super(code);
		this.dateTime = dateTime;
		this.code=code;
		this.type=type;
		this.movieName = movieName;
		this.address = address;
		this.screenNo = screenNo;
		this.pricePerUnit = pricePerUnit;
	}
	public Movie(String code) {
		super(code);

	}


	//getter and setter method
	public String getDateTime() {
		return dateTime;
	}



	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}



	public String getMovieName() {
		return movieName;
	}



	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public String getscreenNo() {
		return screenNo;
	}



	public void setscreenNo(String sNo) {
		this.screenNo = screenNo;
	}



	public String getpricePerUnit() {
		return pricePerUnit;
	}



	public void setpPP(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	/*
	 * method returning subtotal from product list taking 
	 * code and quantity as argument
	 */

	@Override
	public double subTotal(String code,String qty)
	{
		ArrayList<Product> pro= fr.readProduct();
		double Qty = Double.parseDouble(qty);
		String[] line = pro.toString().split("\\)");
		for(int i=0;i<line.length;i++) {
			String data[] =line[i].split(":");
			if(data[0].contains(code)) {
				double price = Double.parseDouble(data[7]);
				return price*Qty;
			}
		}

		return 0;
	}
	
	public double subTotal(String code,double qty)
	{
		ArrayList<Product> pro= fr.readProduct();
		double Qty = qty;
		String[] line = pro.toString().split("\\)");
		for(int i=0;i<line.length;i++) {
			String data[] =line[i].split(":");
			if(data[0].contains(code)) {
				double price = Double.parseDouble(data[7]);
				return price*Qty;
			}
		}

		return 0;
	}
	/*
	 * method returning tax from product list taking 
	 * code and quantity as argument
	 */
	@Override  double getTax(String code,String qty) {
		return ((subTotal(code,qty)*6)/100);
	}
	/*
	 * method returning tax from product list taking 
	 * code and quantity as argument
	 */
	
	double getTax(String code,String qty,String date) {
		return ((subTotal(code,qty,date)*6)/100);
	}
	/*
	 * method returning grandtotal from product list taking 
	 * code ,data, quantity as argument
	 */
	@Override double grandTotal(String code,String qty){
			return (subTotal(code,qty)+getTax(code,qty));
	}
	 double grandTotal(String code,String qty,String date){
		 return (subTotal(code,qty,date)+getTax(code,qty,date));
	}

	@Override
	//toString method 
	public String toString() {
		return code +":"+type+":"+ dateTime + ":" + movieName + ":" + address + ":" + screenNo
				+ ":" + pricePerUnit + ")";
	}
	
	/*
	 * method calcultaing the subtotal by giving discount on tuesday and thursday 
	 * converting the given date format to days 
	 * and returning the total cost after discount
	 */
	public Double subTotal(String code, String qty, String date) {
		String inDate=date;
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
		Date dat;
		try {
			dat = dateformat.parse(inDate);
			DateFormat dayFormate=new SimpleDateFormat("EEEE"); 
			String day=dayFormate.format(dat);
			if(day.equalsIgnoreCase("Tuesday")||day.equalsIgnoreCase("Thursday")) {
				ArrayList<Product> pro= fr.readProduct();
				double Qty = Double.parseDouble(qty);
				String[] line = pro.toString().split("\\)");
				for(int i=0;i<line.length;i++) {
					String data[] =line[i].split(":");
					if(data[0].contains(code)) {
						double price = Double.parseDouble(data[7]);
						return ((price*Qty)-(0.07*price*Qty));

					}
				}
			}
			else {
				ArrayList<Product> pro= fr.readProduct();
				double Qty = Double.parseDouble(qty);
				String[] line = pro.toString().split("\\)");
				for(int i=0;i<line.length;i++) {
					String data[] =line[i].split(":");
					if(data[0].contains(code)) {
						double price = Double.parseDouble(data[7]);
						return price*Qty;
					}

				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Reader getFr() {
		return fr;
	}
	public void setFr(Reader fr) {
		this.fr = fr;
	}
	public String getScreenNo() {
		return screenNo;
	}
	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	public String getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

}
