public class Player extends Element implements Move {
    public Player() {
        super(14);
    }

    public int lastMovement;

    @Override
    public void Movement(int x, int y) { 
        vector[0] = x * speed;
        vector[1] = y * speed;
    }
    @Override
    public int getVector(int a) {
        return vector[a];
    }    
}
