import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Season extends Tickets {
	Reader fr = new Reader();

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
	public double subTotal(String code,double qty)
	{
		ArrayList<Product> pro= fr.readProduct();
		double Qty = qty;
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
		return ((subTotal(code,qty)*6)/100);
	}
	
	
	/*
	 * method returning grandtotal from product list taking 
	 * code and quantity as argument
	 */
	@Override double grandTotal(String code,String qty){
		return (subTotal(code,qty)+getTax(code,qty));
	}
	double getTax(String code,String qty,String date) {
		return ((subTotal(code,qty,date)*6)/100);
	}
	
	double grandTotal(String code,String qty,String date){
		return (subTotal(code,qty,date)+getTax(code,qty,date));
	}
	/*
	 * 
	 * calculating subtotal based on date of purchase 
	 */
	public double subTotal(String code, String qty, String date) {
		ArrayList<Product> pr =fr.readProduct();
		String str[] = pr.toString().split("\\)");
		for(int i=0;i<str.length;i++) {
			String st [] = str[i].split(":");	
			if(st[0].contains(code)) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				Date da,startDate,endDate;
				try {
					da = df.parse(date);
					startDate = df.parse(st[3]);
					endDate = df.parse(st[4]);
					if(da.compareTo(startDate) >= 0 && da.compareTo(endDate) <=0) {
						double de=TimeUnit.DAYS.convert(endDate.getTime()-startDate.getTime(),TimeUnit.MILLISECONDS);
						double nu=TimeUnit.DAYS.convert(endDate.getTime()-da.getTime(),TimeUnit.MILLISECONDS);
						double Qty = Double.parseDouble(qty);
						String[] line = pr.toString().split("\\)");
						for(int j=0;j<line.length;j++) {
							String data[] =line[j].split(":");
							if(data[0].contains(code)) {
								double price = Double.parseDouble(data[5]);
								return (nu/de*price+8)*Qty;
							}
						}
					}
					else {
						double Qty = Double.parseDouble(qty);
						String[] line = pr.toString().split("\\)");
						for(int j=0;j<line.length;i++) {
							String data[] =line[j].split(":");
							if(data[0].contains(code)) {
								double price = Double.parseDouble(data[5]);
								return (price+8)*Qty;
							}
						}
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
}
