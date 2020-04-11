
public class Room {
	
	 int room_ID;
	 String room_type;
	 double rate;
	 
	 Room(int room_ID, String room_type){

		 switch (room_type) {
		  case "Single_room":
			  
			 this.room_ID = room_ID;
		     this.rate = 40.00;
		 
		     break;
		    
		  case "Double_room":
			  
			  this.rate = 60.00;
			  this.room_ID = room_ID;
			  break;
		    
		  case "Delux_room":
			  
			  this.room_ID = room_ID;
			  this.rate = 80.00;
			  break;
			  
		  case "Pent_house":
			  
			  this.room_ID = room_ID;
			  this.rate = 100.00;
		 }
	     this.room_type = room_type;
	 }

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}

