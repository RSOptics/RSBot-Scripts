package com.rs.miner.generic;

import org.powerbot.core.script.methods.Players;
import org.powerbot.core.script.wrappers.Player;

public abstract class AbstractStrategy {

	private boolean initialized;

	public abstract void prepare();

	public abstract boolean canExecute();

	public abstract void execute();

	public boolean isInitialized() {
		return initialized;
	}

	public void initialize() {
		prepare();
		initialized = true;
	}

	protected static Player getLocal() {
		return Players.getLocal();
	}

}
