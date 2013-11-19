package madesy.model;

import java.util.UUID;

public abstract class BaseModel {
	protected String id = UUID.randomUUID().toString();

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	
}
