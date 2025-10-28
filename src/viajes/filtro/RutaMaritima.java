package viajes.filtro;

import java.util.Date;

public interface RutaMaritima {
	
	public Terminal puertoDestino();
	
	public Date fechaSalida();
	
	public Date fechaLlegada();
}
