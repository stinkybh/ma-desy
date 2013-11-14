package madesy.web.servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.Client;
import madesy.model.ClientType;
import madesy.model.pickings.Picking;
import madesy.model.pickings.PickingSize;
import madesy.web.dto.NewPickingRequest;
import madesy.web.requests.PickingServiceRequest;
import madesy.web.requests.Request;
import madesy.web.utils.ParametersToBeanConverter;

@WebServlet("/new-picking")
public class NewPickingServlet extends HttpServlet {
	private static final long serialVersionUID = -8281953502188692277L;

	public void doPost(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		final NewPickingRequest pickingRequest = ParametersToBeanConverter
				.populate(NewPickingRequest.class, request);

		new PickingServiceRequest(request, response) {

			@Override
			public String request() {
				PickingSize size = new PickingSize(
						pickingRequest.getPickingWidth(),
						pickingRequest.getPickingHeight(),
						pickingRequest.getPickingLength());
				Client sender = new Client(pickingRequest.getSenderName(),
						pickingRequest.getSenderAddress(), ClientType.SENDER);
				Client receiver = new Client(pickingRequest.getReceiverName(),
						pickingRequest.getReceiverAddress(),
						ClientType.RECEIVER);
				Picking picking = new Picking(loggedUser.getId(), size, sender,
						receiver);
				pickingService.newPicking(picking);

				return "new-picking";
			}

		}.redirect();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Request(request, response) {

			@Override
			public String request() {
				return "client/new-picking.jsp";
			}
		}.forward();
	}

}
