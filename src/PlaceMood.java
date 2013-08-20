import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;


public class PlaceMood {

	private JFrame frmMood;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaceMood window = new PlaceMood();
					window.frmMood.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlaceMood() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMood = new JFrame();
		frmMood.setTitle("Mood");
		frmMood.getContentPane().setBackground(Color.GRAY);
		frmMood.setForeground(Color.GRAY);
		frmMood.getContentPane().setForeground(Color.GRAY);
		frmMood.setBounds(100, 100, 450, 457);
		frmMood.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frmMood.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblMoodDescription = new JLabel("Mood");
		lblMoodDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMoodDescription.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblMoodDescription = new GridBagConstraints();
		gbc_lblMoodDescription.anchor = GridBagConstraints.WEST;
		gbc_lblMoodDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblMoodDescription.gridx = 1;
		gbc_lblMoodDescription.gridy = 0;
		frmMood.getContentPane().add(lblMoodDescription, gbc_lblMoodDescription);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 1;
		frmMood.getContentPane().add(lblDescription, gbc_lblDescription);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 1;
		frmMood.getContentPane().add(textArea, gbc_textArea);
		
		JLabel lblLight = new JLabel("Light");
		lblLight.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLight = new GridBagConstraints();
		gbc_lblLight.anchor = GridBagConstraints.WEST;
		gbc_lblLight.insets = new Insets(0, 0, 5, 5);
		gbc_lblLight.gridx = 1;
		gbc_lblLight.gridy = 2;
		frmMood.getContentPane().add(lblLight, gbc_lblLight);
		
		JTextArea textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 2;
		gbc_textArea_1.gridy = 2;
		frmMood.getContentPane().add(textArea_1, gbc_textArea_1);
		
		JButton btnNewLight = new JButton("Add Light");
		GridBagConstraints gbc_btnNewLight = new GridBagConstraints();
		gbc_btnNewLight.anchor = GridBagConstraints.WEST;
		gbc_btnNewLight.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewLight.gridx = 2;
		gbc_btnNewLight.gridy = 3;
		frmMood.getContentPane().add(btnNewLight, gbc_btnNewLight);
		
		JLabel lblSound = new JLabel("Sound");
		lblSound.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblSound = new GridBagConstraints();
		gbc_lblSound.anchor = GridBagConstraints.WEST;
		gbc_lblSound.insets = new Insets(0, 0, 5, 5);
		gbc_lblSound.gridx = 1;
		gbc_lblSound.gridy = 4;
		frmMood.getContentPane().add(lblSound, gbc_lblSound);
		
		JTextArea textArea_2 = new JTextArea();
		GridBagConstraints gbc_textArea_2 = new GridBagConstraints();
		gbc_textArea_2.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_2.fill = GridBagConstraints.BOTH;
		gbc_textArea_2.gridx = 2;
		gbc_textArea_2.gridy = 4;
		frmMood.getContentPane().add(textArea_2, gbc_textArea_2);
		
		JButton btnAddSound = new JButton("Add Sound");
		GridBagConstraints gbc_btnAddSound = new GridBagConstraints();
		gbc_btnAddSound.anchor = GridBagConstraints.WEST;
		gbc_btnAddSound.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddSound.gridx = 2;
		gbc_btnAddSound.gridy = 5;
		frmMood.getContentPane().add(btnAddSound, gbc_btnAddSound);
		
		JLabel lblProjection = new JLabel("Projection");
		lblProjection.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblProjection = new GridBagConstraints();
		gbc_lblProjection.anchor = GridBagConstraints.WEST;
		gbc_lblProjection.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjection.gridx = 1;
		gbc_lblProjection.gridy = 6;
		frmMood.getContentPane().add(lblProjection, gbc_lblProjection);
		
		JTextArea textArea_3 = new JTextArea();
		GridBagConstraints gbc_textArea_3 = new GridBagConstraints();
		gbc_textArea_3.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_3.fill = GridBagConstraints.BOTH;
		gbc_textArea_3.gridx = 2;
		gbc_textArea_3.gridy = 6;
		frmMood.getContentPane().add(textArea_3, gbc_textArea_3);
		
		JLabel lblSpecialEffects = new JLabel("Special effects");
		lblSpecialEffects.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblSpecialEffects = new GridBagConstraints();
		gbc_lblSpecialEffects.anchor = GridBagConstraints.WEST;
		gbc_lblSpecialEffects.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpecialEffects.gridx = 1;
		gbc_lblSpecialEffects.gridy = 8;
		frmMood.getContentPane().add(lblSpecialEffects, gbc_lblSpecialEffects);
		
		JTextArea textArea_5 = new JTextArea();
		GridBagConstraints gbc_textArea_5 = new GridBagConstraints();
		gbc_textArea_5.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_5.fill = GridBagConstraints.BOTH;
		gbc_textArea_5.gridx = 2;
		gbc_textArea_5.gridy = 8;
		frmMood.getContentPane().add(textArea_5, gbc_textArea_5);
		
		JButton btnAddSpecialEffect = new JButton("Add Spec.Effect");
		GridBagConstraints gbc_btnAddSpecialEffect = new GridBagConstraints();
		gbc_btnAddSpecialEffect.anchor = GridBagConstraints.WEST;
		gbc_btnAddSpecialEffect.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddSpecialEffect.gridx = 2;
		gbc_btnAddSpecialEffect.gridy = 9;
		frmMood.getContentPane().add(btnAddSpecialEffect, gbc_btnAddSpecialEffect);
		
		JLabel lblOther = new JLabel("Other");
		lblOther.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblOther = new GridBagConstraints();
		gbc_lblOther.anchor = GridBagConstraints.WEST;
		gbc_lblOther.insets = new Insets(0, 0, 5, 5);
		gbc_lblOther.gridx = 1;
		gbc_lblOther.gridy = 10;
		frmMood.getContentPane().add(lblOther, gbc_lblOther);
		
		JTextArea textArea_4 = new JTextArea();
		GridBagConstraints gbc_textArea_4 = new GridBagConstraints();
		gbc_textArea_4.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_4.fill = GridBagConstraints.BOTH;
		gbc_textArea_4.gridx = 2;
		gbc_textArea_4.gridy = 10;
		frmMood.getContentPane().add(textArea_4, gbc_textArea_4);
		
		JButton btnNewButton = new JButton("Save");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 11;
		frmMood.getContentPane().add(btnNewButton, gbc_btnNewButton);
	}

}
