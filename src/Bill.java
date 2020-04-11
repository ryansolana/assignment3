
public class Bill {
	int bill_id;
	double amountToPay;
	
	
	public Bill(int bill_id, double amountToPay) {
		super();
		this.bill_id = bill_id;
		this.amountToPay = amountToPay;
	}
	
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	public double getAmountToPay() {
		return amountToPay;
	}
	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}
	
	
}
