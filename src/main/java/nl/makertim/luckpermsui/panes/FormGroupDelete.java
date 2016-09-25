package nl.makertim.luckpermsui.panes;

import java.util.Arrays;

import nl.makertim.luckpermsui.elements.LuckPermTextField;
import nl.makertim.luckpermsui.form.FormBase;
import nl.makertim.luckpermsui.form.FormItem;
import nl.makertim.luckpermsui.form.FormResultType;
import nl.makertim.luckpermsui.internal.Group;

public class FormGroupDelete extends FormBase {

	public FormGroupDelete(ViewManager view, Group group) {
		super(view, "DELETE GROUP " + group.getName(),
				Arrays.asList(new FormItem("Confirm with: '" + group.getName() + "'", new LuckPermTextField())),
				Arrays.asList(FormResultType.OK, FormResultType.CANCEL));
	}
}