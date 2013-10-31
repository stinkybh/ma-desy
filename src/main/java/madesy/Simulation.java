package madesy;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import madesy.model.User;
import madesy.model.Users;
import madesy.model.types.UserTypes;
import madesy.model.workers.ClientWorker;
import madesy.model.workers.CourrierWorker;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class Simulation {
	public static void main(String[] args) {
		EventLog eventLog = new EventLog();
		PickingStorage pickingStorage = new PickingStorage();
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Users users = new Users();
		Random rand = new Random();
		
		// Gets all users
		for(User u : users.getUsers()) {
			if(u.getType() == UserTypes.CLIENT)
				pool.submit(new ClientWorker(u.getId(), pickingStorage, eventLog, rand.nextInt(10000)));
			else if(u.getType() == UserTypes.COURIER)
				pool.submit(new CourrierWorker(u.getId(), pickingStorage, eventLog, rand.nextInt(1000)));
			//else
				//pool.submit(new ManagerWorker(eventLog, 5000));
		}
		//pool.submit(new SimulationSupervisor(pool, eventLog, 5, 5000));
		
	}
}
