package com.rs.miner;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;

import com.rs.miner.generic.AbstractStrategy;
import com.rs.miner.impl.MiningGuild;
import com.rs.miner.impl.Powerlevel;
import com.rs.miner.impl.Rimmington;
import com.rs.miner.impl.VarrockEast;
import com.rs.miner.impl.VarrockWest;
import com.rs.miner.ui.GUI;

@Manifest(authors = { "mdawg252" }, description = "This is an all in one mining script.", name = "RSMiner")
public class Main extends ActiveScript implements PaintListener {

	public static volatile State state = State.INITIALIZING;
	public static Location location = Location.NULL;
	public static OreType ore = OreType.NULL;
	public static Mode mode = Mode.NULL;

	public static AbstractStrategy strategy;

	private final Color yellow = new Color(255, 255, 0);
	private final Font verdana = new Font("Verdana", 0, 12);

	public static int oresGained = 0;
	public int oresHour;

	public Timer t = new Timer(0);

	public int startExp = Skills.getExperience(Skills.MINING);
	public int currentExp;
	public int gainedExp;

	public static final int[] CLAY_ROCK = { 9711, 9713, 2108, 2109, 14904,
			14905 };
	public static final int[] TIN_ROCK = { 9714, 9716, 11957, 11958, 11959,
			11933, 11934, 11935, 2094, 2095, 14902, 14903 };
	public static final int[] COPPER_ROCK = { 9709, 9708, 9710, 2090, 2091,
			11960, 11961, 11962, 11936, 11937, 11938, 14906, 14907, 14856,
			14857, 14858 };
	public static final int[] SILVER_ROCK = { 11948, 11949, 11950, 2100, 2101 };
	public static final int[] GOLD_ROCK = { 9720, 9722, 11951, 11952, 11953,
			2098, 2099 };
	public static final int[] IRON_ROCK = { 9718, 9717, 9719, 11954, 11955,
			11956, 2092, 2093, 14900, 14901, 14913, 14914 };
	public static final int[] COAL_ROCK = { 2096, 2097, 11963, 11964, 11965,
			11930, 11931, 11932, 14850, 14851, 14852 };
	public static final int[] MITHRIL_ROCK = { 2103, 2102, 11945, 11946, 11947,
			11942, 11943, 11944, 14853, 14854, 14855 };

	public static int oreID;

	@Override
	public void onStart() {
		log.info("RSMiner has been initiated.");
		GUI g = new GUI();
		g.setVisible(true);
	}

	@Override
	public int loop() {
		if (state == State.INITIALIZING) {
			return 1000;
		} else if (strategy != null && strategy.canExecute()) {
			if (strategy.isInitialized()) {
				strategy.execute();
			} else {
				strategy.initialize();
			}
		}
		return Random.nextInt(150, 300);
	}

	@Override
	public void onStop() {
		log.info("Thank you for using RSMiner.");
	}

	public static List<AbstractStrategy> getAvailableStrategies() {
		List<AbstractStrategy> strategies = new ArrayList<AbstractStrategy>();
		strategies.add(new MiningGuild());
		strategies.add(new Powerlevel());
		strategies.add(new Rimmington());
		strategies.add(new VarrockEast());
		strategies.add(new VarrockWest());
		return strategies;
	}

	@Override
	public void onRepaint(Graphics g1) {

		gainedExp = startExp - Skills.getExperience(Skills.MINING);
		int hourExp = (int) ((gainedExp) * 3600000.0 / t.getElapsed());

		Graphics2D g = (Graphics2D) g1;
		g.setFont(verdana);
		g.setColor(yellow);
		g.drawString("Time Running: " + t.toElapsedString(), 12, 24);
		g.drawString("Status: " + state.toString(), 12, 43);
		g.drawString("Ores Gained: " + oresGained + "(/HR)", 12, 62);
		g.drawString("Experience Gained: " + gainedExp + " (" + hourExp
				+ "/HR)", 12, 81);
		g.drawString("RSMiner by mdawg252", 10, 100);
	}

	public static final int[] getRockIDs() {
		if (ore == OreType.CLAY) {
			return CLAY_ROCK;
		} else if (ore == OreType.TIN) {
			return TIN_ROCK;
		} else if (ore == OreType.COPPER) {
			return COPPER_ROCK;
		} else if (ore == OreType.SILVER) {
			return SILVER_ROCK;
		} else if (ore == OreType.GOLD) {
			return GOLD_ROCK;
		} else if (ore == OreType.IRON) {
			return IRON_ROCK;
		} else if (ore == OreType.COAL) {
			return COAL_ROCK;
		} else if (ore == OreType.MITHRIL) {
			return MITHRIL_ROCK;
		} else {
			return null;
		}
	}

	public static enum State {
		DROPPING, MINING, IDLE, WALKING, BANKING, INITIALIZING
	}

	public static enum Location {
		NULL, GUILD, RIMMINGTON, VARROCK_EAST, VARROCK_WEST
	}

	public static enum OreType {
		NULL, CLAY, TIN, COPPER, SILVER, GOLD, IRON, COAL, MITHRIL
	}

	public static enum Mode {
		NULL, BANKING, POWER_LEVEL
	}
}
