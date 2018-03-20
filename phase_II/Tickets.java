
public class Tickets extends Product {

	public Tickets(String code) {
		super(code);
		// TODO Auto-generated constructor stub
	}

	
	/*
	 * method returning subtotal from product list taking 
	 * code and quantity as argument
	 */
	@Override
	double subTotal(String code, String qty) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/*
	 * method returning tax from product list taking 
	 * code and quantity as argument
	 */
	@Override
	double getTax(String code, String qty) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	/*
	 * method returning grandTotal from product list taking 
	 * code and quantity as argument
	 */


	@Override
	double grandTotal(String price, String tax) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
