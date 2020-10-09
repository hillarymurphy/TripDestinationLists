package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Trip;

/**
 * Servlet implementation class EditTripServlet
 */
@WebServlet("/editTripServlet")
public class EditTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTripServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TripHelper dao = new TripHelper();
		
		String location = request.getParameter("location");
		String state = request.getParameter("state");
		String attraction = request.getParameter("attraction");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		Trip tripToUpdate = dao.searchForTripById(tempId);
		tripToUpdate.setLocation(location);
		tripToUpdate.setState(state);
		tripToUpdate.setAttraction(attraction);
		
		dao.updateTrip(tripToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllTripsServlet").forward(request, response);
	}

}
