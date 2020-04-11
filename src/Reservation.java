import java.util.Date;

public class Reservation {

int bookID;
Date bookDate;
Date checkIn;
Date chechOut;

public Reservation(int bookID, Date bookDate, Date checkIn, Date chechOut) {
		super();
		this.bookID = bookID;
		this.bookDate = bookDate;
		this.checkIn = checkIn;
		this.chechOut = chechOut;
	}
public int getBookID() {
	return bookID;
}
public void setBookID(int bookID) {
	this.bookID = bookID;
}
public Date getBookDate() {
	return bookDate;
}
public void setBookDate(Date bookDate) {
	this.bookDate = bookDate;
}
public Date getCheckIn() {
	return checkIn;
}
public void setCheckIn(Date checkIn) {
	this.checkIn = checkIn;
}
public Date getChechOut() {
	return chechOut;
}
public void setChechOut(Date chechOut) {
	this.chechOut = chechOut;
}

}
