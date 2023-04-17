//package yelpBusiness.tools;
//
//import java.sql.SQLException;
//import java.util.Date;
//
//import yelpBusiness.dal.BusinessOwnersDao;
//import yelpBusiness.dal.UsersDao;
//import yelpBusiness.model.BusinessOwners;
//import yelpBusiness.model.Users;
//
//public class Inserter {
//
//	public static void main(String[] args) throws SQLException {
//
//		//PersonsDao personsDao = PersonsDao.getInstance();
//		UsersDao usersDao = UsersDao.getInstance();
//		BusinessOwnersDao businessOwnersDao = BusinessOwnersDao.getInstance();
////		BusinessesDao businessesDao = BusinessesDao.getInstance();
////		TipsDao tipsDao = TipsDao.getInstance();
//
////		Persons person1 = new Persons("1", "p1", 1, 1, 1, 1, new Date());
////		Persons person2 = new Persons("2", "p2", 1, 1, 1, 1, new Date());
////		personsDao.create(person1);
////		personsDao.create(person2);
//
//		BusinessOwners businessOwner1 = new BusinessOwners("1", "p1", 1, 1, 1, 1, new Date());
//		Users user1 = new Users("2", "p2", 1, 1, 1, 1, new Date());
//		usersDao.create(user1);
//		businessOwnersDao.create(businessOwner1);
////
////		Businesses business1 = new Businesses("1", "b1", "3rd Ave", "Seattle", "WA", "98119", "Restaurant", 119.0,
////				120.0, 5, true, businessOwner1);
////		businessesDao.create(business1);
////
////		Tips tip1 = new Tips("content", new Date(), 1, business1, user1);
////		tipsDao.create(tip1);
//	}
//}


package yelpBusiness.tools;

import java.sql.SQLException;

import java.util.Date;
import java.util.List;

import yelpBusiness.dal.*;
import yelpBusiness.dal.CheckInDao;
import yelpBusiness.dal.PersonsDao;
import yelpBusiness.dal.UsersDao;
import yelpBusiness.model.*;
import yelpBusiness.model.Users;

public class Inserter {

	public static void main(String[] args) throws SQLException {

		PersonsDao personsDao = PersonsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		BusinessOwnersDao businessOwnersDao = BusinessOwnersDao.getInstance();
		BusinessesDao businessesDao = BusinessesDao.getInstance();
//		TipsDao tipsDao = TipsDao.getInstance();
		CheckInDao checkInDao = CheckInDao.getInstance();
		CoffeeShopsDao coffeeShopsDao = CoffeeShopsDao.getInstance();
		OpenTimeDao openTimeDao = OpenTimeDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();

		Persons person1 = new Persons("1", "p1", 1, 1, 1, 1, new Date());
		Persons person2 = new Persons("2", "p2", 1, 1, 1, 1, new Date());
		personsDao.create(person1);
		personsDao.create(person2);

		BusinessOwners businessOwner1 = new BusinessOwners("1", "p1", 1, 1, 1, 1, new Date());
		Users user1 = new Users("2", "p2", 1, 1, 1, 1, new Date());
		usersDao.create(user1);
		businessOwnersDao.create(businessOwner1);
//
		Businesses business1 = new Businesses("1", "b1", "3rd Ave", "Seattle", "WA", "98119", "Restaurant", 119.0,
				120.0, 5, true, businessOwner1);
		businessesDao.create(business1);
//
//		Tips tip1 = new Tips("content", new Date(), 1, business1, user1);
//		tipsDao.create(tip1);
		
		
		CheckIns checkIn1 = new CheckIns(1, "1",business1);
		checkInDao.create(checkIn1);
		
		OpenTime openTime1 = new OpenTime(1, "8am - 8pm", business1);
		openTimeDao.create(openTime1);
		
		CoffeeShops coffeeShops1 = new CoffeeShops("2", "b2", "3rd Ave", "Seattle", "WA", "98119", "coffee", 119.0,
				120.0, 5, true, openTime1, true, businessOwner1);
		coffeeShopsDao.create(coffeeShops1);
		
		
//		coffeeShopsDao.delete(coffeeShops1);
//		openTimeDao.delete(openTime1);
//		checkInDao.delete(checkIn1);
		
		
		Reviews review1 = new Reviews("1",2.00,5,6,7,"content",new Date(),business1,user1);
		reviewsDao.create(review1);
		
		Reviews review2 = new Reviews("2",3.00,5,3,3,"content2",new Date(),business1, user1);
		reviewsDao.create(review2);
		
		List<Reviews> reviews = reviewsDao.getReviewsByBusinessName("b1");
		for(Reviews rev : reviews) {
			System.out.format("Looping reviews: u:%s f:%s l:%s \n",
					rev.getReviewId(), rev.getReviewContent(), rev.getBusiness().getBusinessName());
		}
	}
}
