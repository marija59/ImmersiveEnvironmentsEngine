import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;


public class PlaceBehaviorComponents extends JPanel {
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public PlaceBehaviorComponents() {
		setForeground(Color.WHITE);
		setBackground(Color.GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSense = new JLabel("Sense");
		lblSense.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSense.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblSense = new GridBagConstraints();
		gbc_lblSense.anchor = GridBagConstraints.WEST;
		gbc_lblSense.insets = new Insets(0, 0, 5, 5);
		gbc_lblSense.gridx = 1;
		gbc_lblSense.gridy = 0;
		add(lblSense, gbc_lblSense);
		
		JLabel lblSense_1 = new JLabel("Automation");
		lblSense_1.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblSense_1 = new GridBagConstraints();
		gbc_lblSense_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSense_1.gridx = 2;
		gbc_lblSense_1.gridy = 1;
		add(lblSense_1, gbc_lblSense_1);
		
		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblType_1 = new GridBagConstraints();
		gbc_lblType_1.anchor = GridBagConstraints.WEST;
		gbc_lblType_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblType_1.gridx = 3;
		gbc_lblType_1.gridy = 2;
		add(lblType_1, gbc_lblType_1);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Listen", "See", "Detect presense", "Detect interaction", "Detect position", "Other"}));
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 4;
		gbc_comboBox_3.gridy = 2;
		add(comboBox_3, gbc_comboBox_3);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 3;
		gbc_lblName.gridy = 3;
		add(lblName, gbc_lblName);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 3;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDescriptionSensor = new JLabel("Description sense");
		lblDescriptionSensor.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDescriptionSensor = new GridBagConstraints();
		gbc_lblDescriptionSensor.anchor = GridBagConstraints.EAST;
		gbc_lblDescriptionSensor.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionSensor.gridx = 3;
		gbc_lblDescriptionSensor.gridy = 4;
		add(lblDescriptionSensor, gbc_lblDescriptionSensor);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 4;
		gbc_textArea.gridy = 4;
		add(textArea, gbc_textArea);
		
		JButton btnNewButton = new JButton("Add");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 5;
		add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblInformation = new JLabel("Information");
		lblInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInformation.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblInformation = new GridBagConstraints();
		gbc_lblInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformation.gridx = 1;
		gbc_lblInformation.gridy = 10;
		add(lblInformation, gbc_lblInformation);
		
		JLabel lblDataType = new JLabel("Data Type");
		lblDataType.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDataType = new GridBagConstraints();
		gbc_lblDataType.anchor = GridBagConstraints.WEST;
		gbc_lblDataType.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataType.gridx = 3;
		gbc_lblDataType.gridy = 11;
		add(lblDataType, gbc_lblDataType);
		
		JComboBox comboBox_6 = new JComboBox();
		GridBagConstraints gbc_comboBox_6 = new GridBagConstraints();
		gbc_comboBox_6.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_6.gridx = 4;
		gbc_comboBox_6.gridy = 11;
		add(comboBox_6, gbc_comboBox_6);
		
		JLabel lblAct = new JLabel("Act");
		lblAct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAct.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblAct = new GridBagConstraints();
		gbc_lblAct.anchor = GridBagConstraints.WEST;
		gbc_lblAct.insets = new Insets(0, 0, 5, 5);
		gbc_lblAct.gridx = 1;
		gbc_lblAct.gridy = 13;
		add(lblAct, gbc_lblAct);
		
		JLabel lblAutomation = new JLabel("Automation");
		lblAutomation.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblAutomation = new GridBagConstraints();
		gbc_lblAutomation.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutomation.gridx = 2;
		gbc_lblAutomation.gridy = 14;
		add(lblAutomation, gbc_lblAutomation);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.WEST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 3;
		gbc_lblType.gridy = 15;
		add(lblType, gbc_lblType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Light", "Sound(ambient, interactive, talk)", "Animation", "Movement"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 15;
		add(comboBox, gbc_comboBox);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblName_1 = new GridBagConstraints();
		gbc_lblName_1.anchor = GridBagConstraints.WEST;
		gbc_lblName_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblName_1.gridx = 3;
		gbc_lblName_1.gridy = 16;
		add(lblName_1, gbc_lblName_1);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 16;
		add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JTextArea textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 4;
		gbc_textArea_1.gridy = 17;
		add(textArea_1, gbc_textArea_1);
		
		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.WEST;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 5;
		gbc_btnAdd.gridy = 17;
		add(btnAdd, gbc_btnAdd);

	}

}
