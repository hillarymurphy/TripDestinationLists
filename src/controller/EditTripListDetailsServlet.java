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
 * Servlet implementation class EditTripListDetailsServlet
 */
@WebServlet("/editTripListDetailsServlet")
public class EditTripListDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTripListDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Set up TripListDetailsHelper, TripHelper, TouristHelper
		TripListDetailsHelper tld = new	TripListDetailsHelper();
		TripHelper th = new TripHelper();
		TouristHelper toh = new TouristHelper();

		Integer tempId 	= Integer.parseInt(request.getParameter("id"));
		TripListDetails listToUpdate = tld.searchForTripListDetailsById(tempId); // get specific list by id
		
		String newListName = request.getParameter("listName");
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		
		String touristName = request.getParameter("touristName");
		//find our add the new shopper
		Tourist newTourist = toh.findTourist(touristName);
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year),
					Integer.parseInt(month), Integer.parseInt(day));
		} catch (NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		try {
			//items are selected in list to add
			String[] selectedTrips = request.getParameterValues("allTripsToAdd"); // get all potential lists
			List<Trip> selectedTripsInList = new ArrayList<Trip>();

			// Available Trips loop
			for	(int i = 0; i < selectedTrips.length; i++) {
				System.out.println(selectedTrips[i]);
				Trip t = th.searchForTripById(Integer.parseInt(selectedTrips[i]));
				selectedTripsInList.add(t);

			}
			listToUpdate.setListOfTrips(selectedTripsInList);
		} catch (NullPointerException ex) {
			// no items selected in list - set to an empty list

			List<Trip> selectedTripsInList = new ArrayList<Trip>();
			listToUpdate.setListOfTrips(selectedTripsInList);
		}
		// Information for display on screen
		listToUpdate.setListName(newListName);
		listToUpdate.setTripDate(ld);
		listToUpdate.setTourist(newTourist);
		tld.updateList(listToUpdate);
		getServletContext().getRequestDispatcher("/viewAllTripsListsServlet").forward(request, response);
	}

}
