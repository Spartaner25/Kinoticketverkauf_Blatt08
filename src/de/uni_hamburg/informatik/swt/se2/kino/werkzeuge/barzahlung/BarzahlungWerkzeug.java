package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

/**
 * Mit diesem Werkzeug wird die Barzahlung erledigt
 * 
 * @author kompetenzzentrum
 */
public class BarzahlungWerkzeug {
	
	private int _preis;
	private BarzahlungsWerkzeugUI _ui;
	
	/**
	 * Erzeugt ein neues Barzahlungswerkezug inkl. UI und registriert die Eventlistener.
	 */
	public BarzahlungWerkzeug()
	{
		_ui = new BarzahlungsWerkzeugUI();
		registriereUIAktionen();
	}
	
	/**
	 * Setzt den Preis im Dialogfenster un macht es sichtbar.
	 * @param preis Der zu setzende Preis.
	 * @require Preis grösser gleich Null.
	 */
	public void setzePreis(int preis)
	{
	    assert preis >= 0 : "Vorbedingung verletzt: preis >= null";
		_preis = preis;
		_ui.getPreis().setText(""+preis);
    	_ui.getOKButton().setEnabled(false);
    	_ui.isDialogvisible(true);
	}
	
	/**
	 * Filtert die Eingaben, so dass nur Zahlen eingegebn werden,
	 * ansonsten wird eine Warnung ausgegeben.
	 */
	private void filtereEingabe()
	{
		String gegeben_string = _ui.getGegeben().getText();
		//TODO: Allow Whitespace character 
		if (Pattern.matches("[0-9]+", gegeben_string)) {
			int gegeben_int = Integer.parseInt(gegeben_string);
			setzeRest(gegeben_int);
		} else {
			String[] zahlen = gegeben_string.split("[^0-9]+");
			gegeben_string = arraytostring(zahlen);
			if (gegeben_string.length() == 0) {
				_ui.getGegeben().setText("0");
			} else {
				_ui.getGegeben().setText(gegeben_string);
			}
			_ui.zeigeWarnung();
		}
		
	}
	
	/**
	 * Erzeugt aus einem String array einen String
	 * @param array Das zu konvertierende Array
	 * @return Das Array als String
	 */
	private String arraytostring(String[] array)
	{
		StringBuilder builder = new StringBuilder();
		for (String value : array) {
		    builder.append(value);
		}
		return builder.toString();
	}
	
	/**
	 * Setzt den Restbetrag basierend auf dem übergebenen Preis,
	 * aktiviert entsprechend den OK Button
	 * @param gegeben Der eingegebene Preis
	 */
	//TODO: Negative Rest
	private void setzeRest(int gegeben)
	{
		int rest = gegeben - _preis;
		if (rest >= 0) {
			_ui.getRest().setText(rest+"");
			_ui.getOKButton().setEnabled(true);
		}
	}
	
	/**
	 * Setzt die Sichtbarkeit des Dialogs
	 * @param visible
	 */
	public void isDialogvisible(boolean visible)
	{
		_ui.isDialogvisible(visible);
	}
	
	/**
	 * Gibt die UI zurück.
	 * @return die verwendete DIalog UI
	 */
	public BarzahlungsWerkzeugUI getUI()
	{
		return _ui;
	}
	
	/**
	 * Setzt den Dialog und seine Werte auf den Startzustand zurück.
	 */
	private void reset()
	{
		_ui.isDialogvisible(false);
		_ui.getPreis().setText("0");
		_ui.getGegeben().setText("0");
		_ui.getRest().setText("0");
	}
	
	 /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     */
    private void registriereUIAktionen()
    {
    	_ui.getGegeben().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				_ui.getOKButton().setEnabled(false);
				_ui.getRest().setText("0");
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				filtereEingabe();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
    	_ui.getOKButton().addActionListener(new ActionListener() {
			//TODO: OK Button action
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
    	_ui.getAbbrechenButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				
			}
		});
    }

}
