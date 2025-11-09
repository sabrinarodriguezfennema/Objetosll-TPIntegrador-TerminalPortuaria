package mejorCircuito;

import java.util.List;

import interfaces.RutaMaritima;

public abstract class MejorCircuito {

    public RutaMaritima mejorEntre(List<RutaMaritima> rutas) {
        
    	RutaMaritima mejor = rutas.get(0);
        
        for (RutaMaritima current : rutas) {
            if (condicionDeMejor(current, mejor)) {
                mejor = current;
            }
        }
        return mejor;
    }

    protected abstract boolean condicionDeMejor(RutaMaritima current, RutaMaritima mejor);
}