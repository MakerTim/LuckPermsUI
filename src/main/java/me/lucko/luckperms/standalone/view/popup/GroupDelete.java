package me.lucko.luckperms.standalone.view.popup;

import java.util.Arrays;

import me.lucko.luckperms.groups.Group;
import me.lucko.luckperms.standalone.util.elements.LuckPermTextField;
import me.lucko.luckperms.standalone.util.form.FormBase;
import me.lucko.luckperms.standalone.util.form.FormItem;
import me.lucko.luckperms.standalone.util.form.FormResultType;
import me.lucko.luckperms.standalone.view.scene.Manager;

public class GroupDelete extends FormBase {

	public GroupDelete(Manager view, Group group) {
		super(view, "DELETE GROUP " + group.getName(),
				Arrays.asList(new FormItem("Confirm with: '" + group.getName() + "'", new LuckPermTextField())),
				Arrays.asList(FormResultType.OK, FormResultType.CANCEL));
	}
}
