import java.util.ArrayList;

public abstract class Customer {

	
	//declaraing the instance variable
	private String customerCode;
	private String Type;                         
	private String personCode;
	private String name;
	private Address customerAddress;

	Reader person=new Reader(); //creating  the object from Reader class
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

	public void setPerson(Reader person) {
		this.person = person;
	}

	
	//method that return information that contains percode in person class
	public Person search(String personCode)
	{
		ArrayList<Person> per = person.readPerson();
		for(Person p : per){
			if(p.toString().contains(personCode))
				return p;
		}
		return null;


	}
	
	
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCustomerAddress() {
		String str[]=new String[5];
		str[0]=customerAddress.getSt();
		str[1]=customerAddress.getCity();
		str[2]=customerAddress.getState();
		str[3]=customerAddress.getZip();
		str[4 ]=customerAddress.getCountry();
		return str;
	}
	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}
	public Person getPrimaryContact() {
		return PrimaryContact;
	}
	public void setPrimaryContact(Person primaryContact) {
		PrimaryContact = primaryContact;
	}
	public String getCustomerCode() {
		return customerCode;
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

	
	abstract Double getTax();
	abstract Double getDiscount(double subTotal);
	abstract Double getAdditionalFee();


}
