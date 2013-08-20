import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;


public class Component extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Component() {
		setBackground(Color.GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblComponent = new JLabel("Component");
		lblComponent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblComponent.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblComponent = new GridBagConstraints();
		gbc_lblComponent.insets = new Insets(0, 0, 5, 5);
		gbc_lblComponent.gridx = 1;
		gbc_lblComponent.gridy = 0;
		add(lblComponent, gbc_lblComponent);
		
		JLabel lblStates = new JLabel("States");
		lblStates.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStates = new GridBagConstraints();
		gbc_lblStates.anchor = GridBagConstraints.WEST;
		gbc_lblStates.insets = new Insets(0, 0, 5, 5);
		gbc_lblStates.gridx = 1;
		gbc_lblStates.gridy = 1;
		add(lblStates, gbc_lblStates);
		
		JCheckBox chckbxInitial = new JCheckBox("initial");
		chckbxInitial.setBackground(Color.GRAY);
		chckbxInitial.setForeground(Color.WHITE);
		GridBagConstraints gbc_chckbxInitial = new GridBagConstraints();
		gbc_chckbxInitial.anchor = GridBagConstraints.WEST;
		gbc_chckbxInitial.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxInitial.gridx = 2;
		gbc_chckbxInitial.gridy = 2;
		add(chckbxInitial, gbc_chckbxInitial);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 3;
		add(btnAdd, gbc_btnAdd);
		
		JLabel lblTransitions = new JLabel("Transitions");
		lblTransitions.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTransitions = new GridBagConstraints();
		gbc_lblTransitions.anchor = GridBagConstraints.WEST;
		gbc_lblTransitions.insets = new Insets(0, 0, 5, 5);
		gbc_lblTransitions.gridx = 1;
		gbc_lblTransitions.gridy = 5;
		add(lblTransitions, gbc_lblTransitions);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 6;
		add(lblName, gbc_lblName);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 6;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblStateFrom = new JLabel("State from");
		lblStateFrom.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStateFrom = new GridBagConstraints();
		gbc_lblStateFrom.anchor = GridBagConstraints.WEST;
		gbc_lblStateFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblStateFrom.gridx = 2;
		gbc_lblStateFrom.gridy = 7;
		add(lblStateFrom, gbc_lblStateFrom);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 7;
		add(comboBox, gbc_comboBox);
		
		JLabel lblStateTo = new JLabel("State to");
		lblStateTo.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStateTo = new GridBagConstraints();
		gbc_lblStateTo.anchor = GridBagConstraints.WEST;
		gbc_lblStateTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblStateTo.gridx = 2;
		gbc_lblStateTo.gridy = 8;
		add(lblStateTo, gbc_lblStateTo);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 3;
		gbc_comboBox_1.gridy = 8;
		add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblGuard = new JLabel("Condition");
		lblGuard.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblGuard = new GridBagConstraints();
		gbc_lblGuard.anchor = GridBagConstraints.WEST;
		gbc_lblGuard.insets = new Insets(0, 0, 5, 5);
		gbc_lblGuard.gridx = 2;
		gbc_lblGuard.gridy = 9;
		add(lblGuard, gbc_lblGuard);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 9;
		add(textArea, gbc_textArea);
		
		JLabel lblAction = new JLabel("Action");
		lblAction.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblAction = new GridBagConstraints();
		gbc_lblAction.anchor = GridBagConstraints.WEST;
		gbc_lblAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAction.gridx = 2;
		gbc_lblAction.gridy = 10;
		add(lblAction, gbc_lblAction);
		
		JTextArea textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 3;
		gbc_textArea_1.gridy = 10;
		add(textArea_1, gbc_textArea_1);
		
		JLabel lblEvent = new JLabel("Event");
		lblEvent.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEvent = new GridBagConstraints();
		gbc_lblEvent.anchor = GridBagConstraints.WEST;
		gbc_lblEvent.insets = new Insets(0, 0, 5, 5);
		gbc_lblEvent.gridx = 2;
		gbc_lblEvent.gridy = 11;
		add(lblEvent, gbc_lblEvent);
		
		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 3;
		gbc_comboBox_2.gridy = 11;
		add(comboBox_2, gbc_comboBox_2);
		
		JButton btnAdd_1 = new JButton("Add");
		GridBagConstraints gbc_btnAdd_1 = new GridBagConstraints();
		gbc_btnAdd_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd_1.gridx = 2;
		gbc_btnAdd_1.gridy = 12;
		add(btnAdd_1, gbc_btnAdd_1);

	}

}
