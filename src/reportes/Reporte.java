package reportes;

import java.util.List;
import java.util.Set;

import interfaces.IBuque;
import interfaces.IContainer;
import interfaces.IOrdenDeExportacion;
import interfaces.IOrdenDeImportacion;
import interfaces.IViaje;

public class Reporte {

	private IViaje viaje;
	private List<IOrdenDeExportacion> exportaciones;
	List<IOrdenDeImportacion> importaciones;

	public Reporte(IViaje viaje,List<IOrdenDeImportacion> importaciones, List<IOrdenDeExportacion> exportaciones ) {
		this.viaje = viaje;
		this.exportaciones = exportaciones;
		this.importaciones = importaciones;
	}

	public String generarReporteMuelle() {
		IBuque buque1 = viaje.getBuque();
		int cantidad = buque1.getContainers().size();

		return "Buque: " + buque1.getNombre() + "\nFecha de arribo: " + viaje.fechaSalida() + "\nFecha de partida: "
				+ viaje.fechaSalida() + "\nContenedores operados: " + cantidad + "\n";
	}

	public String generarReporteAduana() {
		IBuque buque1 = viaje.getBuque();
		Set<IContainer> containers = buque1.getContainers();
		StringBuilder reporte = new StringBuilder();

		reporte.append("<html><body>");
		reporte.append("<h1>Reporte Aduana</h1>");
		reporte.append("<p><b>Buque:</b> ").append(buque1.getNombre()).append("</p>");
		reporte.append("<p><b>Arribo:</b> ").append(viaje.fechaSalida()).append("</p>");
		reporte.append("<p><b>Partida:</b> ").append(viaje.fechaSalida()).append("</p>");
		reporte.append("<h4>Contenedores:</h4>");
		reporte.append("<ul>");
		for (IContainer c : containers) {
			reporte.append("<li>").append("ID: ").append(c.getId()).append(" - Tipo: ").append(c.getTipo())
					.append("</li>");
		}
		reporte.append("</ul>");
		reporte.append("</body></html>");

		return reporte.toString();
	}

	public String generarReporteBuque() {

		IBuque buque1 = viaje.getBuque();
		StringBuilder reporte = new StringBuilder();

		reporte.append("<report>\n");
		reporte.append("  <import>\n");

		buque1.getContainers().stream()
				.filter(container -> importaciones.stream().anyMatch(imp -> imp.getDatosDeCarga().equals(container)))
				.forEach(container -> reporte.append("    <item>").append(container.getId()).append("</item>\n"));

		reporte.append("  </import>\n");
		reporte.append("  <export>\n");

		buque1.getContainers().stream()
				.filter(container -> exportaciones.stream().anyMatch(exp -> exp.getDatosDeCarga().equals(container)))
				.forEach(container -> reporte.append("    <item>").append(container.getId()).append("</item>\n"));

		reporte.append("  </export>\n");
		reporte.append("</report>");

		return reporte.toString();
	}

}
