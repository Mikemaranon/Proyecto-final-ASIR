import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
	private int height, width; 
	public int pixels[];

	//sprite sheet collection
	public static SpriteSheet ground = new SpriteSheet(
		"/game-src/32px/Map/Map_Tiles.png", 32, 32);
	public static SpriteSheet rocks = new SpriteSheet(
		"/game-src/32px/Map/Rock_Sprites.png", 32, 128);
	public static SpriteSheet Player_Stop = new SpriteSheet(
		"/game-src/32px/Player/Player_Sprites.png", 28, 224);
	public static SpriteSheet Walk_Back = new SpriteSheet(
		"/game-src/32px/Walk/Walk_Back/Back_Sprites.png", 28, 112);
	public static SpriteSheet Walk_BotL = new SpriteSheet(
		"/game-src/32px/Walk/Walk_BotL/BotL_Sprites.png", 28, 112);
	public static SpriteSheet Walk_BotR = new SpriteSheet(
		"/game-src/32px/Walk/Walk_BotR/BotR_Sprites.png", 28, 112);
	public static SpriteSheet Walk_Front = new SpriteSheet(
		"/game-src/32px/Walk/Walk_Front/Front_Sprites.png", 28, 112);
	public static SpriteSheet Walk_Left = new SpriteSheet(
		"/game-src/32px/Walk/Walk_Left/Left_Sprites.png", 28, 112);
	public static SpriteSheet Walk_Right = new SpriteSheet(
		"/game-src/32px/Walk/Walk_Right/Right_Sprites.png", 28, 112);
	public static SpriteSheet Walk_TopL = new SpriteSheet(
		"/game-src/32px/Walk/Walk_TopL/TopL_Sprites.png", 28, 112);
	public static SpriteSheet Walk_TopR = new SpriteSheet(
		"/game-src/32px/Walk/Walk_TopR/TopR_Sprites.png", 28, 112);

	public SpriteSheet(String spriteSRC, int height, int width){
		this.width = width;
		this.height = height;
		pixels = new int[height * width];
        try {
            BufferedImage src = ImageIO.read(SpriteSheet.class.getResource(spriteSRC));
            src.getRGB(0, 0, this.width, this.height, pixels, 0, this.width);
        } catch(IOException e) {
            e.printStackTrace();
        }
        
	}

	public int getWidth() {
		return width;
	}
}
