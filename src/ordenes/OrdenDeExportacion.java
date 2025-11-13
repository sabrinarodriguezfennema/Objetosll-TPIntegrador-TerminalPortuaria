package ordenes;

import java.time.LocalDateTime;

import facturacion.Factura;
import interfaces.IContainer;
import interfaces.IFactura;
import interfaces.IOrdenDeExportacion;
import interfaces.IShipper;
import interfaces.ITerminal;
import interfaces.IViaje;
import terminal.Terminal;
import terminal.TerminalGestionada;

public class OrdenDeExportacion extends Orden implements IOrdenDeExportacion {
	
	private LocalDateTime fechaSalida;
	private LocalDateTime fechaLlegada;
	private LocalDateTime turno;
	private ITerminal terminalDestino;

	public OrdenDeExportacion(IShipper cliente, IContainer datosDeCarga, String patenteCamion, String dniChofer, LocalDateTime salida, LocalDateTime llegada, ITerminal terminal) {
		super(datosDeCarga, patenteCamion, dniChofer, cliente);
		this.fechaSalida = salida;
		this.fechaLlegada = llegada;
		this.terminalDestino = terminal;
		this.cliente = cliente;
	}
	

	public void asignarTurno (LocalDateTime turno) {
		((IShipper) cliente).fechaDeExportacion(turno);
		this.turno = turno;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}


	public LocalDateTime getFechaLlegada() {
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
	public ITerminal getTerminalDestino() {
		return terminalDestino;
	}

	@Override
	public IFactura generarFactura(LocalDateTime  fechaDeRetiro, double montoPorDiaExcedente, double precioPorKw, IViaje viaje) {
		return new Factura(servicios);
	}

}
