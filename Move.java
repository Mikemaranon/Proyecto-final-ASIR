public interface Move {
    public int[] vector = new int[2];
    public static int speed = 1;

    public void Movement(int x, int y);
    public int getVector(int a);
}
 