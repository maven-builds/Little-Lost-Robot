package main;

import Tile.TileManager;
import entity.Player;
import object.SuperObject;

import javax.swing.JPanel;
import java.awt.*;

// We "extend" JPanel to define the "panel" or window of our game
// Our GamePanel class "implements" Runnable in order to set up our game clock (line 31) down below.

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings

    final int originalTileSize = 16; // 16 x 16 tile sizes
    final int scale = 3; // This scales our tiles to 48 x 48

    public final int tileSize = originalTileSize * scale; // 48 x 48 tile size

    // Our window is essentially a rectangle with "squares/pixels" as its units

    public final int maxScreenColumn = 16; // Sets the length, in horizontal columns, of our window
    public final int maxScreenRow = 12; // Sets th  e width, in vertical rows, of our window

    public final int screenWidth = tileSize * maxScreenColumn; // Defines the width of our window, 48 x 16 or 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // Defines the height of our window, 48 x 12 or 576 pixels

    // WORLD SETTINGS - Every variable that essentially defines our new game window + map

    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 50;

    // FPS

    int FPS = 60; // Defining our frame rates per second here

    TileManager tileH = new TileManager(this);
    KeyHandler keyH = new KeyHandler(); // Created to handle keyboard input as defined in the KeyHandler class
    Thread gameThread; // This constructor sets up a game "clock/thread" for processing frame rates per second

    Sound music = new Sound(); // Instantiating sounds for this GamePanel class - MUSIC
    public Sound effects =  new Sound(); // Instantiating sounds for this GamePanel class - SOUND EFFECTS

    public Player player = new Player(this, keyH);

    public SuperObject obj[] = new SuperObject[100]; // We have prepared 10 "slots" to display up to 10 objects simultaneously
    // This SuperObject array will be called again in the paintComponent function at the bottom

    public CollisionChecker tracker = new CollisionChecker(this); // Track collision
    public AssetSetter aSetter = new AssetSetter(this);

    public UI canvas = new UI(this); // Instantiating the UI class

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Sets the width and height of our window
        this.setBackground(Color.black); // Sets the background color of our window
        this.setDoubleBuffered(true); // All "painting/drawing" is done off-screen if set to "true". Improves performance
        this.addKeyListener(keyH); // We've now included our key handler, "keyH", from line 26
        this.setFocusable(true); // The GamePanel is now "focused" to receive keyboard input

    }

    public void gameSetUp()
    {
        aSetter.setObject();
        playMusic(3);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this); // We instantiate a game thread by passing our GamePanel to the Thread constructor
        gameThread.start(); // This officially starts our game thread and "calls" the "run" function below
    }

    // This "calls" our gameThread

    @Override
    public void run() {

        // Screen gets drawn and redrawn every ~0.0167 seconds
        double drawInterval = 1000000000 / FPS; // Defines FPS. "1,000,000,000" used because we're using nanoseconds for our game clock
        double nextDrawTime = System.nanoTime() + drawInterval; // Roughly 0.0167 seconds is spent on each loop
        System.out.println("FPS: " + FPS);

        while (gameThread != null) // As long as our gameThread exists, it "repeats" the code here
        {

            update(); // We call our update() method here

            repaint(); // Confusing, but this is how you call paintComponent()

            // Our update() and repaint() methods continually update and draw the screen as a result of our game loop

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // This returns how long until the next draw time
                remainingTime = remainingTime / 1000000; // This converts our remainingTime variable to milliseconds

                if (remainingTime < 0)
                {
                    remainingTime = 0; // This thread won't need to sleep if the allocated time to has been used.
                }

                Thread.sleep( (long) remainingTime); // Our thread won't do anything until the "sleep" time is over
                nextDrawTime += drawInterval; // The next draw time will be ~0.0167 seconds later

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() // Continually updates our game
    {

        player.update();
    }

    public void paintComponent(Graphics g) // Graphics is a class for drawing objects on the screen
    {
        super.paintComponent(g); // We access our Graphics "g" here as a way to draw on the screen
        Graphics2D g2 = (Graphics2D)g; // We convert our Graphics class above to a Graphics2D class

        g2.setColor(Color.white); // Draws a rectangle on the screen and fills it with the specified color

        // DEBUGGING (RENDER TIME)
        long drawStart = 0;

        if (keyH.trackRendering == true)
        {
            drawStart = System.nanoTime(); // This will check the time it takes to draw objects
        }

        // Coordinates "playerX" and "playerY" are now variables

        tileH.draw(g2); // Tiles will be drawn before the player

        // We draw objects here, after the tile, but before the player
        // We cannot simply invoke the 'draw()' function in SuperObject as it represents varying elements.

        // We will use a for loop

        for (int i = 0; i < obj.length; i++) // Each element in this array will be scanned individually
        {
            if (obj[i] != null) // If this element isn't null...
            {
                obj[i].draw(g2, this); // ... then draw it
            }
        }

        player.draw(g2); // Draw the player
        canvas.draw(g2); // This will draw all UI elements above every other graphic

        // DEBUGGING (DRAW TIME)

        if (keyH.trackRendering == true)
        {
            long drawEnd = System.nanoTime(); // How long does it take to finish drawing
            long timePassed = drawEnd - drawStart; // Time spent drawing
            g2.setColor(Color.white);
            g2.drawString("Render Time: " + timePassed + " nanoseconds", 10, 400); // Display Render Time
            System.out.println("Render Time:" + timePassed + " nanoseconds");
        }

        g2.dispose(); // Clear the screen
    }

    public void playMusic(int i)
    {
        // We call the functions written in our Sound class to play music, not sound effects

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        effects.stop();
    }

    public void playEffect(int i)
    {
        // Here we play sound effects

        effects.setFile(i);
        effects.play();
    }

    public void stopEffect(int i)
    {
        effects.stop();
    }

}
   