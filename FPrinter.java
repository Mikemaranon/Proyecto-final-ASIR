public class FPrinter {

	private final static int ROCK_SPRITE_SIDE = 32;
	private final static int ROCK_SPRITE_MASK = ROCK_SPRITE_SIDE - 1;

	private final static int MAP_SPRITE_SIDE = 32;
	private final static int MAP_SPRITE_MASK = MAP_SPRITE_SIDE - 1;

	private final static int PLAYER_SPRITE_SIDE = 28;
	private final static int PLAYER_SPRITE_MASK = MAP_SPRITE_SIDE - 1;

	private int width, height;
	public int[] pixels;
	private int contPlayer = 0;
	private String inputComp = "";

	public FPrinter(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[this.width * this.height];
	}

	public void flushGUI() {
		for(int n = 0; n < pixels.length; n++) {
			pixels[n] = 0;
		}
	}

	public void printMap() {
		for(int y = 0; y < height; y++) {
			if(y < 0 || y >= height) {
				continue;
			}
			for(int x = 0; x < width; x++) {
				if(x < 0 || x >= width) {
					continue;
				}
				pixels[x + y * width] = Sprite.map.pixels[(x & MAP_SPRITE_MASK) + (y & MAP_SPRITE_MASK) * MAP_SPRITE_SIDE];
			}
		}
	}

	public void drawRocks(Obstacle o) {
		for(int y = 0; y < ROCK_SPRITE_SIDE; y++) {
			for(int x = 0; x < ROCK_SPRITE_SIDE; x++) {
				switch(o.type) {
					case 1: if(Sprite.rock_1.pixels[(x) + (y) * ROCK_SPRITE_SIDE] != 0) {
								pixels[(x+o.getPosition(0)) + 
								(invertYaxis(o.getPosition(1))+y) * width] = 
								Sprite.rock_1.pixels[(x & ROCK_SPRITE_MASK) + 
								(y & ROCK_SPRITE_MASK) * ROCK_SPRITE_SIDE];
							} break;
					case 2: if(Sprite.rock_2.pixels[(x) + (y) * ROCK_SPRITE_SIDE] != 0) {
								pixels[(x+o.getPosition(0)) + 
								(invertYaxis(o.getPosition(1))+y) * width] = 
								Sprite.rock_2.pixels[(x & ROCK_SPRITE_MASK) + 
								(y & ROCK_SPRITE_MASK) * ROCK_SPRITE_SIDE];
							} break;
					case 3: if(Sprite.rock_3.pixels[(x) + (y) * ROCK_SPRITE_SIDE] != 0) {
								pixels[(x+o.getPosition(0)) + 
								(invertYaxis(o.getPosition(1))+y) * width] = 
								Sprite.rock_3.pixels[(x & ROCK_SPRITE_MASK) + 
								(y & ROCK_SPRITE_MASK) * ROCK_SPRITE_SIDE];
							} break;
					case 4: if(Sprite.rock_4.pixels[(x) + (y) * ROCK_SPRITE_SIDE] != 0) {
								pixels[(x+o.getPosition(0)) + 
								(invertYaxis(o.getPosition(1))+y) * width] = 
								Sprite.rock_4.pixels[(x & ROCK_SPRITE_MASK) + 
								(y & ROCK_SPRITE_MASK) * ROCK_SPRITE_SIDE];
							} break;
					default: if(Sprite.rock_1.pixels[(x) + (y) * ROCK_SPRITE_SIDE] != 0) {
								pixels[(x+o.getPosition(0)) + 
								(invertYaxis(o.getPosition(1))+y) * width] = 
								Sprite.rock_1.pixels[(x & ROCK_SPRITE_MASK) + 
								(y & ROCK_SPRITE_MASK) * ROCK_SPRITE_SIDE];
							}
				}
			}
		}
	}

	public void detectMovement(Player p, String input) {
		
		if (input != inputComp) {
			contPlayer = 0;
			inputComp = input;
		}
		int filter = contPlayer / (60/4);
	
		if(input.length() <= 1) {
			switch(input) {
	    		case "W": 	switch(filter) {
								case 0: drawPlayer(p, Sprite.Walk_Back_1); break;
								case 1: drawPlayer(p, Sprite.Walk_Back_2); break;
								case 2: drawPlayer(p, Sprite.Walk_Back_3); break;
								case 3: drawPlayer(p, Sprite.Walk_Back_4); break;
								default: drawPlayer(p, Sprite.Walk_Back_1); break;
						  	} break;
	    		case "A": 	switch(filter) {
								case 0: drawPlayer(p, Sprite.Walk_Left_1); break;
								case 1: drawPlayer(p, Sprite.Walk_Left_2); break;
								case 2: drawPlayer(p, Sprite.Walk_Left_3); break;
								case 3: drawPlayer(p, Sprite.Walk_Left_4); break;
								default: drawPlayer(p, Sprite.Walk_Left_1); break;
							} break;
	    		case "S": 	switch(filter) {
								case 0: drawPlayer(p, Sprite.Walk_Front_1); break;
								case 1: drawPlayer(p, Sprite.Walk_Front_2); break;
								case 2: drawPlayer(p, Sprite.Walk_Front_3); break;
								case 3: drawPlayer(p, Sprite.Walk_Front_4); break;
								default: drawPlayer(p, Sprite.Walk_Front_1); break;
				 			} break;
	    		case "D": 	switch(filter) {
								case 0: drawPlayer(p, Sprite.Walk_Right_1); break;
								case 1: drawPlayer(p, Sprite.Walk_Right_2); break;
								case 2: drawPlayer(p, Sprite.Walk_Right_3); break;
								case 3: drawPlayer(p, Sprite.Walk_Right_4); break;
								default: drawPlayer(p, Sprite.Walk_Right_1); break;
							} break;
	            case "": 	switch(p.lastMovement) {
								case 0: drawPlayer(p, Sprite.Player_Back); break;
								case 1: drawPlayer(p, Sprite.Player_TopL); break;
								case 2: drawPlayer(p, Sprite.Player_Left); break;
								case 3: drawPlayer(p, Sprite.Player_BotL); break;
								case 4: drawPlayer(p, Sprite.Player_Front); break;
								case 5: drawPlayer(p, Sprite.Player_BotR); break;
								case 6: drawPlayer(p, Sprite.Player_Right); break;
								case 7: drawPlayer(p, Sprite.Player_TopR); break;
								default: drawPlayer(p, Sprite.Player_Front);
							} break;
				default: 	drawPlayer(p, Sprite.Player_Front); break;
	    	}
		} else {
			switch(input) {
				case "AW": case "WA": 
					switch(filter) {
						case 0: drawPlayer(p, Sprite.Walk_TopL_1); break;
						case 1: drawPlayer(p, Sprite.Walk_TopL_2); break;
						case 2: drawPlayer(p, Sprite.Walk_TopL_3); break;
						case 3: drawPlayer(p, Sprite.Walk_TopL_4); break;
						default: drawPlayer(p, Sprite.Walk_TopL_1); break;
					} break;
				case "DW": case "WD": 
					switch(filter) {
						case 0: drawPlayer(p, Sprite.Walk_TopR_1); break;
						case 1: drawPlayer(p, Sprite.Walk_TopR_2); break;
						case 2: drawPlayer(p, Sprite.Walk_TopR_3); break;
						case 3: drawPlayer(p, Sprite.Walk_TopR_4); break;
						default: drawPlayer(p, Sprite.Walk_TopR_1); break;
					} break;
				case "AS": case "SA": 
					switch(filter) {
						case 0: drawPlayer(p, Sprite.Walk_BotL_1); break;
						case 1: drawPlayer(p, Sprite.Walk_BotL_2); break;
						case 2: drawPlayer(p, Sprite.Walk_BotL_3); break;
						case 3: drawPlayer(p, Sprite.Walk_BotL_4); break;
						default: drawPlayer(p, Sprite.Walk_BotL_1); break;
					} break;
				case "DS": case "SD": 
					switch(filter) {
						case 0: drawPlayer(p, Sprite.Walk_BotR_1); break;
						case 1: drawPlayer(p, Sprite.Walk_BotR_2); break;
						case 2: drawPlayer(p, Sprite.Walk_BotR_3); break;
						case 3: drawPlayer(p, Sprite.Walk_BotR_4); break;
						default: drawPlayer(p, Sprite.Walk_BotR_1); break;
					} break;
				default: drawPlayer(p, Sprite.Player_Front); break;
					
			}
		}
	}

public void drawPlayer(Player p, Sprite s) {
	for(int y = 0; y < PLAYER_SPRITE_SIDE; y++) { //28 a 1
		for(int x = 0; x < PLAYER_SPRITE_SIDE; x++) { //1 a 28
			if(invertYaxis(p.getPosition(1))+y < height) { 
				if(s.pixels[(x) + (y) * PLAYER_SPRITE_SIDE] != 0) {
					pixels[(x+p.getPosition(0)) + (invertYaxis(p.getPosition(1))+y) * width] = 
					s.pixels[(x & PLAYER_SPRITE_MASK) + (y & PLAYER_SPRITE_MASK) * PLAYER_SPRITE_SIDE];
				}
			}
			
		}
	}
	if(contPlayer > 59) {
		contPlayer = 0;
	} else {
		contPlayer++;
	}
}
	
	public int invertYaxis(int y) {
        y = height-y;
        return y;
    }
}