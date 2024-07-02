// Don't forget to rename the package
package Ex8_6481322;

import java.awt.Image;
import javax.swing.ImageIcon;


// Interface for keeping constant values
interface MyConstants
{
    //----- Resource files
    //      Don't forget to change path
    static final String PATH        = "src/main/java/Ex8_6481322/resources/";
    static final String FILE_BG     = PATH + "background_bridge.jpg";
    static final String FILE_CAT_1  = PATH + "cat.png";
    static final String FILE_CAT_2  = PATH + "catwing.png";
    static final String FILE_DOG_1  = PATH + "dog.png";
    static final String FILE_DOG_2  = PATH + "dogwing.png";
    static final String FILE_WING   = PATH + "wing.png";    
    
    //----- Sizes and locations
    static final int FRAMEWIDTH   = 1000;
    static final int FRAMEHEIGHT  = 500;
    static final int GROUND_Y     = 220;
    static final int SKY_Y        = 50;
    static final int BRIDGE_LEFT  = 340;
    static final int BRIDGE_RIGHT = 500;
}


// Auxiliary class to resize image
class MyImageIcon extends ImageIcon
{
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height)
    {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
}