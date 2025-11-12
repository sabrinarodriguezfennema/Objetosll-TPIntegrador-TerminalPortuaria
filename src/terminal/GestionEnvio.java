package terminal;

import java.util.List;

import clases.MotorDeBusqueda;
import excepciones.OperacionNoDisponibleException;
import interfaces.IShipper;
import interfaces.IConsignee;
import interfaces.IContainer;
import interfaces.EmpresaTransportista;
import interfaces.IRutaMaritima;
import interfaces.IServicio;

public interface GestionEnvio {
	
	//Exportación
	public MotorDeBusqueda cronogramaExportacion(Terminal t);
	public void exportar(IContainer c, Terminal t, IRutaMaritima rm, List<IServicio> servicios, IShipper exportador, EmpresaTransportista empresa) throws OperacionNoDisponibleException;

	//Importación
	public void datosParaElRetiro(IConsignee importador, EmpresaTransportista empresa, IContainer c) throws OperacionNoDisponibleException;

}
