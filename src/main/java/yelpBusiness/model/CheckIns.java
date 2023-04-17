package yelpBusiness.model;

public class CheckIns {

	protected int checkInId;
	protected String checkInTime;
	protected Businesses business;

	public CheckIns(int checkInId, String checkInTime, Businesses business) {
		this.checkInId = checkInId;
		this.checkInTime = checkInTime;
		this.business = business;
	}

	public int getCheckInId() {
		return checkInId;
	}

	public void setCheckInId(int checkInId) {
		this.checkInId = checkInId;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Businesses getBusiness() {
		return business;
	}

	public void setBusiness(Businesses business) {
		this.business = business;
	}

}