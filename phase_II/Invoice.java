import java.util.ArrayList;

public class Invoice {

	private String invoiceCode;
	private String customerCode;
	private String salesPersonCode;
	private String date;
	private ArrayList<SubProducts> productList;
/*
 * creating construtor for invoice 
 */
	public Invoice(String invoiceCode, String customerCode, String salesPersonCode, String date,
			ArrayList<SubProducts> productList) {
		this.invoiceCode =invoiceCode;
		this.customerCode = customerCode;
		this.salesPersonCode=salesPersonCode;
		this.date=date;
		this.productList = productList;
	}
	
	public void setProductList(ArrayList<SubProducts> productList) {
		this.productList = productList;
	}
	
	/*
	 * crating the string builder and appending all the productlist to it 
	 */
	public StringBuilder getPro() {
		StringBuilder list = new StringBuilder();
		for(SubProducts pr:productList) {
			list.append(pr.toString()+",");
			
		}
		return list;
	}

	//method for converting to to string
	@Override
	public String toString() {
		return invoiceCode + "+" + customerCode + "+"+ salesPersonCode + "+" + date + "+" + getPro()+ ")";
	}

}
