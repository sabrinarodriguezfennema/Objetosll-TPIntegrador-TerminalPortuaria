package interfaces;

public interface IEmpresaTransportista {

	public String asignarCamionPara(IContainer c);

	public String asignarChoferPara(IContainer c);
	
	public void agregarCamion(String patente);
	
	public void agregarChofer(String dni);

}
