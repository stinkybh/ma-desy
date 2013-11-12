package madesy.model;

public class Client {
	private String name;
	private String address;
	private ClientType type;

	public Client(String name, String address, ClientType type) {
		validateParameters(name, address);

		this.name = name;
		this.address = address;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ClientType getType() {
		return type;
	}

	public void setType(ClientType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return !(type != other.type);
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Name: ")
			.append(this.name)
			.append(" Address: ")
			.append(this.address)
			.append((this.type == ClientType.SENDER) ? " Type: Sender" : "Type: Receiver")
			.toString();
	}

	private void validateParameters(String name, String address) {
		if (name == null)
			throw new NullPointerException("Name cannot be null");
		if (address == null)
			throw new NullPointerException("Address cannot be null");
	}
}
