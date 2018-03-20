import java.util.ArrayList;

public class General extends Customer {
		
	
		FlatFileReader fr = new FlatFileReader();  //creating object from FlatFileReader class
		ArrayList<Invoice> inv = fr.invoiceReader();  //reading data from invoice arraylist

		
		/*
		 * creating constructor for general
		 */
	public General(String customerCode, String Type, String personCode, String name, Address customerAddress) {
		super(customerCode, Type, personCode, name, customerAddress);
	
	}
	
	
	
	public General() {
		
	}

	
	/*
	 * methods that are override from the super class 
	 * 
	 */
	@Override
	Double getTax() {
		
		return null;
	}

	@Override
	Double getDiscount(double subTotal) {
		
		return (double) 0;
	}

	@Override
	Double getAdditionalFee() {
		
		return (double) 0;
	}

}
