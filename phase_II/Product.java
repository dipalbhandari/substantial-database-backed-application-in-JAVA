import java.io.File;
import java.util.Scanner;

public abstract class Product {
	protected String code;
	protected String type;

/*
 * creating a constructor
 */

	public Product(String code, String type) {

		this.code = code;
		this.type = type;
	}
	public Product(String code) {
		this.code=code;
	}

	/*
	 * creating abstract method to calculate subtotal , code and grandtotal
	 */
	abstract double subTotal(String code,String qty);

	abstract double getTax(String code,String qty);

	abstract double grandTotal(String price,String tax);



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	double grandTotal() {
		// TODO Auto-generated method stub
		return 0;
	}





}