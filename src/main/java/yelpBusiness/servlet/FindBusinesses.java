package yelpBusiness.servlet;

import yelpBusiness.dal.*;
import yelpBusiness.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findbusinesses")
public class FindBusinesses extends HttpServlet {
	protected BusinessesDao businessesDao;
	
	@Override
	public void init() throws ServletException {
		businessesDao = BusinessesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Businesses> businesses = new ArrayList<Businesses>();
        
        try {
            businesses = businessesDao.getMostReviewedBusiness();
            messages.put("success", "Displaying the most reviewed businesses.");
            req.setAttribute("businesses", businesses);
        } catch (SQLException e) {
            e.printStackTrace();
            messages.put("error", "An error occurred while fetching businesses.");
        }
        
        req.getRequestDispatcher("/FindBusinesses.jsp").forward(req, resp);
	}
}