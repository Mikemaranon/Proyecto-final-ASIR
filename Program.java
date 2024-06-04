import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Program extends Canvas implements Runnable{

    private static final int WIDTH = 801;
    private static final int HEIGHT = 601;
    private static final int OBS = 20;

    private JFrame v;
    private static Thread thread;
    private Game game = new Game(WIDTH, HEIGHT, OBS); 
    private Tecla t;

    //USER GRAPHICAL INTERFACE ELEMENTS
    private static FPrinter framePrinter;
    private static BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private static int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

    private static volatile boolean running = false;
    private static final String NAME = "Metallum";
    private static int FPS = 0;

    public Program() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        framePrinter = new FPrinter(WIDTH, HEIGHT);
        v = new JFrame(NAME);
        t = new Tecla();
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setLayout(new BorderLayout());
        v.setResizable(false);
        v.add(this, BorderLayout.CENTER);
        v.pack();
        v.setLocationRelativeTo(null);
        v.setVisible(true);
        v.addKeyListener(t);   
    }

    //RUNNABLE METHODS
    public synchronized void ini() {
        running = true;
        thread = new Thread(this, "Program");
        thread.start();
    }
    public synchronized void end() {
        running = false;
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        
        game.getRoomAccess().createEmptyRoom();
        game.getRoomAccess().setInitialPlayerPosition(game.getPlayer());
        game.setObstacles();
        final int NANO_SEC = 1000000000; //1 nanosecond
        final byte MAX_CPS_STR = 100;    
        final byte MAX_CPS_DGN = 70;     
        final byte MAX_PPS = 5;          
        final byte MAX_FPS = 60;         
        final double WAIT_TIME_STR = NANO_SEC / MAX_CPS_STR; 
        final double WAIT_TIME_DGN = NANO_SEC / MAX_CPS_DGN;
        final double WAIT_TIME_PPS = NANO_SEC / MAX_PPS;
        final double WAIT_TIME_FPS = NANO_SEC / MAX_FPS; //0.016 ms

        long timeRef = System.nanoTime(); //inicio del programa
        long contRef = System.nanoTime(); //contador de FPS en la GUI
        long timeLoop;
        double timePassed; //contador de tiempo transcurrido
        double deltaSTR = 0, deltaDGN = 0, deltaPPS = 0, deltaFPS = 0; 
        
        while(running == true) {
            
            timeLoop = System.nanoTime();
            timePassed = timeLoop - timeRef;
            timeRef = timeLoop;
            
            if(deltaSTR < 1) {
                deltaSTR = deltaSTR + timePassed/WAIT_TIME_STR;
            } else {
                if(t.input.length() <= 1) {
                    runGame();
                    deltaSTR--;
                } 
            }
                
            if(deltaDGN < 1) {
                deltaDGN = deltaDGN + timePassed/WAIT_TIME_DGN;
            } else {
                if(t.input.length() >= 2) {
                    runGame();
                    deltaDGN--;
                }
            }

            if(deltaPPS < 1) {
                deltaPPS = deltaPPS + timePassed/WAIT_TIME_PPS;
            } else {
                System.out.println(game.movementDirection);
                clearScreen();
                game.printPos();
                deltaPPS--;
            }

            if(deltaFPS < 1) {
                deltaFPS = deltaFPS + timePassed/WAIT_TIME_FPS;
            } else {
                runFrames();
                deltaFPS--;
            }

            if(System.nanoTime() - contRef > NANO_SEC) {
                v.setTitle(NAME + " || FPS: " + FPS);
                FPS = 0;
                contRef = System.nanoTime();
            }
        }
    }
    
    public void runGame() {
        game.gameInput(t.input);
    }

    public void runFrames() {

        BufferStrategy str = getBufferStrategy();
        if(str == null) {
            createBufferStrategy(3);
            return;
        }
        //getting frame ready
        framePrinter.flushGUI();
        framePrinter.printMap();
        for(int i = 0; i < OBS; i++) {
            if(game.getObstacle(i).exist == true) {
                framePrinter.drawRocks(game.getObstacle(i));
                //System.out.println("debug, obstacle nº: " + i);
            }
        }
        framePrinter.detectMovement(game.getPlayer(), t.input);

        //screen view
        System.arraycopy(framePrinter.pixels, 0, pixels, 0, pixels.length);
        Graphics g = str.getDrawGraphics();

        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        str.show();
        FPS++;
    }
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void main(String args[]) {
        Program prog = new Program();
        prog.ini();
    }
}
