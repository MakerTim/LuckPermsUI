package me.lucko.luckperms.standalone.view.popup;

import java.util.Arrays;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.groups.Group;
import me.lucko.luckperms.standalone.util.form.FormBase;
import me.lucko.luckperms.standalone.util.form.FormItem;
import me.lucko.luckperms.standalone.util.form.FormResultType;
import me.lucko.luckperms.standalone.view.scene.Manager;

public class PermissionChange extends FormBase {

	public PermissionChange(Manager view, Group group, String node, String server, String world, boolean active) {
		super(view, "Add permission for group: " + group.getName(),
				Arrays.asList(new FormItem("Permission", new TextField(node == null ? "" : node)),
					new FormItem("Server [optional]", new TextField(server == null ? "" : server)),
					new FormItem("World [optional]", new TextField(world == null ? "" : world)),
					new FormItem("Allow permission?", preDoneCheckbox(active))),
				Arrays.asList(FormResultType.OK, FormResultType.CANCEL));
	}

	public PermissionChange(Manager view, Group group, Node perms) {
		this(view, group, perms.getPermission(), perms.getServer().orElse(null), perms.getWorld().orElse(null),
				perms.getValue());
	}

	public static CheckBox preDoneCheckbox(boolean selected) {
		CheckBox checkBox = new CheckBox();
		checkBox.setSelected(selected);
		return checkBox;
	}
}
