package interfaces;

import java.util.List;
import java.util.Set;

public interface IBuque {

	public void addContainer(Container c);

	public Set<Container> getContainers();

	public String getNombre();

	public void removeContainer(Container container);


}
