package main;

import object.ObjectBoots;
import object.ObjectFruit;
import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import static java.awt.font.TextAttribute.FONT;

// This will manage on-screen non-game visuals, the UI
public class UI {

    GamePanel gp;
    Font text1; // Let's EXCLUDE this from the game loop
    BufferedImage keyVisual;

    Font text2;
    BufferedImage bootVisual;

    Font text3;
    BufferedImage berryVisual;

    public boolean dispatchSent = false; // Dispatch has NOT been sent
    public String dispatch = ""; // This will be some text shown to the player after a certain trigger
    int dispatchTracker = 0; // Let's keep track of how many times a dispatch is shown to the player

    public UI(GamePanel gp)
    {
        this.gp = gp;
        text1 = new Font("Arial", Font.PLAIN, 40); // Font descriptor for the first text
        text2 = new Font("Arial", Font.PLAIN, 40); // Front descriptor for the second text

        ObjectKey key = new ObjectKey(); // Instantiating the ObjectKet class
        keyVisual = key.image;

        ObjectBoots boots = new ObjectBoots();
        bootVisual = boots.image;

        ObjectFruit berry = new ObjectFruit();
        berryVisual = berry.image;
    }

    public void revealDispatch(String text)
    {
        dispatch = text; // Whatever the text/dispatch is
        dispatchSent = true; // Dispatch has been sent
    }

    public void draw(Graphics2D g2)
    {
        // g2.setFont(new Font("Roboto", Font.PLAIN, 40)); This would've INCLUDED the font in our game loop

        /*  The code above sets out UI font, but it is important to NOT instantiate in the GamePanel class as takes time.
        This means that the draw() method for this UI class will be included in 60 FPS of the game loop, consuming resources */

        // The right way:
        g2.setFont(text1);
        g2.setFont(text2);
        g2.setColor(Color.white); // Setting the color of the text

        g2.drawImage(keyVisual, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString(" X " + gp.player.numOfKeys, 65, 63); // Specifies the position of the Key icon

        g2.drawImage(bootVisual, gp.tileSize/2, gp.tileSize/2 + 63, gp.tileSize, gp.tileSize, null);
        g2.drawString(" X " + gp.player.numOfBoots, 65, 129); // Specifies the position of the Boot icon

        if (dispatchSent == true)
        {
            g2.setFont(g2.getFont().deriveFont(30F)); // We set the font for the dispatch
            g2.drawString(dispatch, gp.tileSize / 2, gp.tileSize * 5); // Shows the dispatch as a UI element

            dispatchTracker++; // Every time a dispatch is drawn on screen, the dispatchTracker will go up by 1

            if (dispatchTracker > 120 && (dispatch == "New Key Collected!" || dispatch == "Door Unlocked"))
            {
                // If a key is collected or a door is unlocked, after 2 seconds, the dispatch is no longer rendered

                dispatchTracker = 0;
                dispatchSent = false;
            }

            else if (dispatchTracker > 180 && (dispatch == "Speed Boost Activated!" || dispatch == "Violet Berry Eaten"))
            {
                // If a boot is collected, after 3 seconds, the dispatch is no longer redered

                dispatchTracker = 0;
                dispatchSent = false;
            }

            else if (dispatchTracker > 150 && dispatch == "You need a key first!")
            {
                dispatchTracker = 0;
                dispatchSent = false;
            }
        }
    }
}
