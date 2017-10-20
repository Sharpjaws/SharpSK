package me.sharpjaws.sharpSK.hooks.GroupManager;

import javax.annotation.Nullable;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.data.Group;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffGroupManagerAddPermission extends Effect {
	private Expression<OfflinePlayer> player;
	private Expression<String> perm;
	private Expression<World> world;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		perm = (Expression<String>) expr[0];
		player = (Expression<OfflinePlayer>) expr[1];
		world = (Expression<World>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[sharpsk] (gman|group[ ]manager) add perm[ission] %string% to [player] %offlineplayer% [in [world] %-world%]";
	}

	@Override
	protected void execute(Event e) {
		final Plugin GMplugin = Bukkit.getPluginManager().getPlugin("GroupManager");
		GroupManager GM = (GroupManager) GMplugin;
		OverloadedWorldHolder handler = null;
		if (player == null) {
			return;
		}
		;

		if (player.getSingle(e).isOnline()) {
			handler = GM.getWorldsHolder().getWorldDataByPlayerName(player.getSingle(e).getName());
		} else {
			handler = GM.getWorldsHolder().getDefaultWorld();
		}

		if (world != null) {
			handler = GM.getWorldsHolder().getWorldData(world.getSingle(e).getName());
		}
		handler.getUser(player.getSingle(e).getName()).addPermission(perm.getSingle(e));
		;

	}
}
