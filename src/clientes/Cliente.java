package clientes;

import java.util.ArrayList;
import java.util.List;

import interfaces.ICliente;

public abstract class Cliente implements ICliente{
	
    private String nombre;
    private String email;
    private List<String> mailsRecibidos;
    
    public Cliente(String nombre, String email) {

        this.nombre = nombre;
        this.email = email;
        this.mailsRecibidos = new ArrayList<>();
    }
	
    public void recibirMail(String contenido) {
        mailsRecibidos.add(contenido);
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public List<String> getMailsRecibidos() {
        return new ArrayList<>(mailsRecibidos);
    }
}
