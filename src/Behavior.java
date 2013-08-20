import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;


public class Behavior extends JPanel {

	/**
	 * Create the panel.
	 */
	public Behavior() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSequential = new JLabel("Automata");
		GridBagConstraints gbc_lblSequential = new GridBagConstraints();
		gbc_lblSequential.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_lblSequential.insets = new Insets(0, 0, 5, 5);
		gbc_lblSequential.gridx = 0;
		gbc_lblSequential.gridy = 1;
		add(lblSequential, gbc_lblSequential);
		
		JLabel lblStart = new JLabel("State");
		GridBagConstraints gbc_lblStart = new GridBagConstraints();
		gbc_lblStart.anchor = GridBagConstraints.NORTH;
		gbc_lblStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblStart.gridx = 1;
		gbc_lblStart.gridy = 2;
		add(lblStart, gbc_lblStart);
		
		JLabel lblSequence = new JLabel("Transition");
		GridBagConstraints gbc_lblSequence = new GridBagConstraints();
		gbc_lblSequence.anchor = GridBagConstraints.EAST;
		gbc_lblSequence.insets = new Insets(0, 0, 5, 5);
		gbc_lblSequence.gridx = 1;
		gbc_lblSequence.gridy = 3;
		add(lblSequence, gbc_lblSequence);
		
		JLabel lblGuard = new JLabel("Guard");
		GridBagConstraints gbc_lblGuard = new GridBagConstraints();
		gbc_lblGuard.insets = new Insets(0, 0, 5, 5);
		gbc_lblGuard.gridx = 1;
		gbc_lblGuard.gridy = 4;
		add(lblGuard, gbc_lblGuard);
		
		JLabel lblAction = new JLabel("Action");
		GridBagConstraints gbc_lblAction = new GridBagConstraints();
		gbc_lblAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAction.gridx = 1;
		gbc_lblAction.gridy = 5;
		add(lblAction, gbc_lblAction);
		
		JLabel lblEvent = new JLabel("Event");
		GridBagConstraints gbc_lblEvent = new GridBagConstraints();
		gbc_lblEvent.insets = new Insets(0, 0, 5, 5);
		gbc_lblEvent.gridx = 0;
		gbc_lblEvent.gridy = 6;
		add(lblEvent, gbc_lblEvent);
		
		JLabel lblEventName = new JLabel("Event name");
		GridBagConstraints gbc_lblEventName = new GridBagConstraints();
		gbc_lblEventName.insets = new Insets(0, 0, 5, 5);
		gbc_lblEventName.gridx = 1;
		gbc_lblEventName.gridy = 7;
		add(lblEventName, gbc_lblEventName);
		
		JLabel lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 1;
		gbc_lblType.gridy = 8;
		add(lblType, gbc_lblType);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("controllable");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 2;
		gbc_rdbtnNewRadioButton.gridy = 8;
		add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("uncontrollable");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_1.gridx = 3;
		gbc_rdbtnNewRadioButton_1.gridy = 8;
		add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JLabel lblStates = new JLabel("States");
		GridBagConstraints gbc_lblStates = new GridBagConstraints();
		gbc_lblStates.insets = new Insets(0, 0, 0, 5);
		gbc_lblStates.gridx = 0;
		gbc_lblStates.gridy = 9;
		add(lblStates, gbc_lblStates);

	}

}
