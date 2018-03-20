import java.util.ArrayList;

public class Season extends Tickets {
	FlatFileReader fr = new FlatFileReader();  //creating object from flatfilereader

	
	private String name;
	private String startDate;
	private String endDate ;
	private String cost;
	
	
	
	public Season(String code, String type, String name, String startDate, String endDate, String cost) {
		super(code);
		this.name = name;
		this.type=type;   
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
	}

	public Season (String code) {
		super(code);
		}


	@Override
	public String toString() {
		return code +":"+ type+ ":"+name + ":" + startDate + ":" + endDate + ":" + cost
				+ ":" + ")";
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getCost() {
		return cost;
	}



	public void setCost(String cost) {
		this.cost = cost;
	}

	
	/*
	 * method returning subtotal from product list taking 
	 * code and quantity as argument
	 */
	@Override
	public double subTotal(String code,String qty)
	{
		ArrayList<Product> pro= fr.readProduct();
		double Qty = Double.parseDouble(qty);
		String[] line = pro.toString().split("\\)");
		for(int i=0;i<line.length;i++) {
				String data[] =line[i].split(":");
			if(data[0].contains(code)) {
					double price = Double.parseDouble(data[5]);
					return price*Qty;
				}
				}
		
		return 0;
	}
	
	/*
	 * method returning tax from product list taking 
	 * code and quantity as argument
	 */

	@Override  double getTax(String code,String qty) {
		
					return ((subTotal(code,qty)*4)/100);
				}
		
	/*
	 * method returning grandtotal from product list taking 
	 * code and quantity as argument
	 */
	@Override double grandTotal(String code,String qty){
		return (subTotal(code,qty)+getTax(code,qty));
		}
}
