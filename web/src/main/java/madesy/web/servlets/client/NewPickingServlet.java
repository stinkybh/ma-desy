package madesy.web.servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.dto.NewPickingRequest;
import madesy.web.servlets.BaseServlet;
import madesy.web.utils.ParametersToBeanConverter;
import madesy.web.utils.PickingBuilder;

@WebServlet("/client/new-picking")
public class NewPickingServlet extends BaseServlet {
	private static final long serialVersionUID = -8281953502188692277L;

	public void doPost(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		NewPickingRequest pickingRequest = ParametersToBeanConverter.populate(
				NewPickingRequest.class, request);

		pickingService.newPicking(new PickingBuilder(pickingRequest, loggedUser
				.getId()).build());

		redirect("new-picking");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		forward("new-picking.jsp");
	}

}
