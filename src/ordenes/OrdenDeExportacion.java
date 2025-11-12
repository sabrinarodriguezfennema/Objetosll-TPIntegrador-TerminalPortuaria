package ordenes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import facturacion.Factura;
import interfaces.IContainer;
import interfaces.IFactura;
import interfaces.IOrdenDeExportacion;
import interfaces.IShipper;
import interfaces.IViaje;
import terminal.Terminal;
import terminal.TerminalGestionada;

public class OrdenDeExportacion extends Orden implements IOrdenDeExportacion {
	
	private LocalDate fechaSalida;
	private LocalDate fechaLlegada;
	private LocalDateTime turno;
	private Terminal terminalDestino;

	public OrdenDeExportacion(IShipper cliente, IContainer datosDeCarga, String patenteCamion, String dniChofer, LocalDate fechaSalida, LocalDate fechaLlegada, Terminal terminalDestino) {
		super(datosDeCarga, patenteCamion, dniChofer, cliente);
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.terminalDestino = terminalDestino;
		this.cliente = cliente;
	}
	

	public void asignarTurno (LocalDateTime turno) {
		((IShipper) cliente).fechaDeExportacion(turno);
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

	@Override
	public Terminal getTerminalDestino() {
		return terminalDestino;
	}

	@Override
	public IFactura generarFactura(LocalDateTime fecha, double montoPorDíaExcedente, IViaje viaje) {
		return new Factura(servicios);
	}

}
