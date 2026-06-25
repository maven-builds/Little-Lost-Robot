package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public int numOfKeys = 0;
    public int numOfBoots = 0;
    public int numOfBerries = 0;
    public int berryCounter = 0;
    public int numOfCorn = 0;

    // These two are final integers, representing the player's position, so they won't change

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        solidArea = new Rectangle(); // We can pass four (x, y, width, height) parameters to this constructor

        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {

        // worldX and worldY place the player at the center of the screen.
        // We subtract (gp.tileSize / 2) from their assigned values because the game uses the top left corner of a tile to determine the player's position.
        // We subtract half of the tile length from screenX and screenY here.

        worldX = gp.tileSize * 23 - (gp.tileSize / 2);
        worldY = gp.tileSize * 21 - (gp.tileSize / 2);

        speed = 5; // Movement speed
        direction = "down"; // Assigning the player's direction a default value, "down" or south
    }

    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        // X-value increases if the player goes right
        // Y-value increases if the player goes down
        // In Java, in the upper-left corner of the screen, X = 0 and Y = 0

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true)
        {
            // Here:

            if (keyH.upPressed == true)
            {
                direction = "up";
            }

            else if (keyH.downPressed == true)
            {
                direction = "down";
            }

            else if (keyH.leftPressed == true)
            {
                direction = "left";
            }

            else if (keyH.rightPressed == true)
            {
                direction = "right";
            }

            // Check for tile collision

            collisionOn = false;
            gp.tracker.checkTile(this);

            // Check for object collision

            int objIndex = gp.tracker.trackOBJ(this, true);
            pickOBJ(objIndex);

            // If collision is false, the player cannot move
            if (collisionOn == false)
            {
                switch(direction)
                {
                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;

            // The update() method gets called 60 times a second, in every frame, it gets called, it increases our sprite counter by one
            // If it hits 10, it changes the sprite frame number from 1 to 2, or 2 to 1
            // This means the player image will change every 10 frames

            if (spriteCounter > 12)
            {
                if (spriteCounter > 10)
                {
                    if (spriteNumber == 1)
                    {
                        spriteNumber = 2;
                    }

                    else
                    {
                        spriteNumber = 1;
                    }

                    spriteCounter = 0;
                }
            }
        }
    }

    public void pickOBJ(int i)
    {
        if (i != 999 && gp.obj[i] != null)
        {
            String objName = gp.obj[i].name; // All available names of this object are given in the Object[Name] classes

            switch(objName)
            {
                case "Key":
                    gp.playEffect(2);
                    numOfKeys++; // The player will get more keys
                    gp.obj[i] = null; // The key being collided with disappears;

                    gp.canvas.revealDispatch("New Key Collected!"); // Display the dispatch on the UI
                    break;

                case "Door":
                    if (numOfKeys > 0)
                    {
                        gp.playEffect(4);
                        gp.obj[i] = null; // If the player has a key or more, then the door disappears upon collision
                        numOfKeys--; // Once the player "uses" a key to "open" a door, they will lose a key
                        gp.canvas.revealDispatch("Door Unlocked");
                        break;
                    }

                    else {
                        gp.canvas.revealDispatch("You need a key first!");
                    }

                    System.out.println("Keys: " + numOfKeys);
                    break;

                case "Boots":
                    numOfBoots += 1;
                    gp.playEffect(1);
                    speed += 3; // When the player picks up the boots, they become faster
                    gp.obj[i] = null; // When the boots are picked up, they disappear
                    gp.canvas.revealDispatch("Speed Boost Activated!");
                    break;

                case "Violet Berry":
                    numOfBerries += 1;
                    gp.playEffect(1);
                    speed += 7; // When the player picks up the boots, they become faster
                    berryCounter++;
                    gp.obj[i] = null; // When the boots are picked up, they disappear

                    if (berryCounter > 10)
                    {
                        // After 10 seconds, the speed boost from the Violet Berry is over
                        speed -= 7;
                    }

                    gp.canvas.revealDispatch("Violet Berry Eaten");
                    break;

                case "Corn":
                    numOfCorn += 1;
                    gp.playEffect(1);
                    speed += 7; // When the player picks up the boots, they become faster
                    berryCounter++;
                    gp.obj[i] = null; // When the boots are picked up, they disappear

                    if (berryCounter > 10)
                    {
                        // After 10 seconds, the speed boost from the Violet Berry is over
                        speed -= 7;
                    }

                    gp.canvas.revealDispatch("Corn Harvested");
                    break;

                case "Chest":
                    gp.playEffect(1);
                    gp.stopMusic(); // Stop music when the player gets the chest
                    gp.canvas.endState = true; // End game when the player does so
                    break;
            }
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch(direction)
        {
            case "up":
                if (spriteNumber == 1)
                {
                    image = up1;
                }

                else if (spriteNumber == 2)
                {
                    image = up2;
                }


                break;

            case "down":
                if (spriteNumber == 1)
                {
                    image = down1;
                }

                else if (spriteNumber == 2)
                {
                    image = down2;
                }

                break;

            case "right":
                if (spriteNumber == 1)
                {
                    image = right1;
                }

                else if (spriteNumber == 2)
                {
                    image = right2;
                }

                break;

            case "left":
                if (spriteNumber == 1)
                {
                    image = left1;
                }

                else if (spriteNumber == 2)
                {
                    image = left2;
                }

                break;

        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
