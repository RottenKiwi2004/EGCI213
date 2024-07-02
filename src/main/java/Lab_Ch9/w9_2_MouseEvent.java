package Lab_Ch9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class w9_2_MouseEvent extends JFrame 
{
    private JPanel	contentpane;
    private MyButton	button1;


    public w9_2_MouseEvent()
    {
	setTitle("This is a Frame");
        setSize(700, 300);
        setLocationRelativeTo(null);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

	contentpane = (JPanel)getContentPane();
	contentpane.setBackground( new Color(100, 150, 250) );
	contentpane.setLayout( null );

	button1 = new MyButton();
	contentpane.add(button1);
	repaint();
    }


    public static void main(String[] args) 
    {
	new w9_2_MouseEvent();
    }
}

////////////////////////////////////////////////////////////////////////////////////
class MyButton extends JButton implements MouseListener, MouseMotionListener
{
    private int       curX   = 100, curY   = 20;
    private int       width  = 100, height = 30;     // for text button
    //private int       width  = 100, height = 100;    // for icon button
    private boolean   drag;

    // ----- (5) use icon instead of text
    private ImageIcon  clickIcon, dragIcon;

    public MyButton()				
    { 
        String path = "src/main/Java/Lab_Ch9/";
	clickIcon   = new ImageIcon(path + "redbox.png");
	dragIcon    = new ImageIcon(path + "red.png");

	setBounds(curX, curY, width, height);
	setText("Click me");
	//setIcon(clickIcon);
	drag = false;

	// ----- (3) add listeners
	addMouseListener( this );
	addMouseMotionListener( this );
    }

    public void mousePressed( MouseEvent e )	{ }
    public void mouseReleased( MouseEvent e )	{ }
    public void mouseEntered( MouseEvent e )	{ }	
    public void mouseExited( MouseEvent e )	{ }
    public void mouseMoved( MouseEvent e )	{ }

    // ----- (1) handler for MouseListener
    @Override
    public void mouseClicked( MouseEvent e )	
    { 
	if ( !drag )
	{
            setCursor( new Cursor(Cursor.MOVE_CURSOR) );
            setText("Drag me");
            //setIcon(dragIcon);
            drag = true;
	}
	else
	{
            setCursor( new Cursor(Cursor.DEFAULT_CURSOR) );
            setText("Click me");
            //setIcon(clickIcon);
            drag = false;
	}
    }

    // ----- (2) handler for MouseMotionListener
    @Override
    public void mouseDragged( MouseEvent e )	
    { 
	if ( drag )
	{
            curX = curX + e.getX();
            curY = curY + e.getY();

            // ----- (4) bound for dragging
            /*
            Container p = getParent();
            if (curX < 0)  curX = 0;
            if (curY < 0)  curY = 0;
            if (curX + width  > p.getWidth())   curX = p.getWidth() - width;
            if (curY + height > p.getHeight())  curY = p.getHeight() - height;
            */

            setLocation(curX, curY);
        }
    }
};