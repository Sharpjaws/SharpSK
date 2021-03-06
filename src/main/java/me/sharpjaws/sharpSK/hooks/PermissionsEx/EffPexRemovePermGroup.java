package me.sharpjaws.sharpSK.hooks.PermissionsEx;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EffPexRemovePermGroup extends Effect {
	private Expression<String> s;
	private Expression<String> s2;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		s = (Expression<String>) expr[0];
		s2 = (Expression<String>) expr[1];

		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "pex remove permission %string% from group %string%";
	}

	@Override
	protected void execute(Event e) {
		PermissionsEx.getPermissionManager().getGroup(s2.getSingle(e)).removePermission(s.getSingle(e));
	}
}
