package ordenes;


import java.time.Duration;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import interfaces.IConsignee;
import interfaces.IFactura; 
import facturacion.Factura;
import interfaces.IOrdenDeImportacion;
import interfaces.IViaje;
import interfaces.IContainer;
import servicios.ServicioAlmacenamientoExcedente;
import terminal.TerminalGestionada;


public class OrdenDeImportacion extends Orden implements IOrdenDeImportacion{
	
	private int diasTolerancia;
	private LocalDateTime fechaYHoraDeLlegada;
	

	public OrdenDeImportacion(IConsignee cliente, IContainer datosDeCarga, String patenteCamion, String dniChofer, LocalDateTime fechaYHoraDeLlegada) {
		super(datosDeCarga, patenteCamion, dniChofer, cliente);
		this.diasTolerancia = 1;
		this.fechaYHoraDeLlegada = fechaYHoraDeLlegada;
	}
	
	public boolean verificarAutorizacion(String patenteCamion, String dniChofer) {
		
		return this.dniChofer == dniChofer && this.patenteCamion == patenteCamion;
	}
	 
	@Override
	public IFactura generarFactura(LocalDateTime  fechaDeRetiro, double montoPorDiaExcedente, IViaje viaje) {
	    
	    int díasExcedentes = (int) Duration.between(this.fechaYHoraDeLlegada, fechaDeRetiro).toDays();

	    if (díasExcedentes > this.diasTolerancia) {
	    	int díasDeMas = díasExcedentes - this.diasTolerancia;
	    	this.agregarServicio(new ServicioAlmacenamientoExcedente(montoPorDiaExcedente, díasDeMas));
	    }
	    return new Factura(servicios, viaje);
	}
	
	@Override
	public void registrarEn(TerminalGestionada terminalGestionada) {
		terminalGestionada.registrar(this);	
	}


}
