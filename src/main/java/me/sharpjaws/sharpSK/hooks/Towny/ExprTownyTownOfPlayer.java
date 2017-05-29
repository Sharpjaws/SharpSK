package me.sharpjaws.sharpSK.hooks.Towny;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTownyTownOfPlayer extends SimpleExpression<String> {

	
	private Expression<Player> resident;
	

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult Result) {
		resident = (Expression<Player>) expr[0];
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "[sharpsk] [towny] town of %player%";
	}


	@Override
	@Nullable
	protected String[] get(Event e) {
		
		Resident a = new Resident(resident.getSingle(e).getName());
		 try {
			return new String[]{a.getTown().getName()};
		} catch (NotRegisteredException e1) {
		return new String[]{};
		}
		
			}

	@Override
	public boolean isSingle() {
		return true;
	}

	

}


