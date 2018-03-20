
public class Refreshment extends Product {
	
	
	@Override
	public String toString() {
		return "Refreshment [name=" + name + ", cost=" + cost + ", toString()=" + super.toString() + "]";
	}
	private String name ;
	private String cost ;
	public Refreshment(String code, String type, String name, String cost) {
		super(code, type);
		this.name = name;
		this.cost = cost;
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

	
	
	
	

}