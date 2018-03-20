import java.util.ArrayList;

public class Refreshment extends Services {
	FlatFileReader fr = new FlatFileReader();  //creating object from FlatFileReader

	
	
	
	private String name ;
	private String cost ;
	public Refreshment(String code, String type, String name, String cost) {
		super(code, type, cost);
		this.name = name;
		this.cost = cost;
	}
	
	
	public Refreshment(String code) {
		// TODO Auto-generated constructor stub
		super(code);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return code +":" + type+":"+ name + ":" + cost+")";
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
				double price = Double.parseDouble(data[3]);
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