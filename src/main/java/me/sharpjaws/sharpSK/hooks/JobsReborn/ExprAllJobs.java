package me.sharpjaws.sharpSK.hooks.JobsReborn;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprAllJobs extends SimpleExpression<Job> {

	boolean job;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Job> getReturnType() {
		return Job.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			final ParseResult parseResult) {
		job = parseResult.mark == 1 || matchedPattern == 1;
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean paramBoolean) {
		return "all [of|the] jobs";
	}

	@Override
	@Nullable
	protected Job[] get(Event e) {
		ArrayList<Job> jobs = new ArrayList<>();
		for (Job p : Jobs.getJobs()) {
			if (p != null) {
				jobs.add(p);
			}
		}
		return jobs.toArray(new Job[jobs.size()]);
	}

	@Override
	public boolean isLoopOf(final String s) {
		return job && (s.equalsIgnoreCase("job"));
	}
}
