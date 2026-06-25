package main;

import object.ObjectBoots;
import object.ObjectFruit;
import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.swing.*;

import static java.awt.font.TextAttribute.FONT;

// This will manage on-screen non-game visuals, the UI
public class UI {

    GamePanel gp;
    Font text1; // Let's EXCLUDE this from the game loop
    BufferedImage keyVisual;

    Font text2;
    BufferedImage bootVisual;

    Font gameOverFont; // We'll use this for endState messages

    Font text3;
    BufferedImage berryVisual;

    double timeSpent; // Time spent playing
    DecimalFormat timeHUD = new DecimalFormat("#0.00"); // Limit the time playing to the tenths place

    public boolean dispatchSent = false; // Dispatch has NOT been sent
    public String dispatch = ""; // This will be some text shown to the player after a certain trigger
    int dispatchTracker = 0; // Let's keep track of how many times a dispatch is shown to the player
    public boolean endState = false; // If this endState is true, then the game is completed

    public UI(GamePanel gp)
    {
        this.gp = gp;

        gameOverFont = new Font("Arial", Font.BOLD, 80); // Defining the endState font as bold
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

        // Check if the game is finished or ongoing

        if (endState == true)
        {
            String text; int textLength;

            int x; int y;

            text = "Congrats! You spent " + timeHUD.format(timeSpent) + " seconds playing!"; // Player has found the chest
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Return the length of the text above

            x = gp.screenWidth / 2 - ((textLength/2) + 274); // This will slightly center the text
            y = gp.screenHeight / 2 - (gp.tileSize * 3); // To show the player more

            g2.setFont(text1);
            g2.setFont(text2);
            g2.drawString(text, x, y); // Display these elements instead of rendering the whole game when endState is true

            g2.setFont(gameOverFont);
            g2.setColor(Color.yellow);

            text = "You've Finished The Game!"; // Player has finished the game
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 + (textLength * 2); // We'll display this message sligtly lower than the first
            y = gp.screenHeight / 2; // - (gp.tileSize * 3)

            // Finally ending the game

            gp.gameThread = null; // This stops the gameThread from running
        }

        else
        {
            // The right way to set fonts:
            g2.setFont(text1);
            g2.setFont(text2);
            g2.setColor(Color.white); // Setting the color of the text

            g2.drawImage(keyVisual, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString(" X " + gp.player.numOfKeys, 65, 63); // Specifies the position of the Key icon

            g2.drawImage(bootVisual, gp.tileSize/2, gp.tileSize/2 + 63, gp.tileSize, gp.tileSize, null);
            g2.drawString(" X " + gp.player.numOfBoots, 65, 129); // Specifies the position of the Boot icon

            // Recording Time Spent Playing

            timeSpent += (double) 1/60; // This will call the draw method 60 times a second
            g2.drawString("Time: " + timeHUD.format(timeSpent), gp.tileSize * 11, 65);


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
}
