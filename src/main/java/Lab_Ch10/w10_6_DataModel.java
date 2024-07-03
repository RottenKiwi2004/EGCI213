package Lab_Ch10;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class w10_6_DataModel extends JFrame
{
    private JPanel                 contentpane;
    private JList		   list;
    private DefaultListModel	   listmodel;
    private JComboBox              combo;
    private DefaultComboBoxModel   combomodel;
    private JButton		   add_button, remove_button1, remove_button2;
    private JTextField		   text;

    public w10_6_DataModel()
    {
	setTitle("This is a new Frame");
	setBounds(200, 200, 350, 250);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );

	AddComponents();
    }

    public void AddComponents()
    {
	text = new JTextField("Selection", 15);
	text.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 12));
	text.setEditable(false);      

        String []days = {"Monday", "Tuesday", "Wednesday"};

	// ----- (1) Readonly list
	//list = new JList(days);
        
        // ----- (2) Updatable list
        listmodel = new DefaultListModel();
        for(String s : days) listmodel.addElement(s);
        list = new JList(listmodel);              

        list.setPreferredSize( new Dimension(100, 80) );
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged( ListSelectionEvent e )
            {
                // Try with & without condition
                if ( !e.getValueIsAdjusting() )
                {
                    // Checking selection from JList (for both readonly & updatable lists)
                    Object[] items = list.getSelectedValues();
                    if (items.length > 0) text.setText("From List >> " + items[0]);
                    else                  text.setText("");
                }
            }
        });
	     
               
        // ----- (3) Can update JComboBox directly (automatically linked to combomodel)
        combo      = new JComboBox(days);
        combomodel = (DefaultComboBoxModel) combo.getModel();
        
        combo.setPreferredSize( new Dimension(100, 20) );
        combo.addItemListener( new ItemListener() {
            @Override
            public void itemStateChanged( ItemEvent e )
            {
                Object item = combo.getSelectedItem();
                text.setText("From Combo >> " + item);
            }
        });

        
	add_button  = new JButton("Add All");
	add_button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e )
            {
		// ----- (4) Add new item to listmodel & JComboBox
                //           No method to add item to JList
		String item = JOptionPane.showInputDialog("Enter input");
		if (listmodel != null)   listmodel.addElement(item);
                
                combo.addItem(item);
                //if (combomodel != null)  combomodel.addElement(item);
            }
	});

        
	remove_button1  = new JButton("Remove List");
	remove_button1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e )
            {
		// ----- (5) get selected items & remove them from listmodel
                //           No method to remove item from JList
		Object x[] = list.getSelectedValues();
		for (int i=0; i < x.length; i++)
		{
                    if (listmodel != null) listmodel.removeElement(x[i]);
		}
            }
	});

        
	remove_button2  = new JButton("Remove Combo");
	remove_button2.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e )
            {
		// ----- (6) get selected items & remove them from JComboBox
                Object x = combo.getSelectedItem();
                //combo.removeItem(x);
                if (combomodel != null) combomodel.removeElement(x);
            }
	});        

        
        JPanel listPanel = new JPanel();            
        JScrollPane scroll = new JScrollPane(list);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listPanel.add(scroll);
               
        JPanel comboPanel = new JPanel();
        comboPanel.add(combo); 
        
	JPanel buttonPanel = new JPanel();
	buttonPanel.add(add_button);
	buttonPanel.add(remove_button1);
        buttonPanel.add(remove_button2);

        contentpane.add( text,        BorderLayout.NORTH);     
        contentpane.add( listPanel,   BorderLayout.WEST);
        contentpane.add( comboPanel,  BorderLayout.EAST);
        contentpane.add( buttonPanel, BorderLayout.SOUTH);        
	validate();
    }

    public static void main(String[] args) 
    {
	new w10_6_DataModel();
    }
};
