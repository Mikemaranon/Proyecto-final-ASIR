import java.util.Random;

public class Game { 

    private Random rd = new Random(System.nanoTime());
    private Room room;
    private Player player = new Player();
    private Obstacle obstacles[];

    private int x, y;
    String movementDirection = "";
    int xSize, ySize;

    public Game(int xSize, int ySize, int size) {
        this.xSize = xSize;
        this.ySize = ySize;
        room = new Room(xSize, ySize);
        obstacles = new Obstacle[size];
    }
    public Obstacle getObstacle(int i) {
        return obstacles[i];
    }
    public Player getPlayer() {
        return player;
    }
    public Room getRoomAccess() {
        return room;
    }

    public void setObstacles() {
        int n = obstacles.length;
        int size = 32;
        for(int a = 0; a < n; a++) {
            obstacles[a] = new Obstacle();
            x = rd.nextInt(xSize - size*2) + size;
            y = rd.nextInt(ySize - size*2) + size;
            System.out.println("debug: "+a+" created at ["+(x/size)*size+"]["+(y/size)*size+"]");
            room.setElement(x, y, obstacles[a]);
            obstacles[a].type = rd.nextInt(4) + 1;
        }
    }
    
    public void printPos() {
        System.out.println("at [" + player.getPosition(0) + "][" + player.getPosition(1) + "]");
    }
    public void gameInput(String s) {
	    if(s.length() <= 1) {
	    	switch(s) {
	    		case "W": player.Movement(0, -1); //UP
                        if(room.tryMovement(player)==true) {
                            movementDirection = "in: "+ s + ". Moving UP";
                        } player.lastMovement = 0;
	    			break;
	    		case "A": player.Movement(-1, 0); //LEFT
                        if(room.tryMovement(player)==true) {
                            movementDirection = "in: "+ s + ". Moving LEFT";
                        } player.lastMovement = 2;
	    			break;
	    		case "S": player.Movement(0, 1); //DOWN
                        if(room.tryMovement(player)==true) {
                            movementDirection = "in: "+ s + ". Moving DOWN";
                        } player.lastMovement = 4;
	    			break;
	    		case "D": player.Movement(1, 0); //RIGHT
                        if(room.tryMovement(player)==true) {
                            movementDirection = "in: "+ s + ". Moving RIGHT";
                        } player.lastMovement = 6;
	    			break;
                case "": movementDirection = "Movement stopped"; break;
                default: movementDirection = "unknown input detected!: " + s;
                	break;
	    	}
	    } else {
	    	switch(s) {
	    		case "WA": case "AW": 
                        player.Movement(-1, -1); //UP-LEFT
                        if(room.tryMovement(player)==true) {
                            movementDirection = "in: "+ s + ". Moving UP-LEFT";
                        } player.lastMovement = 1;
	    			break;
	    		case "WD": case "DW":
                        player.Movement(1, -1); //UP-RIGHT
                        if(room.tryMovement(player)==true) {
                            movementDirection = "in: "+ s + ". Moving UP-RIGHT";
                        } player.lastMovement = 7; 
	    			break;
	    		case "SA": case "AS": 
                        player.Movement(-1, 1); //DOWN-LEFT
                        if(room.tryMovement(player)==true) {
                            movementDirection = "in: "+ s + ". Moving DOWN-LEFT";
                        } player.lastMovement = 5;
	    			break;
	    		case "SD": case "DS": 
                        player.Movement(1, 1); //DOWN-RIGHT
                        if(room.tryMovement(player)==true) {
                            movementDirection = "in: "+ s + ". Moving DOWN-RIGHT";
                        } player.lastMovement = 3;
	    			break;
                default: movementDirection = "unknown input detected!: " + s;
                         player.lastMovement = 4;
                	     break;
	    	}
	    }
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}