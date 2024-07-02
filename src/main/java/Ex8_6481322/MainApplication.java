// Don't forget to rename the package
package Ex8_6481322;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainApplication extends JFrame implements KeyListener
{
    private JLabel             contentpane;
    private CharacterLabel []    petLabels;
    private CharacterLabel     activeLabel;
    private ItemLabel            wingLabel;
    
    private int framewidth   = MyConstants.FRAMEWIDTH;
    private int frameheight  = MyConstants.FRAMEHEIGHT;
    private int groundY      = MyConstants.GROUND_Y;
    private int skyY         = MyConstants.SKY_Y;
    private int bridgeLeftX  = MyConstants.BRIDGE_LEFT;
    private int bridgeRightX = MyConstants.BRIDGE_RIGHT;

    public static void main(String[] args) 
    {
	    new MainApplication();
    }	    
    
    public MainApplication()
    {      
	    setSize(framewidth, frameheight);
        setLocationRelativeTo(null);
	    setVisible(true);
	    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        // set background image by using JLabel as contentpane
        setContentPane(contentpane = new JLabel());
        MyImageIcon background = new MyImageIcon(MyConstants.FILE_BG).resize(framewidth, frameheight);
        contentpane.setIcon( background );
        contentpane.setLayout( null );
        
        petLabels = new CharacterLabel[2];
	    petLabels[0] = new CharacterLabel(MyConstants.FILE_DOG_1, MyConstants.FILE_DOG_2,
                                          120, 100, this); 
        petLabels[0].setMoveConditions(bridgeLeftX-120, groundY, true, false);
        
        petLabels[1] = new CharacterLabel(MyConstants.FILE_CAT_1, MyConstants.FILE_CAT_2, 
                                          120, 100, this);
        petLabels[1].setMoveConditions(bridgeRightX, groundY, true, false);
        
        wingLabel = new ItemLabel(MyConstants.FILE_WING, 100, 80, this);
        wingLabel.setMoveConditions(bridgeRightX+300, skyY, true, true);        

        
        // first added label is at the front, last added label is at the back
        contentpane.add( wingLabel );
        contentpane.add( petLabels[0] );
        contentpane.add( petLabels[1] );

        addKeyListener(this);

        setDog();
	    repaint();
    }


    @Override
    public void keyTyped( KeyEvent e ) {
    }
    @Override
    public void keyPressed( KeyEvent e ) {
        System.out.printf("KeyChar : [%c]", e.getKeyChar());
        switch(e.getKeyChar()) {
            case 'd':
            case 'D':
                setDog(); break;
            case 'c':
            case 'C':
                setCat(); break;
            case 'j':
            case 'J':
                jump(); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    public CharacterLabel getActiveLabel()  { return activeLabel; }    
    public void setDog()                    { activeLabel = petLabels[0]; setTitle("Dog is active"); }
    public void setCat()                    { activeLabel = petLabels[1]; setTitle("Cat is active"); }
    public void jump  ()                    { }
}

////////////////////////////////////////////////////////////////////////////////
abstract class BaseLabel extends JLabel
{
    protected MyImageIcon      iconMain, iconAlt;
    protected int              curX, curY, width, height;
    protected boolean          horizontalMove, verticalMove;
    protected MainApplication  parentFrame;   
    
    // Constructors
    public BaseLabel() { }    
    public BaseLabel(String file1, int w, int h, MainApplication pf)
    {
        width = w; height = h;
        iconMain = new MyImageIcon(file1).resize(width, height);  
        setHorizontalAlignment(JLabel.CENTER);
        setIcon(iconMain);
        parentFrame = pf;          
        iconAlt = null;
    }
    public BaseLabel(String file1, String file2, int w, int h, MainApplication pf)				
    { 
        this(file1, w, h, pf);
        iconAlt = new MyImageIcon(file2).resize(width, height);
    }

    // Common methods
    public void setMoveConditions(boolean hm, boolean vm)
    {
        horizontalMove = hm; 
        verticalMove   = vm;
    }    
    public void setMoveConditions(int x, int y, boolean hm, boolean vm)
    {
        curX = x; curY = y; 
        setBounds(curX, curY, width, height);
        setMoveConditions(hm, vm);
    } 
    
    abstract public void updateLocation(); 
}

////////////////////////////////////////////////////////////////////////////////
class CharacterLabel extends BaseLabel 
{
    public CharacterLabel(String file1, String file2, int w, int h, MainApplication pf)				
    { 
        // Main icon without wings, alternative icon with wings
        super(file1, file2, w, h, pf);
    }
    
    public void updateLocation()    { }    
    public void moveUp()            { }
    public void moveDown()          { }
    public void moveLeft()          { }
    public void moveRight()         { }
    public void jump()              { }
}

////////////////////////////////////////////////////////////////////////////////
class ItemLabel extends BaseLabel implements MouseMotionListener
{
    public ItemLabel(String file, int w, int h, MainApplication pf)				
    { 
        // Alternative icon = null
        super(file, w, h, pf);
    }   

    public void updateLocation()    { }
    public void setMainIcon()       { setIcon(iconMain); }    
    public void setAltIcon()        { setIcon(iconAlt); }
    public void mouseDragged(MouseEvent e) {
        //
    }

    public void mouseMoved(MouseEvent e) {

    }
}
