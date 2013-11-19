package madesy.simulation;

import java.util.List;

import madesy.model.courier.CourierPickingsInfo;
import madesy.model.events.Event;
import madesy.model.events.EventType;
import madesy.model.pickings.PickingService;
import madesy.model.reports.ReportService;
import madesy.workers.BaseWorker;

public class ReportsSimulation extends SimulationBase {

	public ReportsSimulation(PickingService pickingService,
			ReportService reportService, CourierPickingsInfo courierPickings) {
		super(pickingService, reportService, courierPickings);
	}

	@Override
	public List<BaseWorker> process() {

		List<BaseWorker> workers = workersGenerator.generate(8, 5, 1);
		workers.add(new SimulationSupervisor(pool,
				pickingService.getEventLog(), 50) {

			@Override
			public boolean checkForTermination() {
				int count = 0;
				for (Event e : eventLog.getEvents()) {
					if (e.getEventType() == EventType.MANAGER_REPORT) {
						count++;
					}
				}

				return count >= 10;
			}

		});

		return workers;

	}

}
