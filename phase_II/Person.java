import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Person {	

	private String personCode;    //personCode
	private String firstName;   //firstName
	private String lastName;   //lastName
	private Address address;   //address
	private String [] email;  //emailAddress

	
	/*
	 * creating a constructor
	 */
	public Person(String personCode , String firstName , String lastName , Address address , String em){
		super();
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		String temp=em;
		this.email =temp.split(",");
	
		getEmail((email.length-1),email);
	}
	
	/*
	 * creating a constructor
	 */
	
	public Person(String personCode , String firstName , String ln , Address address ){
		super();
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName= lastName;
		this.address = address;

	}
	public String getpersonCode() {
		return personCode;
	}
	public void setpersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getlastName() {
		return lastName;
	}
	public void setLn(String ln) {
		this.lastName = lastName;
	}


	@Override
	public String toString() {
		if(email==null) {
			return personCode  + ":" + firstName + ":"+ lastName + ":" + address ;
		}
		else 
			return  personCode  + ":" + firstName + ":"+ lastName + ":" + address + ":" + Arrays.toString(email) ;

	}
	public Address getaddress() {
		return address;
	}
	
	/*
	 * creating a method for storing email address to 
	 * constructor
	 */
	public  void getEmail(int number,String[] x) {
		String[] str=new String[number];
		for(int i=0;i<number;i++) {
			str[i]=x[i];

		}
	}

}