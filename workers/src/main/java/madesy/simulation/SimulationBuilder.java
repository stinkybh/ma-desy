package madesy.simulation;

import madesy.model.services.ReportService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.ReportStorage;
import madesy.workers.PickingDispatcherWorker;

public class SimulationBuilder {
	private SimulationBase simulation;
	private PickingStorage pickingStorage;
	private EventLog eventLog;
	private ReportService reportService;
	private SimulationType simulationType;
	
	public SimulationBuilder() {
		this.pickingStorage = new PickingStorage();
		this.eventLog = new EventLog();
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
	
	public SimulationBuilder addPickingDispatcher(PickingDispatcherWorker pickingDispatcher) {
		this.simulation.getThreadPool().submit(pickingDispatcher);
		return this;
	}
	
	public SimulationBase build() {
		if (this.simulationType == null)
			throw new IllegalStateException("Simulation type not specified");
		
		this.simulation = (this.simulationType == SimulationType.PICKINGS_NUMBER) 
				? new PickingsSimulation(pickingStorage, eventLog, 
						reportService)
				: new ReportsSimulation(pickingStorage, eventLog,
						reportService);
				
		return simulation;
	}
}
