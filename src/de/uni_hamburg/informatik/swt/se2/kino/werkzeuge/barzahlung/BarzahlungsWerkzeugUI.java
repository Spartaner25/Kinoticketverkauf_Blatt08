package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Klasse, die die Ui des Bezahldialogs bereitstellt
 */
public class BarzahlungsWerkzeugUI extends JDialog {

	/**
	 * Standard JDialog UID
	 */
	private static final long serialVersionUID = 1L;
	private JDialog BarzahlungDialog;
	private JPanel content;
	private JPanel input;
	private JPanel buttons;
	
	private JButton _okButton;
	private JButton _abbrechenButton;
	
	private JLabel _preis;
	private JTextField _gegeben;
	private JLabel _rest;
/*
 * Erstellt die Ui des Barzahlwerkzeugs
 */
	public BarzahlungsWerkzeugUI() {
		BarzahlungDialog=new JDialog(this,"Barzahlung",true);
        
        // Set size
        BarzahlungDialog.setSize(300,160);
        
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - BarzahlungDialog.getWidth()) / 2;
        final int y = (screenSize.height - BarzahlungDialog.getHeight()) / 2;
        BarzahlungDialog.setLocation(x, y);
        
        content = new JPanel();
        BarzahlungDialog.add(content);
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        
        input = new JPanel();
        buttons = new JPanel();
        content.add(input);
        content.add(buttons);
        
        input.setLayout(new GridLayout(3, 2));  
        
        input.add(new JLabel("Preis:",JLabel.CENTER));
        _preis = new JLabel("0");
        input.add(_preis);
        
        input.add(new JLabel("Gegeben:",JLabel.CENTER));
        _gegeben = new JTextField("0");
        input.add(_gegeben);
        
        input.add(new JLabel("Zurück:",JLabel.CENTER));
        _rest = new JLabel("0");
        input.add(_rest);
        
        buttons.setLayout(new FlowLayout());
        _okButton = new JButton("OK");
        buttons.add(_okButton);
        
        _abbrechenButton =new JButton("Abbrechen");
        buttons.add(_abbrechenButton);
        
	}
	
	/*
	 * Gibt das Label Preis zurück
	 */
	public JLabel getPreis()
	{
		return _preis;
	}
	
	/*
     * Gibt das Label Gegeben zurück
     */
	public JTextField getGegeben()
	{
		return _gegeben;
	}
	
	/*
     * Gibt das Label Rest zurück
     */
	public JLabel getRest()
	{
		return _rest;
	}
	
	/*
     * Gibt den Button Ok zurück
     */
	public JButton getOKButton()
	{
		return _okButton;
	}
	
	/*
     * Gibt den Button Cancel zurück
     */
	public JButton getAbbrechenButton()
	{
		return _abbrechenButton;
	}
	
	/*
	 * Zeigt die Warnung bzgl Buchstabeneingabe an
	 */
	public void zeigeWarnung()
	{
		JOptionPane.showMessageDialog(this,
			    "Bitte geben sie nur Zahlen ein.",
			    "Preiseingabe",
			    JOptionPane.WARNING_MESSAGE);
	}
	/*
	 * Methode um den Barzahldialog sichtabr oder unsichtbar zu machen
	 * @param visible Ist es sichabr oder nicht? Boolean visible regelt das!
	 */
	public void isDialogvisible(boolean visible)
	{
		// Like JFrame, JDialog isn't visible, you'll
        // have to make it visible
        // Remember to show JDialog after its parent is
        // shown so that its parent is visible
		// And remember that you need to register
		// the UIlisteners before making it visible
        BarzahlungDialog.setVisible(visible);
	}
}
