package main;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import filtro.FechaSalidaAntesDe;
import filtro.FechaSalidaDespuesDe;
import buque.Buque;
import buqueViaje.Coordenadas;
import circuito.Circuito;
import clientes.Shipper;
import containers.ContainerDry;
import containers.ContainerReefer;
import excepciones.OperacionNoDisponibleException;
import facturacion.Factura;
import filtro.Filtro;
import filtro.FiltroCompuestoAND;
import bl.BillOfLading;
import bl.BLEspecial;
import bl.BLSimple;
import clientes.Consignee;
import interfaces.IBuque;
import interfaces.IBuqueViaje;
import interfaces.ICircuito;
import interfaces.ICliente;
import interfaces.IConsignee;
import interfaces.IContainer;
import interfaces.IEmpresaTransportista;
import interfaces.INaviera;
import interfaces.IRutaMaritima;
import interfaces.IShipper;
import interfaces.ITerminal;
import interfaces.ITramo;
import interfaces.IViaje;
import mejorCircuito.GestorDeRutas;
import mejorCircuito.MenorTiempo;
import motorDeBusqueda.MotorDeBusqueda;
import naviera.Naviera;
import tramo.Tramo;
import traslado.EmpresaTransportista;
import terminal.TerminalGestionada;
import terminal.Terminal;

public class Main {

	public static void main(String[] args) throws OperacionNoDisponibleException {
		TerminalGestionada terminalBuenosAires = new TerminalGestionada("Buenos Aires", new Coordenadas(0, 0));
		ITerminal terminalUruguay = new Terminal("Uruguay", new Coordenadas(60, 10));
		ITerminal terminalBrasil = new Terminal("Brasil", new Coordenadas(100, 100));
		ITerminal terminalMisiones = new Terminal("Misiones", new Coordenadas(40, 50));
		ITerminal terminalCorrientes = new Terminal("Corrientes", new Coordenadas(40, 40));
		
		ITramo tramoBsAsACorrientes = new Tramo(terminalBuenosAires, terminalCorrientes, Duration.ofDays(2), 300);
		ITramo tramoCorrientesAMisiones = new Tramo(terminalCorrientes, terminalMisiones, Duration.ofDays(2), 300);
		ITramo tramoMisionesAUruguay = new Tramo(terminalMisiones, terminalUruguay, Duration.ofDays(2), 300);
		ITramo tramoUruguayABrasil = new Tramo(terminalUruguay, terminalBrasil, Duration.ofDays(2), 300);
		ITramo tramoBrasilABsAs = new Tramo(terminalUruguay, terminalBrasil, Duration.ofDays(2), 300);
		
		ITramo tramoCorrientesAUruguay = new Tramo(terminalCorrientes, terminalUruguay, Duration.ofDays(2), 300);
		ITramo tramoUruguayABsAs = new Tramo(terminalUruguay, terminalBuenosAires, Duration.ofDays(2), 300);
		
		ITramo tramoBsAsAUruguay = new Tramo(terminalBuenosAires, terminalUruguay, Duration.ofDays(2), 300);
		
		List<ITramo> paraCircuitoBsAsABrasil = new ArrayList<ITramo>(List.of(tramoBsAsACorrientes, tramoCorrientesAMisiones, tramoMisionesAUruguay, tramoUruguayABrasil, tramoBrasilABsAs));
		List<ITramo> paraCircuitoBsAsAUruguay = new ArrayList<ITramo>(List.of(tramoBsAsACorrientes, tramoCorrientesAUruguay, tramoUruguayABsAs));
		List<ITramo> paraCircuitoUruguayABsAs = new ArrayList<ITramo>(List.of(tramoUruguayABsAs, tramoBsAsAUruguay));
		List<ITramo> paraCircuitoUruguayACorrientes = new ArrayList<ITramo>(List.of(tramoUruguayABsAs, tramoBsAsACorrientes, tramoCorrientesAUruguay));
		
		ICircuito circuitoBsAsABrasil = new Circuito(paraCircuitoBsAsABrasil);
		ICircuito circuitoBsAsAUruguay = new Circuito(paraCircuitoBsAsAUruguay);
		ICircuito circuitoUruguayABsAs = new Circuito(paraCircuitoUruguayABsAs);
		ICircuito circuitoUruguayACorrientes = new Circuito(paraCircuitoUruguayACorrientes);
		
		INaviera naviera1 = new Naviera();
		naviera1.agregarCircuito(circuitoBsAsABrasil);
		naviera1.agregarCircuito(circuitoBsAsAUruguay);
		INaviera naviera2 = new Naviera();
		naviera2.agregarCircuito(circuitoUruguayABsAs);
		naviera2.agregarCircuito(circuitoUruguayACorrientes);
		
		IBuque buque1 = new Buque("Buque 1");
		IBuque buque2 = new Buque("Buque 2");
		IBuque buque3 = new Buque("Buque 3");
		
		naviera1.agregarBuque(buque1);
		naviera1.agregarBuque(buque2);
		naviera2.agregarBuque(buque3);
		
		IViaje primerViaje = naviera1.crearViaje(LocalDate.of(2025, 12, 12).atTime(0,0), buque1, circuitoBsAsABrasil);
		IViaje segundoViaje = naviera2.crearViaje(LocalDate.of(2025, 12, 13).atTime(0,0), buque2, circuitoUruguayACorrientes);
		IViaje tercerViaje = naviera2.crearViaje(LocalDate.of(2025, 12, 14).atTime(0,0), buque1, circuitoBsAsABrasil);
		IViaje cuartoViaje = naviera2.crearViaje(LocalDate.of(2025, 12, 15).atTime(0,0), buque1, circuitoBsAsABrasil);
		
		terminalBuenosAires.registrarNaviera(naviera1);
		terminalBuenosAires.registrarNaviera(naviera2);
		
		//Situación de exportación
		ICliente sabri = new Shipper("Sabri", "sabri@mail");
		MotorDeBusqueda unMotor = terminalBuenosAires.cronogramaExportacion(terminalUruguay);
		
		Filtro unFiltro = new FiltroCompuestoAND(new FechaSalidaAntesDe(LocalDate.of(2025, 12, 15).atTime(0,0)), new FechaSalidaDespuesDe(LocalDate.of(2025, 12, 11).atTime(0,0)));
		unMotor.aplicarFiltro(unFiltro);
		
		GestorDeRutas unGestorDeRutas = new GestorDeRutas(new MenorTiempo());
		IRutaMaritima rutaAElegir = unGestorDeRutas.planificar(unMotor.getRutasFiltradas());
		
		IEmpresaTransportista fleteContainer = new EmpresaTransportista("Flete Container");
		fleteContainer.agregarCamion("abc123");
		fleteContainer.agregarChofer("10101010");
		fleteContainer.agregarCamion("xyz789");
		fleteContainer.agregarChofer("54455445");
		
		List<BillOfLading> listaDePropductosDeSabri = new ArrayList<BillOfLading>(List.of(new BLSimple("Ropa", 2d)));
		BillOfLading productosDeSabri = new BLEspecial(listaDePropductosDeSabri);
		IConsignee consigneeBrasilero = new Consignee("juanciño", "juanciño@mail");
		IContainer containerDeSabri = new ContainerDry(5d, 5d, 10d, "sabr", 1234567, productosDeSabri, consigneeBrasilero);
		
		terminalBuenosAires.exportar(containerDeSabri, terminalBuenosAires, rutaAElegir, (IShipper) sabri, fleteContainer);
		terminalBuenosAires.agregarServicioDeLavado(containerDeSabri);
		LocalDateTime laFechaDeEntrega = ((IShipper) sabri).getFechaDeExportacion();
		terminalBuenosAires.entregaDeContainer("xyz789", "54455445", containerDeSabri, laFechaDeEntrega);
		naviera1.iniciarViaje(primerViaje);
		
		IBuqueViaje buqueViajeDelPrimerViaje = terminalBuenosAires.getBuqueViajes().iterator().next();
		buqueViajeDelPrimerViaje.recibirCoordenadas(new Coordenadas(0, 0));
		buqueViajeDelPrimerViaje.recibirCoordenadas(new Coordenadas(0, 0));
		buqueViajeDelPrimerViaje.recibirCoordenadas(new Coordenadas(0, 0));
		buqueViajeDelPrimerViaje.recibirCoordenadas(new Coordenadas(0, 0));
		buqueViajeDelPrimerViaje.recibirCoordenadas(new Coordenadas(1, 0));
		buqueViajeDelPrimerViaje.recibirCoordenadas(new Coordenadas(1, 1));
		
//		System.out.print(sabri.getMailsRecibidos());
//		System.out.print(sabri.getFacturasRecibidas());
		terminalBuenosAires.registrarPago(sabri.getFacturasRecibidas().get(0));
		
		//Situación de importación
		ICliente nico = new Consignee("Nico", "nico@mail");
		List<BillOfLading> listaDePropductosDeNico = new ArrayList<BillOfLading>(List.of(new BLSimple("Ropa", 2d)));
		BillOfLading productosDeNico = new BLEspecial(listaDePropductosDeNico);
		IContainer containerDeNico = new ContainerReefer(5d, 5d, 10d, "nico", 7654321, productosDeNico, (IConsignee) nico, 25);
		buque2.addContainer(containerDeNico);
		
		terminalBuenosAires.datosParaElRetiro((IConsignee) nico, fleteContainer, containerDeNico);
		terminalBuenosAires.agregarServicioDeLavado(containerDeNico);
		naviera2.iniciarViaje(segundoViaje);
		
		IBuqueViaje buqueViajeDelSegundoViaje = terminalBuenosAires.getBuqueViajes().iterator().next();
		buqueViajeDelSegundoViaje.recibirCoordenadas(new Coordenadas(50, 10));
		buqueViajeDelSegundoViaje.recibirCoordenadas(new Coordenadas(40, 10));
		buqueViajeDelSegundoViaje.recibirCoordenadas(new Coordenadas(30, 10));
		buqueViajeDelSegundoViaje.recibirCoordenadas(new Coordenadas(30, 0));
		buqueViajeDelSegundoViaje.recibirCoordenadas(new Coordenadas(20, 0));
		buqueViajeDelSegundoViaje.recibirCoordenadas(new Coordenadas(10, 0));
		buqueViajeDelSegundoViaje.recibirCoordenadas(new Coordenadas(0, 0));
		
		LocalDateTime laFechaDeRetiro = ((IConsignee)nico).getFechaDeImportacion();
		terminalBuenosAires.retiroDeContainer("abc123", "10101010", "nico7654321", laFechaDeRetiro);
		
		System.out.print(nico.getMailsRecibidos());
		terminalBuenosAires.registrarPago(nico.getFacturasRecibidas().get(0));
		
	}

}
