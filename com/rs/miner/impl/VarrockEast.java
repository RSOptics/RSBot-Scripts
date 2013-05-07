package com.rs.miner.impl;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.Path;
import org.powerbot.game.api.wrappers.node.SceneObject;

import com.rs.miner.Main;
import com.rs.miner.Main.Location;
import com.rs.miner.Main.Mode;
import com.rs.miner.generic.AbstractStrategy;

public class VarrockEast extends AbstractStrategy {

  public final Tile BANK_TILE = new Tile(3254, 3419, 0);
	public final Tile MINE_TILE = new Tile(3286, 3364, 0);

	public final Area MINE_AREA = new Area(new Tile[] {
			new Tile(3294, 3356, 0), new Tile(3278, 3372, 0) });

	public final Area BANK_AREA = new Area(new Tile[] {
			new Tile(3248, 3425, 0), new Tile(3257, 3416, 0) });

	@Override
	public void prepare() {
	}

	@Override
	public boolean canExecute() {
		return Main.mode == Mode.BANKING
				&& Main.location == Location.VARROCK_EAST;
	}

	@Override
	public void execute() {
		SceneObject rock = SceneEntities.getNearest(Main.getRockIDs());
		if (Inventory.isFull()) {
			if (BANK_AREA.contains(Players.getLocal().getLocation())) {
				if (Bank.isOpen()) {
					Bank.deposit(Main.oreID, 28);
				} else {
					Bank.open();
				}
			} else if (MINE_AREA.contains(Players.getLocal().getLocation())) {
				Path path = Walking.findPath(BANK_TILE);
				path.traverse();
			}
		} else {
			if (BANK_AREA.contains(Players.getLocal().getLocation())) {
				Path path = Walking.findPath(MINE_TILE);
				path.traverse();
			} else if (MINE_AREA.contains(Players.getLocal().getLocation())) {
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
}
