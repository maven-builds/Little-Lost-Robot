package main;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)  
    {
        JFrame window = new JFrame(); // Create a new window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Make the window "close-able"
        window.setResizable(true); // Window cannot be resized
        window.setTitle("Little Lost Robot"); // Window title

        GamePanel gamePanel = new GamePanel(); // "Import" our aGamePanel class through a "gamePanel" variable
        Component add = window.add(gamePanel);// "Activates/Adds" our gamePanel class/variable
        window.pack(); // "Packs" the window to fit the preferred size and layout of its subcomponents (GamePanel)

        window.setLocationRelativeTo(null); // Window will be displayed at the center of the screen
        window.setVisible(true); // Window will be visibleaassas

        gamePanel.gameSetUp(); // Invoking the 'objSetUp' function given in our GamePanel class
        gamePanel.startGameThread(); // Activates our game thread

        // Scaling Beforehand: 270,000 to 620,000 nanoseconds, ~445,000 nanoseconds avg.
        // Scaling During The Game Loop: 450,000 to 920,000 nanoseconds, ~685,000 nanoseconds avg.
        // Game renders ~35% faster now
    }
}