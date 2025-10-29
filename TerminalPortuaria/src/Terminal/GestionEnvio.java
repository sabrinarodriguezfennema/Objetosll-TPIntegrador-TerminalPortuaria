package Terminal;

import java.util.List;

import Carga.Container;
import Clientes.Shipper;
import ClasesParaMockear.MotorDeBusqueda;
import InterfacesParaMockear.RutaMarítima;
import InterfacesParaMockear.Servicio;

public interface GestionEnvio {
	
	//Exportación
	public MotorDeBusqueda cronogramaExportacion(Terminal t);
	public void exportar(Container c, Terminal t, RutaMarítima rm, List<Servicio> servicios, Shipper exportador);

	//Importación
	public void datosParaElRetiro(String patenteCamion, String dniChofer);

}
