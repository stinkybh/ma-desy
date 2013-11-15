package madesy.model.pickings;

import java.util.ArrayList;
import java.util.List;

import madesy.model.pickings.Picking;

/**
 * Container for all pickings of all clients, providing
 * the necessary concurrent methods on it, for adding
 * a new picking and changing the status of a specified
 * one.
 *
 */
public class PickingStorage {
	private List<Picking> pickings = new ArrayList<Picking>();
	
	public PickingStorage() {
	}
	
	public void add(Picking picking) {
		pickings.add(picking);
	}
	
	public Picking getPicking(String pickingId) {
		for(Picking p : pickings) {
			if(p.getId().equals(pickingId))
				return p;
		}
		
		return null;
	}
	
	public List<Picking> getPickings() {
		return pickings;
	}

	@Override
	public String toString() {
		return "PickingStorage [pickings=" + pickings + "]\n";
	}
}
