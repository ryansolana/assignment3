
public class Guest {

    int guestId;
	String title ;
	String firstName;
	String lastName;
	String address;
	int phone;
	String email;
	
	Guest(   int guestId, String title ,String firstName, 
			String lastName, String address, int phone, String email){
		
		this.guestId = guestId;
		this.title = title;
		this.firstName = firstName;
		this.lastName =lastName;
		this.address =address;
		this.phone = phone;
		this.email = email;
		
	}

	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
   
}
