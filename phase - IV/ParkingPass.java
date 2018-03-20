import java.util.ArrayList;

public class ParkingPass extends Services {
	Reader fr = new Reader();

private String parkinFee;     //declaring the instance variable

	
	//creating the constructor
	public ParkingPass(String code, String type, String parkinFee) {
		
		super(code, type, parkinFee);
		this.parkinFee=parkinFee;

	}
	public ParkingPass(String code) {
		super(code);
	}
	@Override
	//toString method
	public String toString() {
		return code +":"+type+":"+parkinFee +")";
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
					double price = Double.parseDouble(data[2]);
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
					double price = Double.parseDouble(data[2]);
					return price*Qty;
				}
				}
		
		return 0;
	}
	
	/*
	 * method returning tax
	 */

	@Override  double getTax(String code,String qty) {
					return ((subTotal(code,qty)*4)/100);
				}
	
	double getTax(String code,double qty) {
		return ((subTotal(code,qty)*4)/100);
	}
	
	/*
	 * method returning grandtotal taking code and quantity as arguments
	 */
	@Override double grandTotal(String code,String qty){
		return (subTotal(code,qty)+getTax(code,qty));
		}
	
	public String getParkinFee() {
		return parkinFee;
	}
	public void setParkinFee(String parkinFee) {
		this.parkinFee = parkinFee;
	}
	
	
	
}