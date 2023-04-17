package yelpBusiness.model;

import java.util.Date;

public class Users extends Persons {

	public Users(String userId, String personName, int reviewCount, int usefulCount, int funnyCount, int coolCount,
			Date yelpingSince) {
		super(userId, personName, reviewCount, usefulCount, funnyCount, coolCount, yelpingSince);
	}

	public Users(String userId) {
		super(userId);
	}
	
	
}