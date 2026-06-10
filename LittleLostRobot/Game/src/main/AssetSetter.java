package main;

import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        // We are creating "keys" to manage each object

        gp.obj[0] = new ObjectKey();
        gp.obj[0].worldX = 23 * gp.tileSize; // We're setting the position of this key on the X axis
        gp.obj[0].worldY = 7 * gp.tileSize; // We're setting the position of this key on the Y axis

        gp.obj[1] = new ObjectKey();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new ObjectKey();
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3] = new ObjectDoor();
        gp.obj[3].worldX = 23 * gp.tileSize;
        gp.obj[3].worldY = 40 * gp .tileSize;

        gp.obj[4] = new ObjectDoor();
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 9 * gp.tileSize;

        gp.obj[5] = new ObjectDoor();
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6] = new ObjectChest();
        gp.obj[6].worldX = 11 * gp.tileSize;
        gp.obj[6].worldY = 31 * gp.tileSize;

        gp.obj[7] = new ObjectBoots();
        gp.obj[7].worldX = 16 * gp.tileSize;
        gp.obj[7].worldY = 19 * gp.tileSize;

        gp.obj[8] = new ObjectFruit();
        gp.obj[8].worldX = 25 * gp.tileSize;
        gp.obj[8].worldY = 23 * gp.tileSize;

        gp.obj[9] = new ObjectCorn();
        gp.obj[9].worldX = 38 * gp.tileSize;
        gp.obj[9].worldY = 41 * gp.tileSize;

        gp.obj[10] = new ObjectCorn();
        gp.obj[10].worldX = 39 * gp.tileSize;
        gp.obj[10].worldY = 41 * gp.tileSize;

        gp.obj[11] = new ObjectCorn();
        gp.obj[11].worldX = 40 * gp.tileSize;
        gp.obj[11].worldY = 41 * gp.tileSize;

        gp.obj[12] = new ObjectCorn();
        gp.obj[12].worldX = 39 * gp.tileSize;
        gp.obj[12].worldY = 42 * gp.tileSize;

        gp.obj[13] = new ObjectCorn();
        gp.obj[13].worldX = 39 * gp.tileSize;
        gp.obj[13].worldY = 41 * gp.tileSize;

        gp.obj[14] = new ObjectCorn();
        gp.obj[14].worldX = 39 * gp.tileSize;
        gp.obj[14].worldY = 41 * gp.tileSize;

        gp.obj[15] = new ObjectCorn();
        gp.obj[15].worldX = 40 * gp.tileSize;
        gp.obj[15].worldY = 42 * gp.tileSize;

        gp.obj[16] = new ObjectCorn();
        gp.obj[16].worldX = 40 * gp.tileSize;
        gp.obj[16].worldY = 41 * gp.tileSize;

        gp.obj[17] = new ObjectCorn();
        gp.obj[17].worldX = 43 * gp.tileSize;
        gp.obj[17].worldY = 41 * gp.tileSize;

    }
}
