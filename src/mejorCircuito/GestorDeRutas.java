package mejorCircuito;

import java.util.List;

import interfaces.RutaMaritima;

public class GestorDeRutas {
	
	private MejorCircuito criterio;

    public GestorDeRutas(MejorCircuito criterio) {
        this.criterio = criterio;
    }

    public void setCriterio(MejorCircuito nuevoCriterio) {
        this.criterio = nuevoCriterio;
    }

    public RutaMaritima planificar(List<RutaMaritima> rutas) {
        return criterio.mejorEntre(rutas);
    }
}
