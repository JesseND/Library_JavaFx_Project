package business.model;

import java.io.Serializable;

public class LibraryMember extends Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7950058660651825776L;
	private String memberNumber;
	private CheckoutRecord checkoutRecord;
	
	
	public LibraryMember(String memberNumber) {
		super(ROLETYPE.LIBRAY_MEMBER);
		this.memberNumber = memberNumber;
		this.checkoutRecord = new CheckoutRecord();
	}
	
	

	public String getMemberNumber() {
		return memberNumber;
	}



	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}



	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}
	
}
