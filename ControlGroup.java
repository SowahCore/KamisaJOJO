public class ControlGroup{

	private Model model;
  	private Vue f;
  	public ControlButton controlButton;
  	public ControlMenu controlMenu;

	public ControlGroup(Model model) {
		this.model = model;

  		f = new Vue(model);

  		controlButton = new ControlButton(model, f);
  		controlMenu = new ControlMenu(model, f);

	}

}
