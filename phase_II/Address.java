
public class Address {
	
	
	
	
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	
	
	/*
	 * Creating constructor for Address
	 */

	public Address(String street, String city , String state , String zip , String country){
		this.street = street;
		this .city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;

	}

	/*
	  Creating getter and setter method for Address
	 
	 */

	public String getSt() {
		return street;
	}
	public void setSt(String st) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	//Creating toString method
	@Override
	public String toString() {
		return street + " , " + city + ", " + state + " , " + zip + " , " + country;
	}



}
