import java.util.ArrayList;
 
public class Invoice {

	private String invoiceCode;
	private String customerCode;
	private String salesPersonCode;
	private String date;
	private ArrayList<SubProducts> productList;
	private SubProducts sub;
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
	
	public String[] prod() {
		int count=0;
		String[] temp = new String[productList.size()];
		System.out.println(productList.size());
		for(SubProducts pr:productList) {
		if(pr.getRef()!=null) {
			System.out.println("hello");
			temp[count]=pr.getProductName()+","+pr.getRef()+","+pr.getQty();
			count++;
		}
		else {
			System.out.println("hello");
			temp[count]=pr.getProductName()+","+pr.getQty();
			count++;
		}}
		return temp;
		
	}
 	//method for converting to to string
	@Override
	public String toString() {
		return invoiceCode + "+" + customerCode + "+"+ salesPersonCode + "+" + date + "+" + getPro()+ ")";
	}
	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getSalesPersonCode() {
		return salesPersonCode;
	}

	public void setSalesPersonCode(String salesPersonCode) {
		this.salesPersonCode = salesPersonCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<SubProducts> getProductList() {
		return productList;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public void getTotal()
	{
		
	}
}
