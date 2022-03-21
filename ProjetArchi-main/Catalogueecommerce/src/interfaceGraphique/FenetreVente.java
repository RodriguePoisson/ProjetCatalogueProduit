package interfaceGraphique;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Exception.QuantiteException;
import controller.CentralController;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private CentralController centralController;

	public FenetreVente(String[] lesProduits, CentralController centralController) {
		this.centralController = centralController;
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		try 
		{
			this.centralController.vendreProduit(combo.getSelectedItem().toString(),Integer.parseInt(this.txtQuantite.getText()));
			this.dispose();
		} catch (NumberFormatException e1) 
		{
			JOptionPane.showMessageDialog(null, "Vous n'avez pas utilisé le bon type de donné", "Erreur", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		catch (QuantiteException e2)
		{
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

}
