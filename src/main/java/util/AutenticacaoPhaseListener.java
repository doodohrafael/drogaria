package util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener{

	public void afterPhase(PhaseEvent event) {
		
	}

	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
