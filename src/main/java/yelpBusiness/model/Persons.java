package yelpBusiness.model;

import java.util.Date;

public class Persons {

	protected String userId;
	protected String personName;
	protected int reviewCount;
	protected int usefulCount;
	protected int funnyCount;
	protected int coolCount;
	protected Date yelpingSince;

	public Persons(String userId, String personName, int reviewCount, int usefulCount, int funnyCount, int coolCount,
			Date yelpingSince) {
		this.userId = userId;
		this.personName = personName;
		this.reviewCount = reviewCount;
		this.usefulCount = usefulCount;
		this.funnyCount = funnyCount;
		this.coolCount = coolCount;
		this.yelpingSince = yelpingSince;
	}

	public Persons(String userId) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public int getUsefulCount() {
		return usefulCount;
	}

	public void setUsefulCount(int userfulCount) {
		this.usefulCount = userfulCount;
	}

	public int getFunnyCount() {
		return funnyCount;
	}

	public void setFunnyCount(int funnyCount) {
		this.funnyCount = funnyCount;
	}

	public int getCoolCount() {
		return coolCount;
	}

	public void setCoolCount(int coolCount) {
		this.coolCount = coolCount;
	}

	public Date getYelpingSince() {
		return yelpingSince;
	}

	public void setYelpingSince(Date yelpingSince) {
		this.yelpingSince = yelpingSince;
	}

}