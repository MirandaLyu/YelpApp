package yelpBusiness.servlet;

import yelpBusiness.dal.*;
import yelpBusiness.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/reviewcreate")
public class ReviewCreate extends HttpServlet {
	
	protected ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String reviewId = req.getParameter("reviewid");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("success", "Invalid ReviewId");
        } else {
        	// Create the BlogUser.
        	double stars = Double.parseDouble(req.getParameter("stars"));
        	int usefulCount = Integer.parseInt(req.getParameter("usefulcount"));
        	int funnyCount = Integer.parseInt(req.getParameter("funnycount"));
        	int coolCount = Integer.parseInt(req.getParameter("coolcount"));
        	String reviewContent = req.getParameter("reviewcontent");
        	String businessId = req.getParameter("businessid");
        	String userId = req.getParameter("userid");
			UsersDao usersDao = UsersDao.getInstance();
			BusinessesDao businessesDao = BusinessesDao.getInstance();
			Users user = null;
			try {
				user = usersDao.getUserByUserId(userId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Businesses business = null;
			try {
				business = businessesDao.getBusinessById(businessId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	// dob must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String stringReviewDate = req.getParameter("reviewdate");
        	Date reviewDate = new Date();
        	try {
        		reviewDate = dateFormat.parse(stringReviewDate);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Reviews review = new Reviews(reviewId, stars, usefulCount, funnyCount, coolCount, 
	        			reviewContent, reviewDate, business, user);
	        	review = reviewsDao.create(review);
	        	messages.put("success", "Successfully created review" + reviewId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
    }

}
