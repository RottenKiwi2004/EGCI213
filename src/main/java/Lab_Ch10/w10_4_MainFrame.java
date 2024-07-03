package Lab_Ch10;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class w10_4_MainFrame extends JFrame
{
    private JPanel	contentpane;
    private JList	list;
    private JTextArea	text;
    private JButton	print_button, draw_button, clear_button;

    private String [] items = {"January", "February", "March", "April", "May", "June", 
		               "July", "August", "September", "October", "November", "December"};

    // ----- (1) message to be passed to next frames
    private Object [] messageFromList;
    private String    messageFromText;

    // ----- (2) next frames
    w10_2_SemanticEvent sframe;
    w10_5_PrintFrame pframe;


    public w10_4_MainFrame()
    {
	setTitle("This is the first Frame");
	setBounds(200, 200, 350, 250);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );

	AddComponents();
    }

    public void AddComponents()
    {
	// ----- (3) TextArea + CaretEvent
	text = new JTextArea(6, 15);
	text.addCaretListener( new CaretListener() {
            @Override
            public void caretUpdate( CaretEvent e )
            {
		messageFromText = text.getText();
            }
	});


	// ----- (4) List + ListSelectionEvent
	list = new JList( items );
	list.setVisibleRowCount(5);
	list.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged( ListSelectionEvent e )
            {
		if( !e.getValueIsAdjusting() )
		{
                    messageFromList = list.getSelectedValues();
		}
            }
	});


	// ---- (5) Button + ActionEvent : clearup TextArea and List
	clear_button = new JButton("Clear");
	clear_button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) 
            {
		text.setText("");
		list.clearSelection();
            }
	});


	// ----- (6) Button + ActionEvent -> open SemanticEvent frame
	draw_button  = new JButton("Draw");
	draw_button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e )
            {
		if (sframe == null) sframe = new w10_2_SemanticEvent();
		else                sframe.setVisible(true);
            }
        });


	// ----- (7) Button + ActionEvent -> open PrintFrame
	print_button = new JButton("Print");
	print_button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e )
            {
		pframe = new w10_5_PrintFrame();

		// ----- (7.2) get messages from components
		//             no need to catch events in (3) and (4)
		//messageFromText = text.getText();
		//messageFromList = list.getSelectedValues();

		// ----- (7.1) pass messages to the next frame
		pframe.setTextMessage( messageFromText );
		pframe.setCheckMessage( messageFromList );
		pframe.AddComponents();
            }
	});

		
	JPanel mpanel = new JPanel();
	mpanel.add( new JScrollPane(text) );
	mpanel.add( new JScrollPane(list) );

	JPanel spanel = new JPanel();
	spanel.add(draw_button);
	spanel.add(print_button);
	spanel.add(clear_button);

	contentpane.add( mpanel, BorderLayout.CENTER );
	contentpane.add( spanel, BorderLayout.SOUTH );

	validate();
    }


    public static void main(String[] args) 
    {
	// ----- (8) check look & feel
	UIManager.LookAndFeelInfo [] laf = UIManager.getInstalledLookAndFeels();
	System.out.println("===== available look and feel =====");
	for (int i=0; i < laf.length; i++)
	{
            System.out.println( laf[i].getClassName() );
	}

	// ----- (9) set look and feel
	try
	{
            String look1 = "javax.swing.plaf.metal.MetalLookAndFeel";
            String look2 = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            String look3 = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            String look4 = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
            String look5 = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
            UIManager.setLookAndFeel(look5);
	}
	catch (Exception e) { System.out.println(e); }

	new w10_4_MainFrame();
    }
};
