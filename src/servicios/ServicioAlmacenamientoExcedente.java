package servicios;

import containers.Container;

public class ServicioAlmacenamientoExcedente extends Servicio {
	
	private double costoPorExcedente;
	private int diasExcedidos; // TODAVIA FALTA CALCULAR COMO SABER LOS DIAS EXCEDIDOS
	
	public ServicioAlmacenamientoExcedente(double costoPorExcedente,int diasExcedidos) {
		this.costoPorExcedente = costoPorExcedente;
		this.diasExcedidos = diasExcedidos;
	}

	@Override
	public double getPrecio(Container c) {
		return costoPorExcedente * diasExcedidos;
	}
	
	public double getCostoPorExcedente() {
		return costoPorExcedente;
	}
	
	public int getDiasExcedidos() {
		return diasExcedidos; // IMPLEMENTAR MEJOR 
	}
}
