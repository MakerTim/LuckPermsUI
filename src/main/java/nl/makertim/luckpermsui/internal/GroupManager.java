package nl.makertim.luckpermsui.internal;

import nl.makertim.luckpermsui.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupManager {

	private static final PreparedStatement INSERT_GROUP = Main.manager
			.prepare("INSERT INTO lp_groups (name, perms) VALUES (?, ?);");

	private static final PreparedStatement REMOVE_GROUP = Main.manager.prepare("DELETE FROM lp_groups WHERE name = ?;");

	private static final PreparedStatement UPDATE_PERM = Main.manager
			.prepare("UPDATE lp_groups SET perms = ? WHERE name = ?;");

	public static void addGroup(Group group) {
		synchronized (INSERT_GROUP) {
			try {
				INSERT_GROUP.setString(1, group.getName());
				INSERT_GROUP.setString(2, group.getJson());
				Main.manager.updatePrepared(INSERT_GROUP);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void removeGroup(Group group) {
		synchronized (REMOVE_GROUP) {
			try {
				REMOVE_GROUP.setString(1, group.getName());
				Main.manager.deletePrepared(REMOVE_GROUP);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void updatePermissions(Group group) {
		synchronized (UPDATE_PERM) {
			try {
				UPDATE_PERM.setString(1, group.getJson());
				UPDATE_PERM.setString(2, group.getName());
				Main.manager.updatePrepared(UPDATE_PERM);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Group createGroup(String name) {
		return new Group(name, "{}");
	}

}