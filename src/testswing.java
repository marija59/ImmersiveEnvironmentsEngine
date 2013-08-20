import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;


public class testswing {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testswing window = new testswing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testswing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 10};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Dimensions");
		lblNewLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Size");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblLength = new JLabel("Length");
		GridBagConstraints gbc_lblLength = new GridBagConstraints();
		gbc_lblLength.insets = new Insets(0, 0, 5, 0);
		gbc_lblLength.gridx = 1;
		gbc_lblLength.gridy = 2;
		frame.getContentPane().add(lblLength, gbc_lblLength);
		
		JLabel lblWidth = new JLabel("Width");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.insets = new Insets(0, 0, 5, 0);
		gbc_lblWidth.gridx = 1;
		gbc_lblWidth.gridy = 3;
		frame.getContentPane().add(lblWidth, gbc_lblWidth);
		
		JLabel lblHeight = new JLabel("Height");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 5, 0);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 4;
		frame.getContentPane().add(lblHeight, gbc_lblHeight);
		
		JLabel lblShape = new JLabel("Shape");
		GridBagConstraints gbc_lblShape = new GridBagConstraints();
		gbc_lblShape.anchor = GridBagConstraints.EAST;
		gbc_lblShape.insets = new Insets(0, 0, 5, 5);
		gbc_lblShape.gridx = 0;
		gbc_lblShape.gridy = 5;
		frame.getContentPane().add(lblShape, gbc_lblShape);
		
		JLabel lblVisualize = new JLabel("Visualize");
		GridBagConstraints gbc_lblVisualize = new GridBagConstraints();
		gbc_lblVisualize.anchor = GridBagConstraints.EAST;
		gbc_lblVisualize.insets = new Insets(0, 0, 5, 5);
		gbc_lblVisualize.gridx = 0;
		gbc_lblVisualize.gridy = 6;
		frame.getContentPane().add(lblVisualize, gbc_lblVisualize);
		
		JLabel lblDescribeLocation = new JLabel("Describe location");
		GridBagConstraints gbc_lblDescribeLocation = new GridBagConstraints();
		gbc_lblDescribeLocation.anchor = GridBagConstraints.EAST;
		gbc_lblDescribeLocation.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescribeLocation.gridx = 0;
		gbc_lblDescribeLocation.gridy = 7;
		frame.getContentPane().add(lblDescribeLocation, gbc_lblDescribeLocation);
	}

}
