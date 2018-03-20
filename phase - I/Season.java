
public class Season extends Product {
	
	private String name;
	private String startDate;
	private String endDate ;
	private String cost;
	
	
	
	public Season(String code, String type, String name, String startDate, String endDate, String cost) {
		super(code, type);
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
	}



	@Override
	public String toString() {
		return "Season [name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", cost=" + cost
				+ ", toString()=" + super.toString() + "]";
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





}
