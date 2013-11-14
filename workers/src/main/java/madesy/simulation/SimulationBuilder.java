package madesy.simulation;

import java.util.concurrent.ExecutorService;

import madesy.model.services.ReportService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.ReportStorage;

public class SimulationBuilder {
	private SimulationBase simulation;
	private PickingStorage pickingStorage;
	private EventLog eventLog;
	private ReportService reportService;
	private SimulationType simulationType;
	private ExecutorService pool;
	
	
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

	public SimulationBuilder addExecutorService(ExecutorService executorService) {
		this.pool = executorService;
		return this;
	}
	
	public SimulationBase build() {
		if (this.simulationType == null)
			throw new IllegalStateException("Simulation type not specified");
		if (this.pool == null)
			throw new IllegalStateException("ExecutorService not added");
		
		this.simulation = (this.simulationType == SimulationType.PICKINGS_NUMBER) 
				? new PickingsSimulation(pool, pickingStorage, eventLog, 
						reportService)
				: new ReportsSimulation(pool, pickingStorage, eventLog,
						reportService);
				
		return simulation;
	}
}
