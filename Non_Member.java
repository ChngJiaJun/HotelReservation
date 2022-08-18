package application;

//-----Author: Hoo Ern Ping
//-----ID: B200152B
public class Non_Member extends Customer{
	
	//-----default constructor
	public Non_Member() {
		super();
	}
			
	//-----constructor with parameter
	public Non_Member(int id, String name, String floor, 
				String[] roomID, int stay) {
		
		super(id, name, floor, roomID, stay);
	}
	
        //-----task method
	public double calcTotalDiscount() {
		double discount = 1;
		
		return discount * super.calcTotalPrice();
	}
        
	public double calcGrandTotal() {
		return super.calcTotalPrice() ;
	}
	
	//-----print price method
	public String printPrice() {
		return String.format("%.2f",calcTotalDiscount())+","+String.format("%.2f",calcGrandTotal());
	}
		
	//-----print method
	public String toString() {
		return super.toString()+
				"\nNon Member Discount: "+ String.format("%.2f",calcTotalDiscount())+
				"\nGrand Total: "+String.format("%.2f",calcGrandTotal());
	}	
}
