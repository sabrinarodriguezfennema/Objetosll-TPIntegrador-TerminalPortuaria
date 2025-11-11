package mejorCircuito;

import java.util.List;

import interfaces.IRutaMaritima;

public abstract class MejorCircuito {

    public IRutaMaritima mejorEntre(List<IRutaMaritima> rutas) {
        
    	IRutaMaritima mejor = rutas.get(0);
        
        for (IRutaMaritima current : rutas) {
            if (condicionDeMejor(current, mejor)) {
                mejor = current;
            }
        }
        return mejor;
    }

    protected abstract boolean condicionDeMejor(IRutaMaritima current, IRutaMaritima mejor);
}