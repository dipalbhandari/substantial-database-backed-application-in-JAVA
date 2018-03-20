
public class ParkingPass extends Product {
	
	private String parkinFee;     //declaring the instance variable

	
	//creating the constructor
	public ParkingPass(String code, String type, String parkinFee) {
		super(code, type);
		this.parkinFee = parkinFee;
	}

	@Override
	//toString method
	public String toString() {
		return "ParkingPass [parkinFee=" + parkinFee + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
