package Lab_Ch10;

import java.awt.*;
import javax.swing.*;

class w10_5_PrintFrame extends JFrame
{
    private JPanel          contentpane;
    private JCheckBox []    check;
    private JTextArea       text;

    // ----- (1) message from previous frame
    private Object [] items   = null;
    private String    message = "";

    public w10_5_PrintFrame()
    {
	setTitle("This is a new Frame");
	setBounds(200, 200, 400, 400);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );
    }

    public void setCheckMessage( Object [] m )	{ items = m; }
    public void setTextMessage( String m )		{ message = m; }


    public void AddComponents()
    {
	JPanel cpanel = new JPanel();
	if (items != null)
	{
            check = new JCheckBox [ items.length ];
            for (int i=0; i < items.length; i++)
            {
		check[i] = new JCheckBox( items[i].toString() );
		cpanel.add( check[i] );
            }
	}

	text = new JTextArea(message, 8, 40);
	text.setFont( new Font("SanSerif", Font.BOLD | Font.ITALIC, 20) );

	contentpane.add(cpanel, BorderLayout.NORTH);
	contentpane.add(new JScrollPane(text), BorderLayout.SOUTH);

	validate();
    }


    public static void main(String[] args) 
    {
	// ----- (2) test this frame independently
	w10_5_PrintFrame frame = new w10_5_PrintFrame();

	// ----- (3) suppose that messages are passed from previous frame
	frame.setTextMessage( "Hello Test \nHow are you ?" );
	String [] si = {"January", "February", "March", "April", "May", "June"};
	frame.setCheckMessage( si );

	frame.AddComponents();
    }
};
