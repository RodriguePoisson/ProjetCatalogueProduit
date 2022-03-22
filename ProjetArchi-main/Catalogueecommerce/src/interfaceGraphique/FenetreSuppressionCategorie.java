package interfaceGraphique;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Exception.NotVideException;
import controller.CentralController;

public class FenetreSuppressionCategorie extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	private CentralController centralController;
	private String[] lesCategories;
	
	public FenetreSuppressionCategorie(String lesCategories[],CentralController centralController) {
		
		this.lesCategories = lesCategories;
		this.centralController = centralController;
		setTitle("Suppression categorie");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(lesCategories);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Categorie"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		try {
			this.centralController.supprimerCategorie(this.combo.getSelectedItem().toString());
			
		} catch (NotVideException e1) 
		{
			JOptionPane.showMessageDialog(null,e1.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
		}
		this.dispose();
	}

}
