package interfaceGraphique;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.CentralController;

public class FenetreNouvelleCategorie extends JFrame implements ActionListener {

	private JTextField txtTaux;
	private JTextField txtNom;
	private JButton btValider;
	private CentralController centralController;

	public FenetreNouvelleCategorie(CentralController centralController)
	{
		
		this.centralController = centralController;
		setTitle("Creation Categorie");
		setBounds(500, 500, 200, 210);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom categorie");
		JLabel labTaux = new JLabel("Taux TVA");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labTaux);
		txtTaux = new JTextField(15);
		contentPane.add(txtTaux);

		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		try
		{			
			this.centralController.ajouter_categorie( this.txtNom.getText(), Integer.parseInt(this.txtTaux.getText()));
			this.dispose();
		}
		catch(NumberFormatException e1)
		{
			JOptionPane.showMessageDialog(null,"Veuillez rentrer un format de donée valide","erreur",JOptionPane.ERROR_MESSAGE);
		}
	}

}