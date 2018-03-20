
public class SubProducts {
					
	private String productName;
	private String qty;
	private String ref;
	/*
	 * creating constructor 
	 */
	public SubProducts(String productName, String qty) {
			this.productName=productName;
			this.qty=qty;
	}
	
	/*
	 * creatin constructor with different arguments
	 */
	public SubProducts(String productName,String qty , String ref) {
		this.productName=productName;
		this.ref=ref;
		this.qty=qty;
	}
	
	
	/*
	 * toString method returning quantity and reference code
	 */

	@Override
	public String toString() {
		if(ref!=null)
		return productName + ":" + qty + ":" + ref ;
		else
			return  productName + ":" + qty;
			
	}
	
	
}
