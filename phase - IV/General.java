import java.util.ArrayList;

public class General extends Customer {
		
	
		Reader fr=new Reader();
		
		ArrayList<Invoice> inv = fr.readInvoice(); //reading data from invoice arraylist

	public General(String customerCode, String Type, String personCode, String name, Address customerAddress) {
		super(customerCode, Type, personCode, name, customerAddress);
		/*
		 * creating constructor for general
		 */
	}
	public General() {
		
	}

	/*
	 * methods that are override from the super class 
	 * 
	 */
	@Override
	Double getTax() {
		// 
		if(inv.contains("INV0001"))
			{
			
			}
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
