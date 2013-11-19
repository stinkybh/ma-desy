package madesy.model.pickings;

import madesy.model.BaseModel;
import madesy.model.client.Client;
import madesy.model.client.ClientType;

/**
 * Represents a new picking for delivering and contains of ID of the sender, ID
 * of the courier who has taken the delivery, current status and meta info.
 * 
 * @author hristo
 * 
 */
public class Picking extends BaseModel{
	private PickingStatus pickingStatus = PickingStatus.NEW;

	private String senderId;
	private String courierId;

	private Client sender;
	private Client receiver;

	private PickingSize size;

	public Picking(String senderId, PickingSize size, Client sender,
			Client receiver) {
		validateParameters(senderId, size, sender, receiver);

		this.senderId = senderId;
		this.size = size;
		this.sender = sender;
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "Picking [id=" + id + ", pickingStatus=" + pickingStatus
				+ ", senderId=" + senderId + ", courierId=" + courierId
				+ ", sender=" + sender + ", receiver=" + receiver + ", size="
				+ size + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Picking))
			return false;
		Picking other = (Picking) obj;
		if (id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}
	
	public PickingStatus getPickingStatus() {
		return pickingStatus;
	}

	public void setPickingStatus(PickingStatus pickingStatus) {
		this.pickingStatus = pickingStatus;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}

	public String getCourierId() {
		return this.courierId;
	}

	public PickingSize getSize() {
		return this.size;
	}

	public Client getSender() {
		return this.sender;
	}

	public Client getReceiver() {
		return this.receiver;
	}

	private void validateParameters(String senderId, PickingSize size,
			Client sender, Client receiver) {
		if (senderId == null)
			throw new NullPointerException("SenderId cannot be null");

		if (size == null)
			throw new NullPointerException("PickingSize cannot be null");

		if (sender == null)
			throw new NullPointerException("Sender cannot be null");

		if (receiver == null)
			throw new NullPointerException("Receiver cannot be null");

		if (sender.getType() != ClientType.SENDER)
			throw new IllegalArgumentException("Sender is from type RECEIVER");

		if (receiver.getType() != ClientType.RECEIVER)
			throw new IllegalArgumentException("Receiver is from type SENDER");
	}
}
