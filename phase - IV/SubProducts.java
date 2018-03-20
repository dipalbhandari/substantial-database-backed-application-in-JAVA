
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
	 * creating constructor with different arguments
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	
}
