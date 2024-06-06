public class Room {

    //constant values to reference diferent elements
    final int P_NUM = 99;
    final int O_NUM = 1;
    final int W_NUM = 2;
    final int NULL = 0;

    //the room can be any size, this mean it has to
    //be initialized with dynamic variables instead
    //of constant ones
    int xSize, ySize;
    private int[][] dim;

    //define constructor to initialize the object
    public Room(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        dim = new int[ySize][xSize];
    }

    //we need to create a room before starting the game
    public void createEmptyRoom() { 
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                if(y == 0 || x == 0 || y == (ySize-1) || x == (xSize-1)) {
                    dim[y][x] = W_NUM; 
                    System.out.print("["+x+"]["+y+"] = "+dim[y][x]+", ");
                    if(x%10==0 && (y==0 || y==(ySize-1))){
                        System.out.println();
                    }
                } else {
                    dim[y][x] = NULL;
                }
            }
        }
        System.out.println("room created");   
        //debug
        boolean x = true, y = true;
        for(int ya = 0; ya < (ySize-1); ya++) {
            if(dim[ya][0] != W_NUM || dim[ya][xSize-1] != W_NUM) {
                x = false;
            //} else {
                System.out.print("y: "+ ya + " ," );
            }
            
        }
        for(int xa = 0; xa < (xSize-1); xa++) {
            if(dim[0][xa] != W_NUM || dim[ySize-1][xa] != W_NUM) {
                y = false;
            //}  else {
                System.out.print("x: "+ xa + " ," );
            }
        }

        if(x==true) {
            System.out.println("[X] Wall created: " + xSize);
        }
        if(y==true) {
            System.out.println("[Y] Wall created: " + ySize);
        }
    }

    //player will be positioned by default in the room's center
    //xSize + a - size/2 takes exactly the center of the room
    //considering player's hitbox center
    public void setInitialPlayerPosition(Player p) {
        int size = p.getSize();
        int X_POS = (xSize - size*3)/2;
        int Y_POS = (ySize + size*3)/2;
        System.out.println("PLAYER - X: " + X_POS + ", Y: " + Y_POS);
        
        for(int b = 0; b < size*3; b++) {
            for(int a = 0; a < size*3; a++) {
                dim[Y_POS - b][X_POS + a] = P_NUM; 
            }
        }
        //we give the player to this method so that it automatically
        //initializes his position
        p.setPosition(X_POS, Y_POS);
    }

    //invoke this method to create an obstacle in the room
    public void setElement(int xIn, int yIn, Obstacle o) {
        int O_SIZE = o.getSize();
        boolean check = true;
        
        int x = xIn / 32;
        int y = yIn / 32;

        for(int a = 0, b = 31; a < 32; a++, b--) {
            if(dim[y*32 - a][x*32 + a] == O_NUM || 
               dim[y*32 - a][x*32 + b] == O_NUM) { 
                System.out.println("Element collided with other Element!!!");
                System.out.println("skipping this one to avoid problems...");
                check = false; o.exist = false; break;
            }                                                                  
    
            if(dim[y*32 - a][x*32 + a] == P_NUM || 
               dim[y*32 - a][x*32 + b] == P_NUM) {
                System.out.println("Element collided with playerzone!!!");
                System.out.println("skipping this one to avoid problems...");
                check = false; o.exist = false; break;
            }
        }
        if(check == true) {
            x = x*32;
            y = y*32;
            for(int a = 0; a < O_SIZE/1.5; a++) {
                for(int b = 0; b < O_SIZE/1.5; b++) {
                    dim[y + a - 10][x + b] = O_NUM; 
                }
            }
            System.out.println("created: ["+x+"]["+y+"]");
            o.setPosition(x, y);
        }        
    }

    //we need to check the future position before trying 
    //to move the player to avoid collision
    public boolean tryMovement(Player p) {
        int P_SIZE = p.getSize();
        int xMove = p.getPosition(0) + p.getVector(0);
        int yMove = p.getPosition(1) - p.getVector(1);

        if(checkLimits(xMove, yMove, P_SIZE)==true) {
            System.out.println("Player collided with a wall!!! nothing outside.");
            System.out.println("He can't move in this direction...");
            return false;
        }
        for(int a = 0, b = P_SIZE; a < P_SIZE; a++, b--) {
            if(dim[yMove - a][xMove + a] == O_NUM || 
               dim[yMove - a][xMove + b] == O_NUM) {
                System.out.println("Player collided with other Element!!!");
                System.out.println("He can't move in this direction...");
                return false;
            }
            if(dim[yMove - a][xMove + a] == W_NUM || 
               dim[yMove - a][xMove + b] == W_NUM) {
                System.out.println("Player collided with a wall!!!");
                System.out.println("He can't move in this direction...");
                return false;
            }
        }
        /*for(int a = 0; a < P_SIZE; a++) {
            for(int b = 0; b < P_SIZE; b++) {
                dim[yMove - a][xMove + b] = P_NUM; 
            }
        }*/
        //deleteOldPosition(p.getPosition(0), p.getPosition(1), P_SIZE);
        p.setPosition(xMove, yMove);
        return true;        
    }

    //deletes player's old position 
    /*public void deleteOldPosition(int x, int y, int size) {
        for(int a = 0; a < size; a++) {
            for(int b = 0; b < size; b++) {
                dim[y - b][x + a] = NULL; 
            }
        }
    }*/

    //this method will move the player next to the nearest
    //point he was of his destiny if his vector wanted to 
    //move him out of the room's limit
    public boolean checkLimits(int x, int y, int size) {
        for(int a = 0, b = size; a < size -1; a++, b--) {
            if(x + a > xSize || x + a < 0 ||
               y - b > ySize || y - b < 0) {
                return true;
            }
        }
        return false;
    }
}
