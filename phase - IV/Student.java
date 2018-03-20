
public class Student extends Customer{

	public Student(String customerCode, String Type, String personCode, String name, Address customerAddress) {
		super(customerCode, Type, personCode, name, customerAddress);
		// TODO Auto-generated constructor stub
	}
	public Student() {
		
	}
	/*
	 * method returning tax from product list 
	 */
	@Override
	Double getTax() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * method returning tax from product list taking 
	 * subtotal as argument
	 */
	@Override
	Double getDiscount(double subTotal) {
		return ((subTotal)*0.08);
	}

	/*
	 * method returning tax from product list taking 
	 * subtotal as argument
	 */
	@Override
	Double getAdditionalFee() {
		// TODO Auto-generated method stub
		return 6.75;	}

}
