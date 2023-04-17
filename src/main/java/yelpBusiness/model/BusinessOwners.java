package yelpBusiness.model;

import java.util.Date;

public class BusinessOwners extends Persons {

	public BusinessOwners(String userId, String personName, int reviewCount, int usefulCount, int funnyCount,
			int coolCount, Date yelpingSince) {
		super(userId, personName, reviewCount, usefulCount, funnyCount, coolCount, yelpingSince);
	}

	public BusinessOwners(String userId) {
		super(userId);
	}

}