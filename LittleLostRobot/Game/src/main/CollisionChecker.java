package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile(Entity entity)
    {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;

        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;

        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction)
        {
            case "up":

                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileH.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileH.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileH.tile[tileNum1].collision == true || gp.tileH.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }

                break;

            case "down":

                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileH.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileH.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileH.tile[tileNum1].collision == true || gp.tileH.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }

                break;

            case "left":

                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileH.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileH.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileH.tile[tileNum1].collision == true || gp.tileH.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }

                break;

            case "right":

                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileH.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileH.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileH.tile[tileNum1].collision == true || gp.tileH.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }

                break;
        }
    }

    public int trackOBJ(Entity entity, boolean player)
    {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++)
        {
            // This loop will scan the object array

            if (gp.obj[i] != null)
            {
                // If gp.obj[i] is not null, then we will need to know the Entity's solidArea position

                // Get the position for the entity

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the position for the object
                // While the solidArea for the x and y values are set to 0, they are included so the code still works if they are modified

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                // We now check for the entity's direction. We simulate the entity's movement and check where it is after it has moved

                switch(entity.direction)
                {
                    // In this statement, when "intersects" appears, the game will check for an overlap or collision between the respective solidArea rectangles of the entity and object
                    case "up":
                        entity.solidArea.y -= entity.speed;

                        if (entity.solidArea.intersects(gp.obj[i].solidArea))
                        {
                            if (gp.obj[i].collision == true)
                            {
                                // Track the collision of the object and entity
                                entity.collisionOn = true;
                            }

                            if (player == true)
                            {
                                // If it's the player colliding, then return the index
                                index = i;
                            }

                            // If the collision is by a non-player character, an NPC or monster, then nothing happens
                        }

                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;

                        if (entity.solidArea.intersects(gp.obj[i].solidArea))
                        {
                            if (gp.obj[i].collision == true)
                            {
                                // Track the collision of the object and entity
                                entity.collisionOn = true;
                            }

                            if (player == true)
                            {
                                // If it's the player colliding, then return the index
                                index = 1;
                            }

                            // If the collision is by a non-player character, an NPC or monster, then nothing happens
                        }

                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;

                        if (entity.solidArea.intersects(gp.obj[i].solidArea))
                        {
                            if (gp.obj[i].collision == true)
                            {
                                // Track the collision of the object and entity
                                entity.collisionOn = true;
                            }

                            if (player == true)
                            {
                                // If it's the player colliding, then return the index
                                index = 1;
                            }

                            // If the collision is by a non-player character, an NPC or monster, then nothing happens
                        }

                        break;

                    case "right":
                        entity.solidArea.x += entity.speed;

                        if (entity.solidArea.intersects(gp.obj[i].solidArea))
                        {
                            if (gp.obj[i].collision == true)
                            {
                                // Track the collision of the object and entity
                                entity.collisionOn = true;
                            }

                            if (player == true)
                            {
                                // If it's the player colliding, then return the index
                                index = 1;
                            }

                            // If the collision is by a non-player character, an NPC or monster, then nothing happens
                        }

                        break;
                }

                // The code in lines 100 through 107 repeatedly add to the solidArea values, so we use this block to "reset" those values.

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index; // If player collides with any object, return the index of said object
    }
}
