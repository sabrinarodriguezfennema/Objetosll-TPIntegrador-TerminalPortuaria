package servicios;

import java.time.Duration;
import java.time.LocalDateTime;

import interfaces.IContainer;
import containers.ContainerReefer;

public class ServicioElectrico extends Servicio {
	
	private double precioPorKw;
	private LocalDateTime diaIngreso;
	private LocalDateTime diaEgreso;
	
	public ServicioElectrico(double precioPorKw, LocalDateTime diaIngreso, LocalDateTime diaEgreso) {
		this.precioPorKw = precioPorKw;
		this.diaEgreso = diaEgreso;
		this.diaIngreso = diaIngreso;
	}

	@Override
	public double getPrecio(IContainer c) {
	        ContainerReefer rc = (ContainerReefer) c; // ya que solo el containerReefer usa el servicioElectrico

	        return (rc.getkwPorHora() * this.tiempoEnTerminalEnHoras() * precioPorKw);
	}
	
	public LocalDateTime getDiaIngreso() {
		return diaIngreso;
	}
	
	public LocalDateTime getDiaEgreso() {
		return diaEgreso;
	}
	
	public int tiempoEnTerminalEnHoras() {
		Duration duracion = Duration.between(diaIngreso, diaEgreso);
		return (int) duracion.toHours();
	}
}
