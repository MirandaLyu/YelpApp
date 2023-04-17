package yelpBusiness.model;

public class Businesses {

	protected String businessId;
	protected String businessName;
	protected String address;
	protected String city;
	protected String state;
	protected String postalCode;
	protected String categories;
	protected double latitude;
	protected double longitude;
	protected int reviewCount;
	protected boolean isOpen;
	protected BusinessOwners businessOwner;

	public Businesses(String businessId, String businessName, String address, String city, String state,
			String postalCode, String categories, double latitude, double longitude, int reviewCount, boolean isOpen,
			BusinessOwners businessOwner) {

		this.businessId = businessId;
		this.businessName = businessName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.categories = categories;
		this.latitude = latitude;
		this.longitude = longitude;
		this.reviewCount = reviewCount;
		this.isOpen = isOpen;
		this.businessOwner = businessOwner;
	}

	public Businesses(String businessId) {
		// TODO Auto-generated constructor stub
		this.businessId = businessId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public BusinessOwners getBusinessOwner() {
		return businessOwner;
	}

	public void setBusinessOwner(BusinessOwners businessOwner) {
		this.businessOwner = businessOwner;
	}

}