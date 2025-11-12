package interfaces;

import java.util.List;
import java.util.Set;

public interface IBuque {

	public void addContainer(IContainer c);

	public Set<IContainer> getContainers();

	public String getNombre();

	public void removeContainer(IContainer container);


}
