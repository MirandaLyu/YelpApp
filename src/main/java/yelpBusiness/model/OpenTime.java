package yelpBusiness.model;

public class OpenTime {

	protected int openTimeId;
	protected String openHours;
	protected Businesses business;

	public OpenTime(int openTimeId, String openHours, Businesses business) {
		this.openTimeId = openTimeId;
		this.openHours = openHours;
		this.business = business;
	}

	public int getOpenTimeId() {
		return openTimeId;
	}

	public void setOpenTimeId(int openTimeId) {
		this.openTimeId = openTimeId;
	}

	public String getOpenHours() {
		return openHours;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	public Businesses getBusiness() {
		return business;
	}

	public void setBusiness(Businesses business) {
		this.business = business;
	}
}