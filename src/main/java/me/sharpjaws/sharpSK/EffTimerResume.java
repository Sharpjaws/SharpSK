package me.sharpjaws.sharpSK;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.sharpjaws.sharpSK.Threads.CTickTimerThread;
import me.sharpjaws.sharpSK.Threads.CTimerThread;

public class EffTimerResume extends Effect {
	private Expression<String> timer;


	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		timer = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "stop timer %string%";
	}

	@Override
	protected void execute(final Event e) {
		CTimerThread a = null;
		CTickTimerThread b = null;
		for (Thread t : Thread.getAllStackTraces().keySet()) {
	        if (t instanceof CTimerThread) {
	        	if (((CTimerThread) t).instance().getName().equals(timer.getSingle(e))){
	        		a = ((CTimerThread) t).instance();
	        		break;
	        		
	        	}
	        }
	        	 if (t instanceof CTickTimerThread) {
	 	        	if (((CTickTimerThread) t).instance().getName().equals(timer.getSingle(e))){
	 	        		b = ((CTickTimerThread) t).instance();
	 	        		break;
	 	        		
	 	        	}
	        	 }
	        
		}
		try {
		try {
		if (!a.isActive())	{
		a.resumeTimer(a.getName());
		}else{
			
		}
		
		}catch(NullPointerException ex){
		
			if (!b.isActive())	{
			b.resumeTimer(b.getName());
			}else{
			b.resumeTimer(b.getName());
			}
		}
		}catch(NullPointerException ex){
			main core = (main)Bukkit.getPluginManager().getPlugin("SharpSK");
			core.getLogger().warning("Timer "+ "\"" +timer.getSingle(e)+"\"" + " could not be resumed because it does not exist.");
		}
		
	}
	
}