import java.util.Date;

public class Reservation {

	int bookID, noOfRooms, noOfDays;
	String customerName, roomType;
	Date bookDate;
	Date checkIn;
	Date checkOut;
	
	Reservation(int bookID, Date bookDate,
			Date checkIn, Date checkOut,
			String customerName, String roomType,
			int noOfRooms,int noOfDays) {
		setBookID(bookID);
		setBookDate(bookDate);
		setCustomerName(customerName);
		setRoomType(roomType);
		setNoOfRooms(noOfRooms);
		setNoOfDays(noOfDays);
		setCheckIn(checkIn);
		setCheckOut(checkOut);	
	}
	
	public int getBookID() {
		return bookID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getRoomType() {
		return roomType;
	}
	public int getNoOfRooms() {
		return noOfRooms;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public void setCustomerName(String custName) {
		this.customerName = custName;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public void setCheckOut(Date chechOut) {
		this.checkOut = chechOut;
	}

}
