import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceOutput {		
/*
 * creating arrylist to store tax , fee , discount and total
 */
	ArrayList<Double> sub = new ArrayList();
	ArrayList<Double> fee = new ArrayList();
	ArrayList<Double> taxes = new ArrayList();
	ArrayList<Double> discount = new ArrayList();
	ArrayList<Double> total = new ArrayList();
	int count =0,count2=0;
	int num=0;
	StringBuilder fl = new StringBuilder();

	FlatFileReader fr = new FlatFileReader();//creating object from flatfile reader 
	
	
	
	/*
	 * This class design to print all the output in the formatted 
	 */
	public InvoiceOutput(){
		
		String custName=null;
		String perName = null;
		this.fl.append("============================\n");
		this.fl.append("Executive Summary Report\n");
		this.fl.append("============================\n");

		this.fl.append(String.format("%-10s %-40s %-25s %-10s %-10s %-10s %-10s %-10s\n","Invoice","Customer","SalesPerson","Subtotal","Fee","Taxes","Discount","Total"));
		ArrayList<Invoice> inv = fr.invoiceReader();
		for(Invoice in : inv) {
			
			
			InvCal(in);
			String s1[] = in.toString().split("\\+");
			ArrayList<Customer> cust = fr.readCustomer();
			String str[] = TK(cust);
			for(int i=0;i<str.length;i++) {
				String st[] =Toke(str[i]);
				if(st[0].contains(s1[1])) {
					if(st.length==9) {
						if(st[1].equalsIgnoreCase("G")) {						
							custName = st[7]+"  "+ "[General]";

						}
						else {custName = st[7]+"  "+ "[Student]";

						}

					}
					else {
						if(st[1].equalsIgnoreCase("G")) {						
							custName = st[6]+"  "+ "[General]";

						}
						else {custName = st[6]+"  "+ "[Student]";
	

						}
					}
				}
			}	
			ArrayList<Person> per = fr.readPerson();
			String str1[] = TKP(per);
		/*
		 * iterating through the person list to get the name of salesperson
		 */
			for(int i=0;i<str1.length;i++) {
				String st[] =Toke(str1[i]);
				if(st[0].contains(s1[2])) {
					perName = st[2] + ", "+ st[1];
	
				}
			}
			this.fl.append(String.format("%-10s %-40s %-25s $%-10.2f $%-10.2f $%-10.2f $%-8.2f $%-8.2f \n"
					,s1[0],custName,perName,sub.get(num),fee.get(num),taxes.get(num),discount.get(num),total.get(num)));
				num++;
		}
		this.fl.append("===============================================================================================================================\n");
		this.fl.append(String.format("%-78s $%-10.2f $%-10.2f $%-10.2f $%-10.2f $%-10.2f ",
				"TOTAL",sum(sub),sum(fee),sum(taxes),sum(discount),sum(total)));

		this.fl.append(String.format("\n\n	INDIVIDUAL INVOICE DETAIL REPORT\n"));
		this.fl.append("============================\n");
		for(Invoice in : inv) {
			String s1[] = in.toString().split("\\+");
			this.fl.append(String.format("\n %-5s %-10s","INVOICE",s1[0]+"\n"));
			this.fl.append("============================\n");
			ArrayList<Person> per = fr.readPerson();
			String str1[] = TKP(per);
		
			for(int i=0;i<str1.length;i++) {
				String st[] =Toke(str1[i]);
				if(st[0].contains(s1[2])) {
					perName = st[2] + ", "+ st[1];
	
				}
				
			}
			this.fl.append(String.format("%-10s %-20s \n","SalesPerson",perName));
			this.fl.append(String.format("Customer Info\n"));
			/*
			 * this method iterates through the customer list 
			 * find the types , taxes , sbtotal , grandtotal 
			 */
			ArrayList<Customer> cust = fr.readCustomer();
			String str[] = TK(cust);
			for(int i=0;i<str.length;i++) {
				String st[] =Toke(str[i]);
				if(st.length==9) {
				if(st[0].contains(s1[1])) {
					this.fl.append(String.format("%2s %-10s %-1s \n","",st[7],st[0]));
					if(st[1].equalsIgnoreCase("G")) {						
						this.fl.append(String.format("%2s %-10s \n","","[General]"));}
					else {this.fl.append(String.format("%2s %-10s \n","","[Student]"));}
					this.fl.append(st[3]+st[4]+"\n"+st[8]);
				}}
				else {
					if(st[0].contains(s1[1])) {
					this.fl.append(st[6]+st[0]);
					if(st[1].equalsIgnoreCase("G")) {						
						this.fl.append(String.format("%2s %-10s \n","","[General]"));	}
					else {this.fl.append(String.format("%2s %-10s \n","","[Student]"));}
					this.fl.append(st[3]+st[4]+"\n"+st[7]+"\n");}
				}
			}
			this.fl.append("\n---------------------------\n");
			this.fl.append(String.format("%-10s %-50s %10s %10s %10s \n","Code", "Item" ,"Subtotal", "tax", "total"));
			InvCal2(in);
		}
		
		
		
	}

/*
 * this method takes object ot Invoice as an input,
 * iterates through the invoice list 
 * and saves the tax , subtotal , discount 
 */
	public void InvCal(Invoice inv){

		double sum =0;
		double sumTax =0;
		double sumTotal =0;
		String s1[] = inv.toString().split("\\+");

		String s2[] = s1[4].split(",");
		int ticketNumber = 0;
		for(int i=0;i<s2.length;i++) {

			String s3[] = s2[i].split(":");
			
			switch(getType(s3[0])){
			case 'M':{
				String date=null;
				Movie m = new Movie(s3[0]);
				ticketNumber = Integer.parseInt(s3[1]);
				ArrayList<Product> pro = fr.readProduct();
				String line [] = pro.toString().split("\\)");
				for(int j=0;j<line.length;j++) {
					String st [] = line[j].split(":");
					if(st[0].contains(s3[0])) {
						String s[] =st[2].split(" ");
						date=s[0];

					}
				}
				sum+=(m.subTotal(s3[0], s3[1],date)); 

				sumTax+=(m.getTax(s3[0], s3[1],date));
				break;
			}
			case 'P':{
				if(s3.length==3) {
					String ref =s3[2];
					int qty=0;
					for(int j=0;j<s2.length;j++) {
						String temp[] = s2[j].split(":");
						if(ref.contains(temp[0])) {
							qty=Integer.parseInt(temp[1]);
						}
					}
					if(qty>(Integer.parseInt(s3[1]))){
						ParkingPass p = new ParkingPass(s3[0]);
						sum+=(p.subTotal(s3[0], Integer.toString(0)));
						sumTax+=p.getTax(s3[0], Integer.toString(0));
						break;
					}
					else {
						ParkingPass p = new ParkingPass(s3[0]);
						qty=(Integer.parseInt(s3[1]))-qty;
						sumTax+=p.getTax(s3[0],Integer.toString(qty));
						sum+=(p.subTotal(s3[0],Integer.toString(qty)));
						break;
					}

				}
				else{
					ParkingPass p = new ParkingPass(s3[0]);
					sum+=(p.subTotal(s3[0], s3[1]));
					sumTax+=p.getTax(s3[0], s3[1]);
					sumTotal+=(p.grandTotal(s3[0], s3[1]));
					break;
				}
			}
			case 'S':{
				Season s = new Season(s3[0]);
				sum+=(s.subTotal(s3[0], s3[1]));
				sumTax+=(s.getTax(s3[0],s3[1]));
				break;
			}
			case 'R':{
				if(ticketNumber==0) {
					Refreshment r = new Refreshment(s3[0]);

					sum+=(r.subTotal(s3[0], s3[1]));
					sumTax+=(r.getTax(s3[0], s3[1]));
					break;}
				else {
					Refreshment r = new Refreshment(s3[0]);
					sum+=((r.subTotal(s3[0], s3[1]))-(0.05*(r.subTotal(s3[0], s3[1]))));
					sumTax+=((r.getTax(s3[0], s3[1]))-(0.05*(r.getTax(s3[0], s3[1]))));
				}

			}
			}


		}
		if(getCustType(inv)=='G') 
			this.fee.add(0.0);
		else
			this.fee.add(6.75);
		if(getCustType(inv)=='G') 
			this.discount.add(0.0);
		else {
			Student dent = new Student();
			this.discount.add((dent.getDiscount(sum)+sumTax));
		}
		this.sub.add(sum);
		this.taxes.add(sumTax);
		sumTotal=sum+sumTax+this.fee.get(count)-this.discount.get(count);
		this.total.add(sumTotal);
		

	}
	
	
	 /* this method takes object ot Invoice as an input,
	 * iterates through the invoice list 
	 * and saves the tax , subtotal , discount 
	 */
	public void InvCal2(Invoice inv){
		count=0;
		double sum =0 ,sum2=0;
		double sumTax =0, sumTax2=0;
		double sumTotal =0 ,sumTotal2=0;
		String s1[] = inv.toString().split("\\+");

		String s2[] = s1[4].split(",");
		int ticketNumber = 0;
		for(int i=0;i<s2.length-1;i++) {
			
			Date dat;
			String date=null;
			SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
			ArrayList<Product> pro1= fr.readProduct();
			String[] line1 = pro1.toString().split("\\)");
 
			String s3[] = s2[i].split(":");
			
			
			switch(getType(s3[0])){
			case 'M':{
				for(int i1=0;i1<line1.length;i1++) {
					String data[] =line1[i1].split(":");

					if(data[0].contains(s3[0])) {
						try {
							String temp [] =data[2].split(" ");
							dat = dateformat.parse(temp[0]);
							date=dateformat.format(dat);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 	
						String temp[] = data[0].split(" ");
						this.fl.append(String.format("%-10s %-15s %-20s %-15s",temp[1],"Movie Ticket",data[4],date));
					}
				}
				
				Movie m = new Movie(s3[0]);
				ticketNumber = Integer.parseInt(s3[1]);
				ArrayList<Product> pro = fr.readProduct();
				String line [] = pro.toString().split("\\)");
				for(int j=0;j<line.length;j++) {
					String st [] = line[j].split(":");
					if(st[0].contains(s3[0])) {
						String s[] =st[2].split(" ");
						date=s[0];

					}
				}
				sum=(m.subTotal(s3[0], s3[1],date)); 

				sumTax=(m.getTax(s3[0], s3[1],date));
				break;
			}
			case 'P':{
				for(int i1=0;i1<line1.length;i1++) {
					String data[] =line1[i1].split(":");
					if(data[0].contains(s3[0])) {
						String temp[] = data[0].split(" ");
						if(s3.length==3) 
							
						this.fl.append(String.format("%-10s %-20s %-30s",temp[1],"Parking Pass",s3[2]));
						else
							this.fl.append(String.format("%-10s %-50s",temp[1],"Parking Pass"));
					}
				}
				if(s3.length==3) {
					String ref =s3[2];
					int qty=0;
					for(int j=0;j<s2.length;j++) {
						String temp[] = s2[j].split(":");
						if(ref.contains(temp[0])) {
							qty=Integer.parseInt(temp[1]);
						}
					}
					if(qty>(Integer.parseInt(s3[1]))){
						ParkingPass p = new ParkingPass(s3[0]);
						sum=(p.subTotal(s3[0], Integer.toString(0)));
						sumTax=p.getTax(s3[0], Integer.toString(0));
						break;
					}
					else {
						ParkingPass p = new ParkingPass(s3[0]);
						qty=(Integer.parseInt(s3[1]))-qty;
						sumTax=p.getTax(s3[0],Integer.toString(qty));
						sum=(p.subTotal(s3[0],Integer.toString(qty)));
						break;
					}

				}
				else{
					ParkingPass p = new ParkingPass(s3[0]);
					sum=(p.subTotal(s3[0], s3[1]));
					sumTax=p.getTax(s3[0], s3[1]);
					sumTotal+=(p.grandTotal(s3[0], s3[1]));
					break;
				}
			}
			case 'S':{
				for(int i1=0;i1<line1.length;i1++) {
					String data[] =line1[i1].split(":");
					String temp[] = data[0].split(" ");
					if(data[0].contains(s3[0])) 						
						this.fl.append(String.format("%-10s %-20s %-30s",data[0],"Season Pass" ,data[2]));
					}
				Season s = new Season(s3[0]);
				sum=(s.subTotal(s3[0], s3[1]));
				sumTax=(s.getTax(s3[0],s3[1]));
				break;
			}
			case 'R':{
				for(int i1=0;i1<line1.length;i1++) {
					String data[] =line1[i1].split(":");
					if(data[0].contains(s3[0])) {
						String temp[] = data[0].split(" ");
						this.fl.append(String.format("%-10s %-50s",temp[1],data[2]));
					}
					}
				if(ticketNumber==0) {
					Refreshment r = new Refreshment(s3[0]);

					sum=(r.subTotal(s3[0], s3[1]));
					sumTax=(r.getTax(s3[0], s3[1]));
					break;}
				else {
					Refreshment r = new Refreshment(s3[0]);
					sum=((r.subTotal(s3[0], s3[1]))-(0.05*(r.subTotal(s3[0], s3[1]))));
					sumTax=((r.getTax(s3[0], s3[1]))-(0.05*(r.getTax(s3[0], s3[1]))));
				}

			}
			}
			sumTotal=sum+sumTax+this.fee.get(count2)-this.discount.get(count2);
			this.fl.append(String.format("$%10.2f $ %10.2f $%10.2f \n",sum,sumTax,sumTotal));
			sum2+=sum;
			sumTax2+=sumTax;
			sumTotal2+=sumTotal;

		}
		this.fl.append("=======================\n");
		this.fl.append(String.format("%-60s $%10.2f $%10.2f $%10.2f \n","SubTotal",sum2,sumTax2,sumTotal2));
		
		if(getCustType(inv)=='G')
		{
			
		}
		else {

			this.fl.append(String.format("%-90s $%-10.2f \n","Discount",discount.get(count2)));
			this.fl.append(String.format("%-90s $%-10.2f \n","Fee",fee.get(count2)));
		}
		
		this.fl.append(String.format("%-80s $%-10.2f \n\n","Total",(sumTotal2+this.fee.get(count2)-this.discount.get(count2))));
		
		this.fl.append(String.format("%25s %-25s\n\n","","Thank You For Your Purchase"));
		count2++;
	}
	public StringBuilder getFl() {
		return fl;
	}


	public char getType(String code) {
		ArrayList<Product> pro= fr.readProduct();

		String[] line = pro.toString().split("\\)");
		for(int i=0;i<line.length;i++) {
			String data[] =line[i].split(":");

			if(data[0].contains(code)) {
				return  data[1].charAt(0);
			}
		}
		return 0;
	}
	
	
	 /* this method takes object ot Invoice as an input,
	 * iterates through the customer list 
	 * t 
	 */
	public char getCustType(Invoice inv) {
		String str[] = inv.toString().split("\\+");
		ArrayList<Customer> cust = fr.readCustomer();
		String st[]= cust.toString().split("\\)");
		for(int i=0;i<st.length;i++) {
			if(st[i].contains(str[1])) {
				String s [] = st[i].split(":");
				return s[1].charAt(0);
			}
		}
		return 0;


	}
	/*
	 * this method assigns all the ouput in single string builder
	 */
	public StringBuilder output(ArrayList<Double> out){
		StringBuilder list = new StringBuilder();
		//System.out.println(out);
		for(Double i : out ) {
			list.append(i);
		}
		return list;
	}
	public <T> String[] TK(ArrayList<T> list) {
		return list.toString().split("\\)");
	}
	
	public <T> String[] TKP(ArrayList<T> list) {
		return list.toString().split("\\(");
	}
	public String[] Toke(String str) {
		return str.split(":");
	}
/*
 * this method returns sum 
 */
	public double sum(ArrayList<Double> list) {
		double sum=0;
		for(Double l : list) {
			sum+=l;
		}
		return sum;
	}
}