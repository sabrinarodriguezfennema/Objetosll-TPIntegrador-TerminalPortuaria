package mejorCircuito;

import java.util.List;

import interfaces.IRutaMaritima;

public class GestorDeRutas {
	
	private MejorCircuito criterio;

    public GestorDeRutas(MejorCircuito criterio) {
        this.criterio = criterio;
    }

    public void setCriterio(MejorCircuito nuevoCriterio) {
        this.criterio = nuevoCriterio;
    }

    public IRutaMaritima planificar(List<IRutaMaritima> rutas) {
        return criterio.mejorEntre(rutas);
    }
}
