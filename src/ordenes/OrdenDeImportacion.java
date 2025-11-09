package ordenes;


import java.time.Duration;
import java.time.LocalDateTime;

import interfaces.IConsignee;
import interfaces.Factura;
import interfaces.IOrdenDeImportacion;
import interfaces.Container;
import servicios.Almacenamiento;
import terminal.TerminalGestionada;

public class OrdenDeImportacion extends Orden implements IOrdenDeImportacion{
	
	private IConsignee cliente;
	private int horasTolerancia;
	private LocalDateTime fechaYHoraDeLlegada;
	private Almacenamiento almacenamiento;
	

	public OrdenDeImportacion(IConsignee cliente, Container datosDeCarga, String patenteCamion, String dniChofer, LocalDateTime fechaYHoraDeLlegada) {
		super(datosDeCarga, patenteCamion, dniChofer);
		this.cliente = cliente;
		this.horasTolerancia = 24;
		this.fechaYHoraDeLlegada = fechaYHoraDeLlegada;
	}
	
	public boolean verificarAutorizacion(String patenteCamion, String dniChofer) {
		
		return this.dniChofer == dniChofer && this.patenteCamion == patenteCamion;
	}
	
	public void retiroContainer(LocalDateTime fechaDeRetiro, double montoPorHoraExcedente, Factura factura) {
	    
	    int horasExcedentes = (int) Duration.between(this.fechaYHoraDeLlegada, fechaDeRetiro).toHours();

	    if (horasExcedentes > this.horasTolerancia) {
	    	int horasDeMas = horasExcedentes - this.horasTolerancia;
	    	almacenamiento = new Almacenamiento(horasDeMas, montoPorHoraExcedente);
	    	factura.setServicioDeAlmacenamiento(almacenamiento);
	    }
	}
	
	@Override
	public void registrarEn(TerminalGestionada terminalGestionada) {
		terminalGestionada.registrar(this);	
	}

	public IConsignee getConsignee() {
		return cliente;
	}

}
