package servicios;

import java.time.Duration;
import java.time.LocalDateTime;

import containers.Container;
import containers.ContainerReefer;

public class ServicioElectrico implements Servicio {
	
	private int precioPorKw;
	private LocalDateTime diaIngreso;
	private LocalDateTime diaEgreso;
	
	public ServicioElectrico(int precioPorKw, LocalDateTime diaIngreso, LocalDateTime diaEgreso) {
		this.precioPorKw = precioPorKw;
		this.diaEgreso = diaEgreso;
		this.diaIngreso = diaIngreso;
	}

	@Override
	public int getPrecio(Container c) {
	        ContainerReefer rc = (ContainerReefer) c; // ya que solo el containerReefer usa el servicioElectrico

	        return (int) (rc.getkwPorHora() * this.tiempoEnTerminalEnHoras() * precioPorKw);
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
