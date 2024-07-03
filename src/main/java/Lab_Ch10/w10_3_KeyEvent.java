package Lab_Ch10;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class w10_3_KeyEvent extends JFrame 
{
    private JPanel          contentpane;
    private JPasswordField  passfield;
    private JTextArea       textarea;
    private DoubleText      dtext;

    public w10_3_KeyEvent()
    {
	setTitle("This is a Frame");
	setBounds(200, 200, 700, 300);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setBackground( new Color(100, 150, 250) );
	contentpane.setLayout( new FlowLayout() );

	AddComponents();
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
	new w10_3_KeyEvent();
    }
}

////////////////////////////////////////////////////////////////////////////////////

// ----- (1) DoubleText component, with KeyListener 
/*
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
	String current = out.getText();

	if ( e.getKeyChar() != '\b' )
	{
            current = current + e.getKeyChar();
            out.setText(current);
	}
	else
	{
            int last = current.length() - 1;
            current  = current.substring(0, last);
            out.setText(current);
        }
    }

    public void keyPressed( KeyEvent e )	{ }
    public void keyReleased( KeyEvent e )	{ }
};
*/

////////////////////////////////////////////////////////////////////////////////////

// ----- (2) DoubleText component, with CaretListener

class DoubleText implements CaretListener
{
    private JPasswordField in;
    private JTextArea      out;

    public DoubleText(JPasswordField p, JTextArea t)	
    { 
	in = p; out = t; 
	in.addCaretListener( this );
    }

    @Override
    public void caretUpdate( CaretEvent e )
    {
	out.setText( in.getText() );
        //out.setText( new String(in.getPassword()) );
    }
};
