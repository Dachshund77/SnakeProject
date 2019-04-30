package sample;

import java.awt.event.KeyEvent;

public class Movement extends Controller {
   public void keyType(KeyEvent event){
   if (event.getKeyCode() == KeyEvent.VK_UP){
      posY++;
   }
   if (event.getKeyCode() == KeyEvent.VK_DOWN){
      posY--;
   }
   if (event.getKeyCode() == KeyEvent.VK_RIGHT){
      posX++;
   }
   if (event.getKeyCode() == KeyEvent.VK_LEFT){
      posX--;
   }

   }
}
