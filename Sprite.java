public class Sprite {
    private final int size;
    private int x, y;

    public int[] pixels;
    //declare a unidimensional array because its faster to operate.
    //we need to divide the sprite in different rows and locate one
    //after another in order to access them in a correct way
    //constructor has the process 

    //sprite collection
    public static Sprite map = new Sprite(32, 0, 0, SpriteSheet.ground);
        //ROCKS
    public static Sprite rock_1 = new Sprite(32, 0, 0, SpriteSheet.rocks);
    public static Sprite rock_2 = new Sprite(32, 1, 0, SpriteSheet.rocks);
    public static Sprite rock_3 = new Sprite(32, 2, 0, SpriteSheet.rocks);
    public static Sprite rock_4 = new Sprite(32, 3, 0, SpriteSheet.rocks);
        //PLAYER_STOP
    public static Sprite Player_Back = new Sprite(28, 0, 0, SpriteSheet.Player_Stop);
    public static Sprite Player_BotL = new Sprite(28, 1, 0, SpriteSheet.Player_Stop);
    public static Sprite Player_BotR = new Sprite(28, 2, 0, SpriteSheet.Player_Stop);
    public static Sprite Player_Front = new Sprite(28, 3, 0, SpriteSheet.Player_Stop);
    public static Sprite Player_Left = new Sprite(28, 4, 0, SpriteSheet.Player_Stop);
    public static Sprite Player_Right = new Sprite(28, 5, 0, SpriteSheet.Player_Stop);
    public static Sprite Player_TopL = new Sprite(28, 6, 0, SpriteSheet.Player_Stop);
    public static Sprite Player_TopR = new Sprite(28, 7, 0, SpriteSheet.Player_Stop);
        //WALK_BACK
    public static Sprite Walk_Back_1 = new Sprite(28, 0, 0, SpriteSheet.Walk_Back);
    public static Sprite Walk_Back_2 = new Sprite(28, 1, 0, SpriteSheet.Walk_Back);
    public static Sprite Walk_Back_3 = new Sprite(28, 2, 0, SpriteSheet.Walk_Back);
    public static Sprite Walk_Back_4 = new Sprite(28, 3, 0, SpriteSheet.Walk_Back);
        //WALK_BOTL
    public static Sprite Walk_BotL_1 = new Sprite(28, 0, 0, SpriteSheet.Walk_BotL);
    public static Sprite Walk_BotL_2 = new Sprite(28, 1, 0, SpriteSheet.Walk_BotL);
    public static Sprite Walk_BotL_3 = new Sprite(28, 2, 0, SpriteSheet.Walk_BotL);
    public static Sprite Walk_BotL_4 = new Sprite(28, 3, 0, SpriteSheet.Walk_BotL);
        //WALK_BOTR
    public static Sprite Walk_BotR_1 = new Sprite(28, 0, 0, SpriteSheet.Walk_BotR);
    public static Sprite Walk_BotR_2 = new Sprite(28, 1, 0, SpriteSheet.Walk_BotR);
    public static Sprite Walk_BotR_3 = new Sprite(28, 2, 0, SpriteSheet.Walk_BotR);
    public static Sprite Walk_BotR_4 = new Sprite(28, 3, 0, SpriteSheet.Walk_BotR);
        //WALK_FRONT
    public static Sprite Walk_Front_1 = new Sprite(28, 0, 0, SpriteSheet.Walk_Front);
    public static Sprite Walk_Front_2 = new Sprite(28, 1, 0, SpriteSheet.Walk_Front);
    public static Sprite Walk_Front_3 = new Sprite(28, 2, 0, SpriteSheet.Walk_Front);
    public static Sprite Walk_Front_4 = new Sprite(28, 3, 0, SpriteSheet.Walk_Front);
        //WALK_LEFT
    public static Sprite Walk_Left_1 = new Sprite(28, 0, 0, SpriteSheet.Walk_Left);
    public static Sprite Walk_Left_2 = new Sprite(28, 1, 0, SpriteSheet.Walk_Left);
    public static Sprite Walk_Left_3 = new Sprite(28, 2, 0, SpriteSheet.Walk_Left);
    public static Sprite Walk_Left_4 = new Sprite(28, 3, 0, SpriteSheet.Walk_Left);
        //WALK_RIGHT
    public static Sprite Walk_Right_1 = new Sprite(28, 0, 0, SpriteSheet.Walk_Right);
    public static Sprite Walk_Right_2 = new Sprite(28, 1, 0, SpriteSheet.Walk_Right);
    public static Sprite Walk_Right_3 = new Sprite(28, 2, 0, SpriteSheet.Walk_Right);
    public static Sprite Walk_Right_4 = new Sprite(28, 3, 0, SpriteSheet.Walk_Right);
        //WALK_TOPL
    public static Sprite Walk_TopL_1 = new Sprite(28, 0, 0, SpriteSheet.Walk_TopL);
    public static Sprite Walk_TopL_2 = new Sprite(28, 1, 0, SpriteSheet.Walk_TopL);
    public static Sprite Walk_TopL_3 = new Sprite(28, 2, 0, SpriteSheet.Walk_TopL);
    public static Sprite Walk_TopL_4 = new Sprite(28, 3, 0, SpriteSheet.Walk_TopL);
        //WALK_TOPR
    public static Sprite Walk_TopR_1 = new Sprite(28, 0, 0, SpriteSheet.Walk_TopR);
    public static Sprite Walk_TopR_2 = new Sprite(28, 1, 0, SpriteSheet.Walk_TopR);
    public static Sprite Walk_TopR_3 = new Sprite(28, 2, 0, SpriteSheet.Walk_TopR);
    public static Sprite Walk_TopR_4 = new Sprite(28, 3, 0, SpriteSheet.Walk_TopR);


    public Sprite(int size, int col, int row, SpriteSheet sp) {
        this.size = size;
        pixels = new int[this.size * this.size];
        this.x = col*size;
        this.y = row*size;

        for(int yy = 0; yy < size; yy++) {
            for(int xx = 0; xx < size; xx++) {
                pixels[xx + yy * size] = 
                sp.pixels[(xx + this.x)+(yy + this.y) * sp.getWidth()];
            }
        }
    }
}
