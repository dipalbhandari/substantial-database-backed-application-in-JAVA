import java.util.ArrayList;

public class Customer {

	
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

	public void setPerson(FlatFileReader person) {
		this.person = person;
	}

	
	//method that return information that contains percode in person class
	public Person search(String personCode)
	{
		ArrayList<Person> per  = person.readPerson();
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
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

	public Address getCustAddress() {
		return customerAddress;
	}
	public void setCustAddress(Address custAddress) {
		this.customerAddress = customerAddress;
	}

	@Override//toString method
	public String toString() {
		return "Customer [customerCode=" + customerCode + ", Type=" + Type  + "  PrimaryContact= "+ PrimaryContact + ", name=" + name
				+ ", customerAddress=" + customerAddress + "]";
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

	public String getPerson() {
		return person.toString();
	}



}
