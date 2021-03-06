package madesy.simulation;

import madesy.model.courier.CourierPickingsInfo;
import madesy.model.events.EventLog;
import madesy.model.pickings.PickingService;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;
import madesy.model.reports.ReportStorage;
import madesy.workers.PickingDispatcherWorker;

public class SimulationBuilder {
	private SimulationBase simulation;
	private PickingStorage pickingStorage;
	private EventLog eventLog;
	private ReportService reportService;
	private PickingService pickingService;
	private CourierPickingsInfo courierPickings;
	private SimulationType simulationType;

	public SimulationBuilder() {
		this.pickingStorage = new PickingStorage();
		this.eventLog = new EventLog();
		this.courierPickings = new CourierPickingsInfo();
		this.pickingService = new PickingService(eventLog, pickingStorage,
				courierPickings);
		this.reportService = new ReportService(new ReportStorage());
	}

	public SimulationBuilder addPickingStorage(PickingStorage pickingStorage) {
		this.pickingStorage = pickingStorage;
		return this;
	}

	public SimulationBuilder addEventLog(EventLog eventLog) {
		this.eventLog = eventLog;
		return this;
	}

	public SimulationBuilder addReportService(ReportService reportService) {
		this.reportService = reportService;
		return this;
	}

	public SimulationBuilder addSimulationType(SimulationType simulationType) {
		this.simulationType = simulationType;
		return this;
	}

	public SimulationBuilder addPickingDispatcher(
			PickingDispatcherWorker pickingDispatcher) {
		this.simulation.getThreadPool().submit(pickingDispatcher);
		return this;
	}

	public SimulationBase build() {
		if (this.simulationType == null)
			throw new IllegalStateException("Simulation type not specified");

		this.simulation = (this.simulationType == SimulationType.PICKINGS_NUMBER) ? new PickingsSimulation(
				pickingService, reportService, courierPickings)
				: new ReportsSimulation(pickingService, reportService,
						courierPickings);

		return simulation;
	}
}
