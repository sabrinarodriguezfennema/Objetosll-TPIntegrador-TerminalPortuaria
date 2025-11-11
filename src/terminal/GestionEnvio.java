package terminal;

import java.util.List;

import clases.MotorDeBusqueda;
import excepciones.OperacionNoDisponibleException;
import interfaces.IShipper;
import interfaces.IConsignee;
import interfaces.Container;
import interfaces.EmpresaTransportista;
import interfaces.IRutaMaritima;
import interfaces.Servicio;

public interface GestionEnvio {
	
	//Exportación
	public MotorDeBusqueda cronogramaExportacion(Terminal t);
	public void exportar(Container c, Terminal t, IRutaMaritima rm, List<Servicio> servicios, IShipper exportador, EmpresaTransportista empresa) throws OperacionNoDisponibleException;

	//Importación
	public void datosParaElRetiro(IConsignee importador, EmpresaTransportista empresa, Container c) throws OperacionNoDisponibleException;

}
