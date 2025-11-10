package containers;

import java.util.ArrayList;
import java.util.List;

import servicios.Servicio;

public class Container { 
   
	private int altura;
	private int ancho;
	private int largo;
	private String idAlfabetico;
	private int idNumerico;
	protected BillOfLading bl;
	private Consignee consignee;
	protected List<Servicio> servicios = new ArrayList<>();
	private String tipo;
	
	public Container(int altura, int ancho, int largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee, List<Servicio> servicios, String tipo) {
		this.altura = altura;
		this.ancho = ancho;
		this.largo = largo;
		this.idAlfabetico = idAlfabetico;
		this.idNumerico = idNumerico;
		this.bl = bl;
		this.consignee = consignee;
		this.servicios = servicios;
		this.tipo = tipo;
	}
	
	public String tipo() {
		return tipo;
	}
	
	public int volumen() {
        return ancho * largo * altura;
    }
	
	public String getId() {
		return idAlfabetico + idNumerico;
	}
	
	public Consignee getConsignee() {
		return consignee;
	}
	
	public BillOfLading getBL() {
		return bl;
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
	
	public List<Servicio> getServicios() {
		return servicios;
	}
	
	public int totalDeLosServicios() {
		int total = 0; 
		
		for (Servicio s : servicios) {
			total += s.getPrecio(this);  
		}
		
		return total;
	}	
	
}
