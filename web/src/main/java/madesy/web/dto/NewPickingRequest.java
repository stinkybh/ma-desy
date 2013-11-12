package madesy.web.dto;

public class NewPickingRequest {
	private String senderName;
	private String senderAddress;
	private String receiverName;
	private String receiverAddress;
	private int pickingWidth;
	private int pickingLength;
	private int pickingHeight;
	
	public NewPickingRequest() { }

	public NewPickingRequest(String senderName, String senderAddress,
			String receiverName, String receiverAddress, int pickingWidth,
			int pickingLength, int pickingHeight) {
		super();
		this.senderName = senderName;
		this.senderAddress = senderAddress;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.pickingWidth = pickingWidth;
		this.pickingLength = pickingLength;
		this.pickingHeight = pickingHeight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pickingHeight;
		result = prime * result + pickingLength;
		result = prime * result + pickingWidth;
		result = prime * result
				+ ((receiverAddress == null) ? 0 : receiverAddress.hashCode());
		result = prime * result
				+ ((receiverName == null) ? 0 : receiverName.hashCode());
		result = prime * result
				+ ((senderAddress == null) ? 0 : senderAddress.hashCode());
		result = prime * result
				+ ((senderName == null) ? 0 : senderName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof NewPickingRequest))
			return false;
		NewPickingRequest other = (NewPickingRequest) obj;
		if (pickingHeight != other.pickingHeight)
			return false;
		if (pickingLength != other.pickingLength)
			return false;
		if (pickingWidth != other.pickingWidth)
			return false;
		if (!receiverAddress.equals(other.receiverAddress))
			return false;
		if (!receiverName.equals(other.receiverName))
			return false;
		if (!senderAddress.equals(other.senderAddress))
			return false;
		if (!senderName.equals(other.senderName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewPickingRequest [senderName=" + senderName
				+ ", senderAddress=" + senderAddress + ", receiverName="
				+ receiverName + ", receiverAddress=" + receiverAddress
				+ ", pickingWidth=" + pickingWidth + ", pickingLength="
				+ pickingLength + ", pickingHeight=" + pickingHeight + "]";
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public int getPickingWidth() {
		return pickingWidth;
	}

	public void setPickingWidth(int pickingWidth) {
		this.pickingWidth = pickingWidth;
	}

	public int getPickingLength() {
		return pickingLength;
	}

	public void setPickingLength(int pickingLength) {
		this.pickingLength = pickingLength;
	}

	public int getPickingHeight() {
		return pickingHeight;
	}

	public void setPickingHeight(int pickingHeight) {
		this.pickingHeight = pickingHeight;
	}

}
