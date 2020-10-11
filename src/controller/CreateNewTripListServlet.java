package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tourist;
import model.Trip;
import model.TripListDetails;

/**
 * Servlet implementation class CreateNewTripListServlet
 */
@WebServlet("/createNewTripListServlet")
public class CreateNewTripListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewTripListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TripHelper th = new TripHelper();
		String listName = request.getParameter("listName");		

		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		LocalDate ld;
		
		String touristName = request.getParameter("touristName");

		
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		}catch(NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		String[] selectedTrips = request.getParameterValues("allTripsToAdd");
		List<Trip> selectedTripsInList = new ArrayList<Trip>();
		
		//make sure something was selected otherwise we get a null pointer exception
		
		if (selectedTrips != null && selectedTrips.length > 0) {
			for (int i = 0; i<selectedTrips.length; i++) {
				System.out.println(selectedTrips[i]);
				Trip t =
						th.searchForTripById(Integer.parseInt(selectedTrips[i]));
				selectedTripsInList.add(t);
			}
		}
		
		Tourist tourist = new Tourist(touristName);
		TripListDetails tld = new TripListDetails(listName, ld, tourist);
		tld.setListOfItems(selectedTripsInList);
		TripListDetailsHelper slh = new TripListDetailsHelper();
		slh.insertNewTripListDetails(tld);
		
		System.out.println("Success!");
		System.out.println(tld.toString());
		
		getServletContext().getRequestDispatcher("/viewAllTripsListsServlet").forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
