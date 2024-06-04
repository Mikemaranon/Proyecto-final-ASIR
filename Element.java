public class Element { 
    protected int[] position = new int[2]; //element's position
    protected int size; //element's dimension

    public Element(int size) {
        this.size = size;
    }

    public void setPosition(int x, int y) {
        position[0] = x; 
        position[1] = y;
    }  
    public int getPosition(int a) {
        return position[a];
    }

    public int getSize() {
        return size;
    }    
}
