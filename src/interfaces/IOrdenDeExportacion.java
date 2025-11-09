package interfaces;

import java.time.LocalDateTime;


public interface IOrdenDeExportacion extends Orden{

	public String getCamion();

	public String getChofer();

	public LocalDateTime turno();

	public Shipper getShipper();

}
