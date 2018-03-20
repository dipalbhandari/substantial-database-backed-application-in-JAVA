import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlatFileReader {

	public ArrayList<Person> readPerson(){
		Scanner sc =null;
		ArrayList<Person> personList = new ArrayList<Person>();

		try{

			sc = new Scanner(new File("Persons.dat"));
			sc.nextLine();
			while(sc.hasNext()){
				String line = sc.nextLine();
				String data[] = line.split(";");
				if(data.length==4){
					String code = data[0];
					String name = data[1];
					String name2[] =  name.split(",");
					String lastName = name2[0];
					String firstName = name2[1];
					String add = data[2];
					String add1[] = add.split(",");
					String street = add1[0];
					String city = add1[1];
					String state = add1[2];
					String zipCode = add1[3];
					String country = add1[4];
					String emailAddress = data[3];


					Address address = new Address(street , city, state , zipCode , country );
					Person per = new Person(code , lastName , firstName ,address , emailAddress );
					personList.add(per);
				}
				else {
					String code = data[0];
					String name = data[1];
					String name2[] =  name.split(",");
					String lastName = name2[0];
					String firstName = name2[1];
					String add = data[2];
					String add1[] = add.split(",");
					String street = add1[0];
					String city = add1[1];
					String state = add1[2];
					String zipCode = add1[3];
					String country = add1[4];
					Address address = new Address(street , city, state , zipCode , country);
					Person per = new Person(code , lastName , firstName ,address );
					personList.add(per);
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.print("File Not Found!");
		}
		sc.close(); 
		/**for(Person p: personList){
			System.out.println(p.toString());

		}**/
		return personList;
		
	}



	public static ArrayList<Product> readProduct(){
		Scanner scc=null;
		ArrayList<Product> Products = new ArrayList<Product>();
		try{
			scc = new Scanner(new File("Products.dat"));
			scc.nextLine();
			while(scc.hasNextLine()){
				String line = scc.nextLine();
				String data[] = line.split(";");
				if(data[1].equalsIgnoreCase("M")){
					String code = data[0];
					String dateTime= data[2];
					String movieName = data[3];
					String add1[] = data[4].split(",");
					String street = add1[0].trim();
					String city = add1[1];
					String state = add1[2];
					String zipCode = add1[3];
					String country = add1[4].trim();
					Address address = new Address(street , city, state , zipCode , country );
					String sNo = data [5];
					String pPP = data [6];
					Movie m = new Movie(code, data[1], dateTime,movieName,address,sNo,pPP);
					Products.add(m);
				}
				else if(data[1].equalsIgnoreCase("S")){

					String code = data[0];
					String name = data[2];
					String startDate = data[3];
					String endDate = data [4];
					String cost = data [5];
					Season s = new Season(code,data[1], name,startDate,endDate,cost);
					Products.add(s);
				}
				else if(data[1].equalsIgnoreCase("p")){

					String code = data[0];
					String parkingFee = data[1];
					ParkingPass p = new ParkingPass(code, data[1] ,parkingFee);
					Products.add(p);
				}
				else{

					String code = data[0];
					String name = data [2];
					String cost = data [3];
					Refreshment r = new Refreshment(code,data[1] ,name,cost);
					Products.add(r);
				}

			}

		}catch(FileNotFoundException e){
			System.out.print("File Not Found!");
		}
		scc.close();
		/**for(Product c : Products){
			System.out.println(c.toString());

		}**/
		return Products;

	}

	public ArrayList<Customer> readCustomer(){

		ArrayList<Customer> readCustomer = new ArrayList<Customer>();
		Scanner scnr =null;

		try{

			scnr = new Scanner(new File("Customers.dat"));
			scnr.nextLine();
			while(scnr.hasNext()){
				String line = scnr.nextLine();
				String data[] = line.split(";");
				String custcode = data[0];
				String type = data[1];
				String personCode=data[2];
				String name = data[3];
				String add = data[4];
				String add1[] = add.split(",");
				String street = add1[0];
				String city = add1[1];
				String state = add1[2];
				String zipCode = add1[3];
				String country = add1[4];

				Address address = new Address(street , city, state , zipCode , country );


				Customer cust =new Customer(custcode,type,personCode,name, address );
				readCustomer.add(cust);

			}
		}

		catch(FileNotFoundException e){
			System.out.print("File Not Found!");
		}
		scnr.close();
		/**for(Customer c : readCustomer){
			System.out.println(c.toString());

		}**/
		return readCustomer;
	}



}
