import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tecla implements KeyListener{

    public String input = new String();
    
    @Override
    public void keyTyped(KeyEvent e) {
        //vacío
    }
    @Override
    public void keyPressed(KeyEvent e) {
        String keyString = KeyEvent.getKeyText(e.getKeyCode());
        if(!input.contains(keyString)) {
            input = input + keyString;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        String keyString = KeyEvent.getKeyText(e.getKeyCode());

        input = input.replaceAll(keyString,"");
    }
}
