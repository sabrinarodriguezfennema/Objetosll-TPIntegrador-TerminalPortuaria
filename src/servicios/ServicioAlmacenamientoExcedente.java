package servicios;

import containers.Container;

public class ServicioAlmacenamientoExcedente implements Servicio {
	
	private int costoPorExcedente;
	private int diasExcedidos; // TODAVIA FALTA CALCULAR COMO SABER LOS DIAS EXCEDIDOS
	
	public ServicioAlmacenamientoExcedente(int costoPorExcedente,int diasExcedidos) {
		this.costoPorExcedente = costoPorExcedente;
		this.diasExcedidos = diasExcedidos;
	}

	@Override
	public int getPrecio(Container c) {
		return costoPorExcedente * diasExcedidos;
	}
	
	public int getCostoPorExcedente() {
		return costoPorExcedente;
	}
	
	public int getDiasExcedidos() {
		return diasExcedidos; // IMPLEMENTAR MEJOR 
	}
}
