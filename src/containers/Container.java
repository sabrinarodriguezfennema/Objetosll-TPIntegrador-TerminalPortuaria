package containers;

public abstract class Container {
   
	int peso;
	int altura;
	int ancho;
	int largo;
	String idAlfabetico;
	int idNumerico;
	
	public String getIdentificador() {
		return idAlfabetico + idNumerico;
	}
	
	public int getPeso() {
		return peso;
	}
}
