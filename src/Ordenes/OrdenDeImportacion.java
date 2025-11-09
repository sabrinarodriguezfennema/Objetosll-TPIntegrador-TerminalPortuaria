package Ordenes;


import java.time.Duration;
import java.time.LocalDateTime;

import Carga.Container;
import Clientes.Consignee;
import Facturacion.Factura;
import Servicios.Almacenamiento;

public class OrdenDeImportacion extends Orden{
	
	private Consignee cliente;
	private int horasTolerancia;
	private LocalDateTime fechaYHoraDeLlegada;
	private Almacenamiento almacenamiento;
	

	public OrdenDeImportacion(Consignee cliente, Container datosDeCarga, String patenteCamion, String dniChofer, LocalDateTime fechaYHoraDeLlegada) {
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
	


	

}
