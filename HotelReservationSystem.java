package application;
import java.util.ArrayList;

//-----Author: Hoo Ern Ping
//-----ID: B200152B
public class HotelReservationSystem {
	
	//-----arraylist
	private ArrayList<Customer> entry;
	
	//-----deflaut constructor
	public HotelReservationSystem() {
		entry = new ArrayList<Customer>();
	}
	
	//-----buy ticket or add method
	public String add(Customer q) {
		boolean found = false;
		String info = "";
		
		for(int i = 0; i < entry.size(); i++) {
			Customer p = entry.get(i);
			
			if(p.getID() == q.getID()) {
				found = true;
			}
		}
		
		if(found == true) {
			info = "Record of customer id: "+ q.getID()+" exists."+
					"\nPlease enter other customer id.";
		}else {
			entry.add(q);

		}
		return info;
	}
	
	//-----remove ticket or remove method
	public String[] remove(int id) {
		String[] info;
		int i = 0;
		for(i = 0; i < entry.size(); i++) {
			Customer p = entry.get(i);
			
			if(p.getID() == id) {
				break;
			}
		}
		
		if(i == entry.size()) {
			info = new String[1];
			info[0] = "null";
		}else {
			//-----remove customer
			info = entry.get(i).getRoomID();
			entry.remove(i);
		}
		
		return info;
	}
	
	// Get Floor method
	public String getFloor(int id) {
		int i = 0;
		for(i = 0; i < entry.size(); i++) {
			Customer p = entry.get(i);
			
			if(p.getID() == id) {
				break;
			}
		}
		if(i == entry.size()) {
			return null;
		}else {
			return entry.get(i).getFloor();
		}
	}
	
	//-----select one customer or search method
	public String search(int id) {
		String info = "";
		int i = 0;
		
		for(i = 0; i < entry.size(); i++) {
			Customer p = entry.get(i);
			
			if(p.getID() == id) {
				break;
			}
		}
		
		if(i == entry.size()) {
			info = "Cannot find the id";
		}else {
			// toString method form Customer class
			info = entry.get(i).toString();
		}
		
		return info;
	}

}
