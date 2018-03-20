import java.util.ArrayList;

public abstract class Customer {

	
	//declaraing the instance variable
	private String customerCode;
	private String Type;                         
	private String personCode;
	private String name;
	private Address customerAddress;

	FlatFileReader person=new FlatFileReader(); //creating  the object from FlatFileReader class
	Person PrimaryContact;
	
	//creating constructor
	public Customer (String customerCode ,String Type ,String personCode ,String name,Address customerAddress)
	{                       
		this.customerCode=customerCode;
		this.Type=Type;
		this.personCode=personCode;
		this.name=name;
		this.customerAddress=customerAddress;
		this.PrimaryContact=search(personCode);

	}
	public Customer() {
		
	}

	public void setPerson(FlatFileReader person) {
		this.person = person;
	}

	
	/*
	 * method returning person by searching personCode 
	 */
	public Person search(String personCode)
	{
		ArrayList<Person> per = person.readPerson();
		for(Person p : per){
			if(p.toString().contains(personCode))
				return p;
		}
		return null;


	}
	
	
	
	public String getcustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}


	@Override//toString method
	public String toString() {
		return customerCode + ":" + Type  + ":"+ PrimaryContact + ":" + name
				+ ":" + customerAddress +")";
	}
	public String getPerson() {
		return person.toString();
	}

	/*
	 * abstract methods to calculate tax , discount and additional fee basis on criteria 
	 */
	abstract Double getTax();
	abstract Double getDiscount(double subTotal);
	abstract Double getAdditionalFee();


}
