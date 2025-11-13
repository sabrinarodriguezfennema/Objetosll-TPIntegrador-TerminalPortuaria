package facturacion;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.*;

import interfaces.IViaje;
import interfaces.IServicio;

public class FacturaTest {

    private IServicio servicio1;
    private IServicio servicio2;
    private IViaje viaje;
    private Set<IServicio> servicios;
    private Factura factura;
    private Factura facturaViaje;

    @BeforeEach
    void setUp() {
    	
        servicio1 = mock(IServicio.class);
        servicio2 = mock(IServicio.class);
        viaje = mock(IViaje.class);

        when(servicio1.getPrecio(null)).thenReturn(1000.0);
        when(servicio2.getPrecio(null)).thenReturn(2000.0);
        when(servicio1.getFecha()).thenReturn(LocalDateTime.of(2025, 11, 10, 0, 0));
        when(servicio2.getFecha()).thenReturn(LocalDateTime.of(2025, 11, 11, 0, 0));

        when(viaje.fechaSalida()).thenReturn(LocalDateTime.of(2025, 11, 12, 0, 0));

        servicios = new HashSet<>();
        servicios.add(servicio1);
        servicios.add(servicio2);

        factura = new Factura(servicios);
        facturaViaje = new Factura(servicios, viaje);
    }
        

    @Test
    void facturaSoloConServicios() {
        
        List<ItemDesglose> desglose = factura.desglose();

        assertEquals(2, desglose.size());
        assertEquals(3000.0, factura.montoTotal(), 0.01);

        verify(servicio1).getPrecio(null);
        verify(servicio2).getPrecio(null);
    }

    @Test
    void facturaConServiciosYViaje() {
       
        List<ItemDesglose> desglose = facturaViaje.desglose();

        assertEquals(3, desglose.size());
        assertEquals(3000.0, facturaViaje.montoTotal(), 0.01);
        
        verify(servicio1).getPrecio(null);
        verify(servicio2).getPrecio(null);
        verify(viaje).precioTotal();
    }
    
  //test okey
}
