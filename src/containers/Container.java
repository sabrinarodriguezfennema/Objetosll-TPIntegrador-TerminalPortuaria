package containers;

import java.util.ArrayList;
import java.util.List;

import servicios.Servicio;

public class Container { 
   
	private double altura;
	private double ancho;
	private double largo;
	private String idAlfabetico;
	private int idNumerico;
	protected BillOfLading bl;
	private Consignee consignee;
	protected String tipo;
	
	public Container(double altura, double ancho, double largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee) {
		this.altura = altura;
		this.ancho = ancho;
		this.largo = largo;
		this.idAlfabetico = idAlfabetico;
		this.idNumerico = idNumerico;
		this.bl = bl;
		this.consignee = consignee;
	}
	
	public String tipo() {
		return tipo;
	}
	
	public double volumen() {
        return ancho * largo * altura;
    }
	
	public String getIdentificador() {
		return idAlfabetico + idNumerico;
	}
	
	public Consignee getDueño() {
		return consignee;
	}
	
	public BillOfLading getBL() {
		return bl;
	}
	
	public double getAltura() {
		return altura;
	}
	
	public double getAncho() {
		return ancho;
	}
	
	public double getLargo() {
		return largo;
	}
	
	public int getPeso() {
		return bl.pesoTotal();
	}
	
//	public int totalDeLosServicios() {
//		int total = 0; 
//		
//		for (Servicio s : servicios) {
//			total += s.getPrecio(this);  
//		}
//		
//		return total;
//	}	
	
}
