package yelpBusiness.model;

public class CoffeeShops extends Businesses {

	protected boolean outDoorSeating;

	public CoffeeShops(String businessId, String businessName, String address, String city, String state,
			String postalCode, String categories, double latitude, double longitude, int reviewCount, boolean isOpen,
			OpenTime openTime, boolean outDoorSeating, BusinessOwners businessOwner) {
		super(businessId, businessName, address, city, state, postalCode, categories, latitude, longitude, reviewCount,
				isOpen, businessOwner);
		// TODO Auto-generated constructor stub
		this.outDoorSeating = outDoorSeating;
	}

	public CoffeeShops(String businessId) {
		super(businessId);
	}

	public boolean isOutDoorSeating() {
		return outDoorSeating;
	}

	public void setOutDoorSeating(boolean outDoorSeating) {
		this.outDoorSeating = outDoorSeating;
	}

}