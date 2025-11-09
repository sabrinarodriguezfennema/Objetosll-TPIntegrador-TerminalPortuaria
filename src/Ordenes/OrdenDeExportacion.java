package Ordenes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Carga.Container;
import Clientes.Shipper;

public class OrdenDeExportacion extends Orden{
	
	private Shipper cliente;
	private LocalDate fechaSalida;
	private LocalDate fechaLlegada;
	private LocalDateTime turno;

	public OrdenDeExportacion(Shipper cliente, Container datosDeCarga, String patenteCamion, String dniChofer, LocalDate fechaSalida, LocalDate fechaLlegada) {
		super(datosDeCarga, patenteCamion, dniChofer);
		this.cliente = cliente;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
	}
	
	public Shipper getShipper() {
		return cliente;
	}
	
	public void asignarTurno (LocalDateTime turno) {
		cliente.fechaDeExportacion(turno);
		this.turno = turno;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}


	public LocalDate getFechaLlegada() {
		return fechaLlegada;
	}

	public LocalDateTime turno() {
		return turno;
	}


}
