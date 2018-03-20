import java.util.Arrays;

public class Movie extends Product {
	 //initailizing the instance variable 
	private	String dateTime;
	private String movieName;
	private Address address;
	private String screenNo;
	private String pricePerUnit;
	
	
	//creating constructor 
	public Movie(String code, String type, String dateTime, String movieName, Address address, String screenNo, String pricePerUnit) {
		super(code, type);
		this.dateTime = dateTime;
		this.movieName = movieName;
		this.address = address;
		this.screenNo = screenNo;
		this.pricePerUnit = pricePerUnit;
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



	@Override
	//toString method 
	public String toString() {
		return "Movie [dateTime=" + dateTime + ", movieName=" + movieName + ", address=" + address + ", screenNo=" + screenNo
				+ ", pricePerUnit=" + pricePerUnit + ", toString()=" + super.toString() + "]";
	}
	
}
