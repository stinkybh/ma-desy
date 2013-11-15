package madesy.web.utils;

import madesy.model.client.Client;
import madesy.model.client.ClientType;
import madesy.model.pickings.Picking;
import madesy.model.pickings.PickingSize;
import madesy.web.dto.NewPickingRequest;

public class PickingBuilder {
	private Picking picking;
	
	public PickingBuilder(NewPickingRequest pickingRequest, String userId) {
		PickingSize size = new PickingSize(pickingRequest.getPickingWidth(),
				pickingRequest.getPickingHeight(),
				pickingRequest.getPickingLength());
		Client sender = new Client(pickingRequest.getSenderName(),
				pickingRequest.getSenderAddress(), ClientType.SENDER);
		Client receiver = new Client(pickingRequest.getReceiverName(),
				pickingRequest.getReceiverAddress(), ClientType.RECEIVER);
		this.picking = new Picking(userId, size, sender,
				receiver);
	}
	
	public Picking build() {
		return this.picking;
	}
}
