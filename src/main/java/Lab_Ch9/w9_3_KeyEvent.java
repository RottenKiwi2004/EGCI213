package Lab_Ch9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class w9_3_KeyEvent extends JFrame 
{
    private JPanel              contentpane;
    private JPasswordField	passfield;
    private JTextArea		textarea;
    private DoubleText          dtext;

    public w9_3_KeyEvent()
    {
	setTitle("This is a Frame");
        setSize(700, 300);
        setLocationRelativeTo(null);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setBackground( new Color(100, 150, 250) );
	contentpane.setLayout( new FlowLayout() );

	AddComponents();

	// ----- (1) listener is automatically added when dtext is created
	//           in AddComponents()
    }

    public void AddComponents()
    {
	passfield = new JPasswordField(30);
	textarea  = new JTextArea(8, 30);
	dtext     = new DoubleText( passfield, textarea );

	Font f = new Font("SanSerif", Font.BOLD, 20);
	passfield.setFont(f);
	textarea.setFont(f);
	textarea.setEditable(false);

	contentpane.add(passfield);
	contentpane.add( new JScrollPane(textarea) );
	validate();
    }

    public static void main(String[] args) 
    {
	new w9_3_KeyEvent();
    }
}

////////////////////////////////////////////////////////////////////////////////////

// ----- (1) DoubleText component, with KeyListener 

class DoubleText implements KeyListener
{
    private JPasswordField in;
    private JTextArea      out;

    public DoubleText(JPasswordField p, JTextArea t)	
    { 
	in = p; out = t; 
	in.addKeyListener( this );
    }

    @Override
    public void keyTyped( KeyEvent e )
    {
	//System.out.printf("t >>  %c  (%s) \n", e.getKeyChar(), e.getKeyText(e.getKeyCode()) );
	String current = out.getText();

	// ----- (2) get the last char from "in" and add it to "out"
	current = current + e.getKeyChar();
	out.setText(current);

	// ----- (3) consume the event
	//e.consume();
    }

    @Override
    public void keyPressed( KeyEvent e )
    { 
	//System.out.printf("p >>  %c  (%s) \n", e.getKeyChar(), e.getKeyText(e.getKeyCode()) );

	// ----- (4) if DEL is pressed, clear everything
	//           DEL button has no unicode character -> getKeyCode()
	//
	//           what happens if we put move this to keyTyped(...) ?
	/*
	if ( e.getKeyCode() == KeyEvent.VK_DELETE )
	{
            in.setText("");
            out.setText("");
	}
	*/
    }

    public void keyReleased( KeyEvent e )	{ }
};