package madesy.simulation;

import java.util.List;

import madesy.model.events.Event;
import madesy.model.events.EventLog;
import madesy.model.events.EventType;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;
import madesy.workers.BaseWorker;

public class ReportsSimulation extends SimulationBase {

	public ReportsSimulation(PickingStorage pickingStorage, EventLog eventLog,
			ReportService reportService) {
		super(pickingStorage, eventLog, reportService);
	}

	@Override
	public List<BaseWorker> process() {

		List<BaseWorker> workers = workersGenerator.generate(8, 5, 1);
		workers.add(new SimulationSupervisor(pool, eventLog, 50) {

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
