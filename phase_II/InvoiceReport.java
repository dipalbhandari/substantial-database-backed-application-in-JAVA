import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InvoiceReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FlatFileReader fr = new FlatFileReader();
		
		InvoiceOutput io = new InvoiceOutput();
		System.out.print(io.getFl());
		try(  PrintWriter out = new PrintWriter("data/output.txt")){
		    //out.println( io.getFl() );
		    out.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
