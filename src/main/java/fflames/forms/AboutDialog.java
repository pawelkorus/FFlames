package fflames.forms;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class AboutDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	public AboutDialog() {
		setResizable(false);
		setMinimumSize(new Dimension(450, 200));
		setPreferredSize(new Dimension(450, 200));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("About");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{121, 152, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(gridBagLayout);
		getContentPane().add(panel);
		
		JLabel lblFflames = new JLabel("FFlames");
		lblFflames.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lblFflames = new GridBagConstraints();
		gbc_lblFflames.gridwidth = 2;
		gbc_lblFflames.insets = new Insets(0, 0, 5, 0);
		gbc_lblFflames.gridx = 0;
		gbc_lblFflames.gridy = 0;
		panel.add(lblFflames, gbc_lblFflames);
		
		JLabel lblVersion = new JLabel("Version");
		lblVersion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblVersion = new GridBagConstraints();
		gbc_lblVersion.anchor = GridBagConstraints.WEST;
		gbc_lblVersion.insets = new Insets(0, 0, 5, 5);
		gbc_lblVersion.gridx = 0;
		gbc_lblVersion.gridy = 1;
		panel.add(lblVersion, gbc_lblVersion);
		
		JLabel label = new JLabel("2.0");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.WEST;
		gbc_lblAuthor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthor.gridx = 0;
		gbc_lblAuthor.gridy = 2;
		panel.add(lblAuthor, gbc_lblAuthor);
		
		JLabel lblAuthorName = new JLabel("Paweł Korus pawelkorus@ymail.com");
		lblAuthorName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblAuthorName = new GridBagConstraints();
		gbc_lblAuthorName.insets = new Insets(0, 0, 5, 0);
		gbc_lblAuthorName.anchor = GridBagConstraints.WEST;
		gbc_lblAuthorName.gridx = 1;
		gbc_lblAuthorName.gridy = 2;
		panel.add(lblAuthorName, gbc_lblAuthorName);
		
		JLabel lblProgramInfo = new JLabel("Program for generating fractal flames");
		GridBagConstraints gbc_lblProgramInfo = new GridBagConstraints();
		gbc_lblProgramInfo.insets = new Insets(0, 0, 5, 0);
		gbc_lblProgramInfo.fill = GridBagConstraints.VERTICAL;
		gbc_lblProgramInfo.weighty = 3.0;
		gbc_lblProgramInfo.anchor = GridBagConstraints.WEST;
		gbc_lblProgramInfo.gridwidth = 2;
		gbc_lblProgramInfo.gridx = 0;
		gbc_lblProgramInfo.gridy = 3;
		panel.add(lblProgramInfo, gbc_lblProgramInfo);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(this);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		panel.add(btnNewButton, gbc_btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
