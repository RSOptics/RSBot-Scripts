package com.rs.miner.impl;

import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

import com.rs.miner.Main;
import com.rs.miner.Main.Mode;
import com.rs.miner.generic.AbstractStrategy;

public class Powerlevel extends AbstractStrategy {

  @Override
	public void prepare() {
	}

	@Override
	public boolean canExecute() {
		return Main.mode == Mode.POWER_LEVEL;
	}

	@Override
	public void execute() {
		SceneObject rock = SceneEntities.getNearest(Main.getRockIDs());
		if (Inventory.isFull()) {
			int i = 0;
			while (i != 28) {
				Keyboard.sendKey((char) 1);
				i++;
			}
		} else {
			if (rock != null) {
				if (rock.isOnScreen()) {
					rock.interact("Mine");
				} else {
					Camera.turnTo(rock);
				}
			}
		}
	}
}
