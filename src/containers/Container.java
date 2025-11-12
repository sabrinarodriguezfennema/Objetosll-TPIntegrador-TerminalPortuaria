package containers;

import interfaces.BillOfLading;


import interfaces.IConsignee;
import interfaces.IContainer;

public abstract class Container implements IContainer{ 
   
	private double altura;
	private double ancho;
	private double largo;
	private String idAlfabetico;
	private int idNumerico;
	protected BillOfLading bl;
	private IConsignee consignee;
	protected String tipo;
	
	public Container(double altura, double ancho, double largo, String idAlfabetico, int idNumerico2, BillOfLading bl, IConsignee consignee) {
		this.altura = altura;
		this.ancho = ancho;
		this.largo = largo;
		this.idAlfabetico = idAlfabetico;
		this.idNumerico = idNumerico2;
		this.bl = bl;
		this.consignee = consignee;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public double volumen() {
        return ancho * largo * altura;
    }
	
	public String getId() {
		return idAlfabetico + idNumerico;
	}
	
	public IConsignee getDueño() {
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
