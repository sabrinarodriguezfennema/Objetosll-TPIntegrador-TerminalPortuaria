package ordenes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import interfaces.Container;
import interfaces.IOrdenDeExportacion;
import interfaces.Shipper;
import terminal.Terminal;
import terminal.TerminalGestionada;

public class OrdenDeExportacion extends Orden implements IOrdenDeExportacion {
	
	private Shipper cliente;
	private LocalDate fechaSalida;
	private LocalDate fechaLlegada;
	private LocalDateTime turno;
	private Terminal terminalDestino;

	public OrdenDeExportacion(Shipper cliente, Container datosDeCarga, String patenteCamion, String dniChofer, LocalDate fechaSalida, LocalDate fechaLlegada, Terminal terminalDestino) {
		super(datosDeCarga, patenteCamion, dniChofer);
		this.cliente = cliente;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.terminalDestino = terminalDestino;
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

	@Override
	public void registrarEn(TerminalGestionada terminalGestionada) {
		terminalGestionada.registrar(this);	
	}


}
