package states;

public interface State {
	public void plays(String play);
	public void changeState();
	public void advice();
	public void stats();
}
