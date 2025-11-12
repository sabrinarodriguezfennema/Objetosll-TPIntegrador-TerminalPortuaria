package containers;

import interfaces.BillOfLading;
import clientes.Consignee;
import interfaces.IContainer;

public abstract class Container implements IContainer{ 
   
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
	
	public String getId() {
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
	
	public double getPeso() {
		return bl.peso();
	}
		
}
