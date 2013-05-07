package com.rs.miner.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.rs.miner.Main;
import com.rs.miner.Main.Location;
import com.rs.miner.Main.Mode;
import com.rs.miner.Main.OreType;
import com.rs.miner.Main.State;

public class GUI extends JFrame {

  private static final long serialVersionUID = 1L;

	public GUI() {
		initComponents();
	}

	private void rdPowerActionPerformed(ActionEvent e) {
		cboLocation.setEnabled(false);
	}

	private void rdBankActionPerformed(ActionEvent e) {
		cboLocation.setEnabled(true);
	}

	private void btnStartActionPerformed(ActionEvent e) {
		String oreChosen = cboOre.getSelectedItem().toString();
		String locationChosen = cboLocation.getSelectedItem().toString();

		if (oreChosen.equals("CLAY")) {
			Main.ore = OreType.CLAY;
			Main.oreID = 434;
		} else if (oreChosen.equals("TIN")) {
			Main.ore = OreType.NULL;
			Main.oreID = 438;
		} else if (oreChosen.equals("COPPER")) {
			Main.ore = OreType.COPPER;
			Main.oreID = 436;
		} else if (oreChosen.equals("SILVER")) {
			Main.ore = OreType.SILVER;
			Main.oreID = 442;
		} else if (oreChosen.equals("GOLD")) {
			Main.ore = OreType.GOLD;
			Main.oreID = 444;
		} else if (oreChosen.equals("IRON")) {
			Main.ore = OreType.IRON;
			Main.oreID = 440;
		} else if (oreChosen.equals("COAL")) {
			Main.ore = OreType.COAL;
			Main.oreID = 453;
		} else if (oreChosen.equals("MITHRIL")) {
			Main.ore = OreType.MITHRIL;
			Main.oreID = 447;
		}

		if (locationChosen.equals("GUILD")) {
			Main.location = Location.GUILD;
		} else if (locationChosen.equals("RIMMINGTON")) {
			Main.location = Location.RIMMINGTON;
		} else if (locationChosen.equals("VARROCK_EAST")) {
			Main.location = Location.VARROCK_EAST;
		} else if (locationChosen.equals("VARROCK_WEST")) {
			Main.location = Location.VARROCK_WEST;
		}

		if (rdPower.isSelected()) {
			Main.mode = Mode.POWER_LEVEL;
		} else if (rdBank.isSelected()) {
			Main.mode = Mode.BANKING;
		}

		Main.state = State.IDLE;
		this.setVisible(false);
	}

	private void initComponents() {
		label1 = new JLabel();
		cboOre = new JComboBox<>();
		rdPower = new JRadioButton();
		rdBank = new JRadioButton();
		cboLocation = new JComboBox<>();
		btnStart = new JButton();
		lblLocation = new JLabel();

		// ======== this ========
		setTitle("RSMiner");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		// ---- label1 ----
		label1.setText("Select Ore");
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setFont(label1.getFont().deriveFont(
				label1.getFont().getStyle() | Font.BOLD));
		contentPane.add(label1);
		label1.setBounds(10, 35, 75, label1.getPreferredSize().height);

		// ---- cboOre ----
		cboOre.setModel(new DefaultComboBoxModel<>(new String[] { "NULL",
				"CLAY", "TIN", "COPPER", "SILVER", "GOLD", "IRON", "COAL",
				"MITHRIL" }));
		contentPane.add(cboOre);
		cboOre.setBounds(10, 50, 84, cboOre.getPreferredSize().height);

		// ---- rdPower ----
		rdPower.setText("Powerlevel");
		rdPower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdPowerActionPerformed(e);
			}
		});
		contentPane.add(rdPower);
		rdPower.setBounds(new Rectangle(new Point(5, 5), rdPower
				.getPreferredSize()));

		// ---- rdBank ----
		rdBank.setText("Bank Mode");
		rdBank.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdBankActionPerformed(e);
			}
		});
		contentPane.add(rdBank);
		rdBank.setBounds(new Rectangle(new Point(110, 5), rdBank
				.getPreferredSize()));

		// ---- cboLocation ----
		cboLocation.setModel(new DefaultComboBoxModel<>(new String[] { "NULL",
				"GUILD", "RIMMINGTON", "VARROCK_EAST", "VARROCK_WEST" }));
		contentPane.add(cboLocation);
		cboLocation.setEnabled(false);
		cboLocation.setBounds(100, 50, 90,
				cboLocation.getPreferredSize().height);

		// ---- btnStart ----
		btnStart.setText("Automate");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStartActionPerformed(e);
			}
		});
		contentPane.add(btnStart);
		btnStart.setBounds(new Rectangle(new Point(55, 80), btnStart
				.getPreferredSize()));

		// ---- lblLocation ----
		lblLocation.setText("Select Location");
		lblLocation.setFont(lblLocation.getFont().deriveFont(
				lblLocation.getFont().getStyle() | Font.BOLD));
		contentPane.add(lblLocation);
		lblLocation.setBounds(new Rectangle(new Point(100, 35), lblLocation
				.getPreferredSize()));

		contentPane.setPreferredSize(new Dimension(215, 150));
		setSize(215, 150);
		setLocationRelativeTo(getOwner());

		// ---- powerbank ----
		ButtonGroup powerbank = new ButtonGroup();
		powerbank.add(rdPower);
		powerbank.add(rdBank);
	}

	private JLabel label1;
	private JComboBox<String> cboOre;
	private JRadioButton rdPower;
	private JRadioButton rdBank;
	private JComboBox<String> cboLocation;
	private JButton btnStart;
	private JLabel lblLocation;
}
