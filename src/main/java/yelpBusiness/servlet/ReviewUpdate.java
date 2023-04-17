package yelpBusiness.servlet;
import yelpBusiness.dal.*;
import yelpBusiness.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reviewupdate")
public class ReviewUpdate extends HttpServlet {
	
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
       

        // Retrieve user and validate.
        String reviewId = req.getParameter("reviewid");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid review id.");
        } else {
        	try {
        		Reviews review = reviewsDao.getReviewByReviewId(reviewId);
        		if(review == null) {
        			messages.put("success", "review does not exist.");
        		}
        		req.setAttribute("review", review);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String reviewId = req.getParameter("reviewid");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Name.");
        } else {
        	try {
        		Reviews review = reviewsDao.getReviewByReviewId(reviewId);
        		if(review == null) {
        			messages.put("success", "review does not exist. No update to perform.");
        		} else {
        			String newContent = req.getParameter("newcontent");
        			if (newContent == null || newContent.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid newContent.");
        	        } else {
        	        	review = reviewsDao.UpdateReviewContent(review, newContent);
        	        	messages.put("success", "Successfully updated review" + review.getReviewId());
        	        }
        		}
        		req.setAttribute("review", review);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);
    }
}
