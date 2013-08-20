import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;


public class PlaceDescription extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public PlaceDescription() {
		setBackground(Color.GRAY);
		setForeground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{-5, 0, 0, 0, 0, 0, 0, 0, 0, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 0);
		gbc_lblDescription.gridx = 3;
		gbc_lblDescription.gridy = 0;
		add(lblDescription, gbc_lblDescription);
		
		JLabel lblDimensions = new JLabel("Dimensions");
		lblDimensions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDimensions.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDimensions = new GridBagConstraints();
		gbc_lblDimensions.anchor = GridBagConstraints.EAST;
		gbc_lblDimensions.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimensions.gridx = 1;
		gbc_lblDimensions.gridy = 1;
		add(lblDimensions, gbc_lblDimensions);
		
		JLabel label = new JLabel("Size");
		label.setForeground(Color.WHITE);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		add(label, gbc_label);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLength = new GridBagConstraints();
		gbc_lblLength.anchor = GridBagConstraints.EAST;
		gbc_lblLength.insets = new Insets(0, 0, 5, 5);
		gbc_lblLength.gridx = 2;
		gbc_lblLength.gridy = 2;
		add(lblLength, gbc_lblLength);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.EAST;
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 2;
		gbc_lblWidth.gridy = 3;
		add(lblWidth, gbc_lblWidth);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 3;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 2;
		gbc_lblHeight.gridy = 4;
		add(lblHeight, gbc_lblHeight);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 4;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblShape = new JLabel("Shape");
		lblShape.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblShape = new GridBagConstraints();
		gbc_lblShape.anchor = GridBagConstraints.EAST;
		gbc_lblShape.insets = new Insets(0, 0, 5, 5);
		gbc_lblShape.gridx = 1;
		gbc_lblShape.gridy = 5;
		add(lblShape, gbc_lblShape);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 5;
		add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblVizualization = new JLabel("Vizualization");
		lblVizualization.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblVizualization = new GridBagConstraints();
		gbc_lblVizualization.anchor = GridBagConstraints.EAST;
		gbc_lblVizualization.insets = new Insets(0, 0, 5, 5);
		gbc_lblVizualization.gridx = 1;
		gbc_lblVizualization.gridy = 6;
		add(lblVizualization, gbc_lblVizualization);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 6;
		add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.anchor = GridBagConstraints.EAST;
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 1;
		gbc_lblLocation.gridy = 7;
		add(lblLocation, gbc_lblLocation);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.anchor = GridBagConstraints.NORTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 7;
		add(textArea, gbc_textArea);
		
		JLabel lblColors = new JLabel("Colors");
		lblColors.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblColors = new GridBagConstraints();
		gbc_lblColors.anchor = GridBagConstraints.EAST;
		gbc_lblColors.insets = new Insets(0, 0, 5, 5);
		gbc_lblColors.gridx = 1;
		gbc_lblColors.gridy = 8;
		add(lblColors, gbc_lblColors);
		
		JTextArea textArea_2 = new JTextArea();
		GridBagConstraints gbc_textArea_2 = new GridBagConstraints();
		gbc_textArea_2.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_2.fill = GridBagConstraints.BOTH;
		gbc_textArea_2.gridx = 3;
		gbc_textArea_2.gridy = 8;
		add(textArea_2, gbc_textArea_2);
		
		JLabel lblLandscaping = new JLabel("Landscaping");
		lblLandscaping.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLandscaping = new GridBagConstraints();
		gbc_lblLandscaping.anchor = GridBagConstraints.EAST;
		gbc_lblLandscaping.insets = new Insets(0, 0, 5, 5);
		gbc_lblLandscaping.gridx = 1;
		gbc_lblLandscaping.gridy = 9;
		add(lblLandscaping, gbc_lblLandscaping);
		
		JTextArea textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 3;
		gbc_textArea_1.gridy = 9;
		add(textArea_1, gbc_textArea_1);
		
		JLabel lblConstrains = new JLabel("Constrains");
		lblConstrains.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConstrains.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConstrains = new GridBagConstraints();
		gbc_lblConstrains.insets = new Insets(0, 0, 5, 5);
		gbc_lblConstrains.gridx = 1;
		gbc_lblConstrains.gridy = 10;
		add(lblConstrains, gbc_lblConstrains);
		
		JLabel lblSafety = new JLabel("Safety");
		lblSafety.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblSafety = new GridBagConstraints();
		gbc_lblSafety.anchor = GridBagConstraints.EAST;
		gbc_lblSafety.insets = new Insets(0, 0, 5, 5);
		gbc_lblSafety.gridx = 1;
		gbc_lblSafety.gridy = 11;
		add(lblSafety, gbc_lblSafety);
		
		JTextArea textArea_3 = new JTextArea();
		GridBagConstraints gbc_textArea_3 = new GridBagConstraints();
		gbc_textArea_3.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_3.fill = GridBagConstraints.BOTH;
		gbc_textArea_3.gridx = 3;
		gbc_textArea_3.gridy = 11;
		add(textArea_3, gbc_textArea_3);
		
		JLabel lblFire = new JLabel("Fire");
		lblFire.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblFire = new GridBagConstraints();
		gbc_lblFire.anchor = GridBagConstraints.EAST;
		gbc_lblFire.insets = new Insets(0, 0, 5, 5);
		gbc_lblFire.gridx = 1;
		gbc_lblFire.gridy = 12;
		add(lblFire, gbc_lblFire);
		
		JTextArea textArea_4 = new JTextArea();
		GridBagConstraints gbc_textArea_4 = new GridBagConstraints();
		gbc_textArea_4.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_4.fill = GridBagConstraints.BOTH;
		gbc_textArea_4.gridx = 3;
		gbc_textArea_4.gridy = 12;
		add(textArea_4, gbc_textArea_4);
		
		JLabel lblEmergencyLight = new JLabel("Emergency light");
		lblEmergencyLight.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEmergencyLight = new GridBagConstraints();
		gbc_lblEmergencyLight.anchor = GridBagConstraints.EAST;
		gbc_lblEmergencyLight.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmergencyLight.gridx = 1;
		gbc_lblEmergencyLight.gridy = 13;
		add(lblEmergencyLight, gbc_lblEmergencyLight);
		
		JTextArea textArea_5 = new JTextArea();
		GridBagConstraints gbc_textArea_5 = new GridBagConstraints();
		gbc_textArea_5.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_5.fill = GridBagConstraints.BOTH;
		gbc_textArea_5.gridx = 3;
		gbc_textArea_5.gridy = 13;
		add(textArea_5, gbc_textArea_5);
		
		JLabel lblEmergencyExit = new JLabel("Emergency exit");
		lblEmergencyExit.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblEmergencyExit = new GridBagConstraints();
		gbc_lblEmergencyExit.anchor = GridBagConstraints.EAST;
		gbc_lblEmergencyExit.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmergencyExit.gridx = 1;
		gbc_lblEmergencyExit.gridy = 14;
		add(lblEmergencyExit, gbc_lblEmergencyExit);
		
		JTextArea textArea_6 = new JTextArea();
		GridBagConstraints gbc_textArea_6 = new GridBagConstraints();
		gbc_textArea_6.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_6.fill = GridBagConstraints.BOTH;
		gbc_textArea_6.gridx = 3;
		gbc_textArea_6.gridy = 14;
		add(textArea_6, gbc_textArea_6);
		
		JLabel lblScenery = new JLabel("Scenery");
		lblScenery.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblScenery.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblScenery = new GridBagConstraints();
		gbc_lblScenery.anchor = GridBagConstraints.EAST;
		gbc_lblScenery.insets = new Insets(0, 0, 5, 5);
		gbc_lblScenery.gridx = 1;
		gbc_lblScenery.gridy = 15;
		add(lblScenery, gbc_lblScenery);
		
		JLabel lblScenery_1 = new JLabel("Scenery");
		lblScenery_1.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblScenery_1 = new GridBagConstraints();
		gbc_lblScenery_1.anchor = GridBagConstraints.EAST;
		gbc_lblScenery_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblScenery_1.gridx = 1;
		gbc_lblScenery_1.gridy = 16;
		add(lblScenery_1, gbc_lblScenery_1);
		
		JTextArea textArea_7 = new JTextArea();
		GridBagConstraints gbc_textArea_7 = new GridBagConstraints();
		gbc_textArea_7.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_7.fill = GridBagConstraints.BOTH;
		gbc_textArea_7.gridx = 3;
		gbc_textArea_7.gridy = 16;
		add(textArea_7, gbc_textArea_7);
		
		JLabel lblMaterials = new JLabel("Materials");
		lblMaterials.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblMaterials = new GridBagConstraints();
		gbc_lblMaterials.anchor = GridBagConstraints.EAST;
		gbc_lblMaterials.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaterials.gridx = 1;
		gbc_lblMaterials.gridy = 17;
		add(lblMaterials, gbc_lblMaterials);
		
		JTextArea textArea_8 = new JTextArea();
		GridBagConstraints gbc_textArea_8 = new GridBagConstraints();
		gbc_textArea_8.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_8.fill = GridBagConstraints.BOTH;
		gbc_textArea_8.gridx = 3;
		gbc_textArea_8.gridy = 17;
		add(textArea_8, gbc_textArea_8);
		
		JLabel lblStaticObjects = new JLabel("Static objects");
		lblStaticObjects.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStaticObjects = new GridBagConstraints();
		gbc_lblStaticObjects.anchor = GridBagConstraints.EAST;
		gbc_lblStaticObjects.insets = new Insets(0, 0, 5, 5);
		gbc_lblStaticObjects.gridx = 1;
		gbc_lblStaticObjects.gridy = 18;
		add(lblStaticObjects, gbc_lblStaticObjects);
		
		JTextArea textArea_9 = new JTextArea();
		GridBagConstraints gbc_textArea_9 = new GridBagConstraints();
		gbc_textArea_9.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_9.fill = GridBagConstraints.BOTH;
		gbc_textArea_9.gridx = 3;
		gbc_textArea_9.gridy = 18;
		add(textArea_9, gbc_textArea_9);

	}

}
