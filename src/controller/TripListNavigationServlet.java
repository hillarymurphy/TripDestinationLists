package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TripListDetails;

/**
 * Servlet implementation class TripListNavigationServlet
 */
@WebServlet("/tripListNavigationServlet")
public class TripListNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TripListNavigationServlet() {
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
		TripListDetailsHelper tld = new TripListDetailsHelper();
		String act = request.getParameter("doThisToList");
		if (act == null) {
		// no button has been selected
			getServletContext().getRequestDispatcher("/viewAllTripsListsServlet").forward(request, response);
			}
		else if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				TripListDetails listToDelete = tld.searchForTripListDetailsById(tempId);
				tld.deleteList(listToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to click a button to delete");
			} finally {
				getServletContext().getRequestDispatcher("/viewAllTripsListsServlet").forward(request, response);
			}
		} else if (act.equals("edit")) {
			try
			{ Integer tempId = Integer.parseInt(request.getParameter("id"));
			TripListDetails listToEdit = tld.searchForTripListDetailsById(tempId);
			request.setAttribute("listToEdit", listToEdit);
			
			request.setAttribute("month", listToEdit.getTripDate().getMonthValue());
			request.setAttribute("date", listToEdit.getTripDate().getDayOfMonth());
			request.setAttribute("year", listToEdit.getTripDate().getYear());
			
			TripHelper tldForTrips = new TripHelper();
			
			request.setAttribute("allTrips", tldForTrips.showAllTrips());
			
			if (tldForTrips.showAllTrips().isEmpty()){
			request.setAttribute("allTrips", " ");
			}
			
			getServletContext().getRequestDispatcher("/edit-trip.jsp").forward(request, response);
			
			} catch (NumberFormatException e) {
				request.setAttribute("allTrips", tld.showAllTrips());
				
				if(tld.showAllTrips().isEmpty()){request.setAttribute("allTrips", " ");}
				getServletContext().getRequestDispatcher("/viewAllTripsListsServlet").forward(request, response);
			} 
		} else if (act.equals("add")) {
			
			getServletContext().getRequestDispatcher("/new-list.jsp").forward(request, response);
			}
	}

}

