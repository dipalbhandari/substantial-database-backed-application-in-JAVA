import java.util.ArrayList;

public class Services extends Product {
	private String parkinFee;     //declaring the instance variable
	private String name ;
	private String cost ;

	//creating the constructor
	public Services(String code, String type, String parkinFee) {
		super(code, type);
		this.parkinFee=parkinFee;
	}	
	
	public  Services(String code) {
		super(code);
	}


	
	@Override
	public String toString() {
		if(type.equals("P"))
			return code +":" + type + ":" + parkinFee+")";
		else 
		return code +":" + type + ":" + name + ":" + cost+")";
	}


	public Services(String code, String type, String name, String cost) {
		super(code, type);
		this.name = name;
		this.cost = cost;
	}
	/*
	 * method returning subtotal from product list taking 
	 * code and quantity as argument
	 */


	@Override
	double subTotal(String code, String qty) {
		
		return 0;
	}
	/*
	 * method returning tax from product list taking 
	 * code and quantity as argument
	 */
	@Override
	double getTax(String code,String qty) {
		
		return 0;
	}
	/*
	 * method returning grandTotal from product list taking 
	 * code and quantity as argument
	 */
	@Override
	double grandTotal(String price, String tax) {
		return 0;
	}




}
