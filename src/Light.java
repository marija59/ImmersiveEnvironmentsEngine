import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;


public class Light {

	private JFrame frmLight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Light window = new Light();
					window.frmLight.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Light() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLight = new JFrame();
		frmLight.getContentPane().setBackground(Color.GRAY);
		frmLight.setTitle("Light");
		frmLight.setBounds(100, 100, 582, 402);
		frmLight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frmLight.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblLight = new JLabel("Light");
		lblLight.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLight.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLight = new GridBagConstraints();
		gbc_lblLight.anchor = GridBagConstraints.WEST;
		gbc_lblLight.insets = new Insets(0, 0, 5, 5);
		gbc_lblLight.gridx = 0;
		gbc_lblLight.gridy = 0;
		frmLight.getContentPane().add(lblLight, gbc_lblLight);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		frmLight.getContentPane().add(lblDescription, gbc_lblDescription);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 1;
		frmLight.getContentPane().add(textArea, gbc_textArea);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.WEST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 2;
		frmLight.getContentPane().add(lblType, gbc_lblType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "ambient", "interactive", "spot"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 2;
		frmLight.getContentPane().add(comboBox, gbc_comboBox);
		
		JLabel lblPosition = new JLabel("Components");
		lblPosition.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPosition = new GridBagConstraints();
		gbc_lblPosition.anchor = GridBagConstraints.WEST;
		gbc_lblPosition.insets = new Insets(0, 0, 5, 5);
		gbc_lblPosition.gridx = 0;
		gbc_lblPosition.gridy = 3;
		frmLight.getContentPane().add(lblPosition, gbc_lblPosition);
		
		JButton btnNewComponent = new JButton("New Component");
		GridBagConstraints gbc_btnNewComponent = new GridBagConstraints();
		gbc_btnNewComponent.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewComponent.gridx = 2;
		gbc_btnNewComponent.gridy = 4;
		frmLight.getContentPane().add(btnNewComponent, gbc_btnNewComponent);
		
		JTextPane txtpnComponents = new JTextPane();
		txtpnComponents.setText("Components:");
		GridBagConstraints gbc_txtpnComponents = new GridBagConstraints();
		gbc_txtpnComponents.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnComponents.fill = GridBagConstraints.BOTH;
		gbc_txtpnComponents.gridx = 2;
		gbc_txtpnComponents.gridy = 5;
		frmLight.getContentPane().add(txtpnComponents, gbc_txtpnComponents);
		
		JLabel lblBehavior = new JLabel("Behavior");
		lblBehavior.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblBehavior = new GridBagConstraints();
		gbc_lblBehavior.anchor = GridBagConstraints.WEST;
		gbc_lblBehavior.insets = new Insets(0, 0, 0, 5);
		gbc_lblBehavior.gridx = 0;
		gbc_lblBehavior.gridy = 7;
		frmLight.getContentPane().add(lblBehavior, gbc_lblBehavior);
		
		JButton btnNewBehavior = new JButton("New behavior");
		GridBagConstraints gbc_btnNewBehavior = new GridBagConstraints();
		gbc_btnNewBehavior.gridx = 2;
		gbc_btnNewBehavior.gridy = 7;
		frmLight.getContentPane().add(btnNewBehavior, gbc_btnNewBehavior);
	}
}
