package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Trip;


/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TripHelper dao = new TripHelper();
		String act = request.getParameter("doThisToTrip");
		
		String path = "/viewAllTripsServlet";
		
		if (act.contentEquals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Trip tripToDelete = dao.searchForTripById(tempId);
				dao.deleteTrip(tripToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a trip to delete");
			}
			
			
		} else if (act.contentEquals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				Trip tripToEdit = dao.searchForTripById(tempId);
				request.setAttribute("tripToEdit", tripToEdit);
				path = "/edit-trip.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a trip to edit");
			}
			
		} else if (act.contentEquals("add")) {
			path = "/index.html";
		}
		
		getServletContext().getRequestDispatcher(path).forward(request, response);
		
	}


}
