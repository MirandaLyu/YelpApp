package yelpBusiness.model;

public class Restaurants extends Businesses {

	protected boolean canTakeOut;

	public Restaurants(String businessId, String businessName, String address, String city, String state,
			String postalCode, String categories, double latitude, double longitude, int reviewCount, boolean isOpen,
			boolean canTakeOut, BusinessOwners businessOwner) {
		super(businessId, businessName, address, city, state, postalCode, categories, latitude, longitude, reviewCount,
				isOpen, businessOwner);
		// TODO Auto-generated constructor stub
		this.canTakeOut = canTakeOut;
	}

	public Restaurants(String businessId) {
		super(businessId);
		// TODO Auto-generated constructor stub
	}

	public boolean isCanTakeOut() {
		return canTakeOut;
	}

	public void setCanTakeOut(boolean canTakeOut) {
		this.canTakeOut = canTakeOut;
	}

}