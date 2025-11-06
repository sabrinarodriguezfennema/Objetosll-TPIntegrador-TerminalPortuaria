package containers;

import java.util.ArrayList;
import java.util.List;

import servicios.Servicio;

public abstract class Container { 
   
	private int altura;
	private int ancho;
	private int largo;
	private String idAlfabetico;
	private int idNumerico;
	protected BillOfLading bl;
	private Consignee consignee;
	protected List<Servicio> servicios = new ArrayList<>();
	
	public Container(int altura, int ancho, int largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee, List<Servicio> servicios) {
		this.altura = altura;
		this.ancho = ancho;
		this.largo = largo;
		this.idAlfabetico = idAlfabetico;
		this.idNumerico = idNumerico;
		this.bl = bl;
		this.consignee = consignee;
		this.servicios = servicios;
	}
	
	public int volumen() {
        return ancho * largo * altura;
    }
	
	public String getIdentificador() {
		return idAlfabetico + idNumerico;
	}
	
	public Consignee getConsignee() {
		return consignee;
	}
	
	public int getAltura() {
		return altura;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getLargo() {
		return largo;
	}
	
	public int getPeso() {
		return bl.pesoTotal();
	}
	
	public void agregarServicio(Servicio s) {
		servicios.add(s);
	}
	
	public int totalDeLosServicios() {
		int total = 0; 
		
		for (Servicio s : servicios) {
			total += s.getPrecio(this);  
		}
		
		return total;
	}	
	
}
