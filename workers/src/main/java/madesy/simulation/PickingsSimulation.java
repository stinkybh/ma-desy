package madesy.simulation;

import java.util.List;

import madesy.model.courier.CourierPickingsInfo;
import madesy.model.events.Event;
import madesy.model.events.EventType;
import madesy.model.pickings.PickingService;
import madesy.model.reports.ReportService;
import madesy.workers.BaseWorker;
import madesy.workers.PickingDispatcherWorker;

public class PickingsSimulation extends SimulationBase {

	public PickingsSimulation(PickingService pickingService,
			ReportService reportService, CourierPickingsInfo courierPickings) {
		super(pickingService, reportService, courierPickings);
	}

	@Override
	public List<BaseWorker> process() {

		List<BaseWorker> workers = workersGenerator.generate(3, 3, 2);
		workers.add(new PickingDispatcherWorker(pickingService, 50));
		workers.add(new SimulationSupervisor(pool, pickingService.getEventLog(), 50) {

			@Override
			public boolean checkForTermination() {
				int count = 0;
				for (Event e : eventLog.getEvents()) {
					if (e.getEventType() == EventType.NEW_PICKING) {
						count++;
					}
				}
				return count >= 10000;
			}

		});

		return workers;
	}

}
