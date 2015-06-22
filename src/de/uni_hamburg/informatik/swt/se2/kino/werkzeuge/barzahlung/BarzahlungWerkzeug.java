package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

public class BarzahlungWerkzeug {
	
	private int _preis;
	private BarzahlungsWerkzeugUI _ui;
	
	public BarzahlungWerkzeug()
	{
		_ui = new BarzahlungsWerkzeugUI();
		registriereUIAktionen();
	}
	
	public void setzePreis(int preis)
	{
		_preis = preis;
		_ui.getPreis().setText(""+preis);
    	_ui.getOKButton().setEnabled(false);
    	_ui.isDialogvisible(true);
	}
	
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
	
	private String arraytostring(String[] array)
	{
		StringBuilder builder = new StringBuilder();
		for (String value : array) {
		    builder.append(value);
		}
		return builder.toString();
	}
	
	//TODO: Negative Rest
	private void setzeRest(int gegeben)
	{
		int rest = gegeben - _preis;
		if (rest >= 0) {
			_ui.getRest().setText(rest+"");
			_ui.getOKButton().setEnabled(true);
		}
	}
	
	public void isDialogvisible(boolean visible)
	{
		_ui.isDialogvisible(visible);
	}
	
	public BarzahlungsWerkzeugUI getUI()
	{
		return _ui;
	}
	
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
