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
        addMouseMotionListener(wingLabel);
        addMouseListener(wingLabel);

        setDog();
	    repaint();
    }

    @Override
    public void keyTyped( KeyEvent e ) { }
    @Override
    public void keyPressed( KeyEvent e ) {
//        System.out.printf("KeyChar : [%3d]\n", e.getKeyCode());
        switch(e.getKeyCode()) {
            case 27: if (!activeLabel.verticalMove) break;
                     activeLabel.setWingStatus(false);
                     wingLabel.setVisible(true);
                     wingLabel.curX = activeLabel.getX();
                     wingLabel.curY = MyConstants.SKY_Y;
                     wingLabel.updateLocation();
                     break;
            case 37: activeLabel.moveLeft();    break;
            case 38: activeLabel.moveUp();      break;
            case 39: activeLabel.moveRight();   break;
            case 40: activeLabel.moveDown();    break;
            // C
            case 67: setCat(); break;
            // D
            case 68: setDog(); break;
            // J
            case 74: jump(); break;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) { }

    public CharacterLabel getActiveLabel()  { return activeLabel; }
    public void setDog() { activeLabel = petLabels[0]; setTitle("Dog is active"); }
    public void setCat() { activeLabel = petLabels[1]; setTitle("Cat is active"); }
    public void jump  () { activeLabel.jump(); }
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
    protected int putValueInBound(int lowerBound, int value, int upperBound) {
        return Math.max(lowerBound, Math.min(value, upperBound));
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
    
    public void updateLocation()    { this.setLocation(this.curX, this.curY); }
    public void moveUp()            {
        if (!this.verticalMove) return;
        this.curY = this.putValueInBound(0, this.curY - 10, MyConstants.FRAMEHEIGHT - this.getHeight());
        this.updateLocation();
    }
    public void moveDown()          {
        if (!this.verticalMove) return;
        this.curY = this.putValueInBound(0, this.curY + 10, MyConstants.FRAMEHEIGHT - this.getHeight());
        this.updateLocation();
    }
    public void moveLeft()          {
        if (!this.horizontalMove) return;
//        System.out.println("moveLeft");
        this.curX = (this.curX + MyConstants.FRAMEWIDTH - 10 + this.getWidth() / 2) % MyConstants.FRAMEWIDTH - this.getWidth() / 2;
        this.updateLocation();
    }
    public void moveRight()         {
        if (!this.horizontalMove) return;
        this.curX = (this.curX + 10 + this.getWidth() / 2) % MyConstants.FRAMEWIDTH - this.getWidth() / 2;
        this.updateLocation();
    }
    public void jump()              {
        if (this.verticalMove) return;
        this.curX = this.curX <= MyConstants.BRIDGE_LEFT - this.getWidth() ? MyConstants.BRIDGE_RIGHT :
                    this.curX >= MyConstants.BRIDGE_RIGHT ? MyConstants.BRIDGE_LEFT - this.getWidth() :
                    this.curX;
        this.updateLocation();
    }
    public void setWingStatus(boolean status) {
        this.verticalMove = status;
        setIcon(status ? this.iconAlt : this.iconMain);
        this.curY = MyConstants.GROUND_Y;
        updateLocation();
    }
}

////////////////////////////////////////////////////////////////////////////////
class ItemLabel extends BaseLabel implements MouseMotionListener, MouseListener
{

    private boolean isDragging = false;
    private int startDragX, startDragY;
    private int startMouseX, startMouseY;
    private int dragOffsetX, dragOffsetY;

    public ItemLabel(String file, int w, int h, MainApplication pf)				
    { 
        // Alternative icon = null
        super(file, w, h, pf);
    }   

    public void updateLocation()    { this.setLocation(this.curX, this.curY); }
    public void setMainIcon()       { setIcon(iconMain); }    
    public void setAltIcon()        { setIcon(iconAlt); }

    @Override
    public void mouseDragged(MouseEvent e) {
//        System.out.printf("MouseDragged [%d, %d]\n", e.getX(), e.getY());
        if (!isDragging) return;
        dragOffsetX = e.getX() - startMouseX; dragOffsetY = e.getY() - startMouseY;
        curX = startDragX + dragOffsetX; curY = startDragY + dragOffsetY;
        curX = putValueInBound(0, curX, MyConstants.FRAMEWIDTH - this.getWidth());
        curY = putValueInBound(0, curY, MyConstants.FRAMEHEIGHT - this.getHeight() - 30);
        this.updateLocation();

        CharacterLabel activeLabel = parentFrame.getActiveLabel();
        if (this.getBounds().intersects(activeLabel.getBounds())) {
            activeLabel.setWingStatus(true);
            this.setVisible(false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)    { }
    @Override
    public void mouseClicked(MouseEvent e)  { }
    @Override
    public void mousePressed(MouseEvent e)  {
        System.out.printf("Wing       [%d, %d]\n", this.getX(), this.getY());
        System.out.printf("MouseDown  [%d, %d]\n", e.getX(), e.getY());
        System.out.printf("Wing Bound [%d, %d]\n\n\n", this.getX() + this.getWidth(), this.getY() + this.getHeight());
        // Check if started dragging inside the wing
        if (!isPointInBound(e.getX(), e.getY())) return;
        isDragging = true;
        startDragX = this.getX(); startDragY = this.getY();
        startMouseX = e.getX(); startMouseY = e.getY();
    }
    @Override
    public void mouseReleased(MouseEvent e) { isDragging = false;}
    @Override
    public void mouseEntered(MouseEvent e)  { }
    @Override
    public void mouseExited(MouseEvent e)   { }

    private boolean isPointInBound(int x, int y) {
        // Windows offset
        x -= 8;
        y -= 30;

        return this.getX() < x && x < this.getX() + this.getWidth()
                && this.getY() < y && y < this.getY() + this.getHeight();
    }
}
