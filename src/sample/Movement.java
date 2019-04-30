package sample;

import Application.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement extends Controller implements KeyListener { //TODO can this class be cleaned up?

   public enum Direction{
      UP,
      DOWN,
      LEFT,
      RIGHT
   }

   @Override
   public void keyTyped(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_UP){
         posY++;
      }
      if (e.getKeyCode() == KeyEvent.VK_DOWN){
         posY--;
      }
      if (e.getKeyCode() == KeyEvent.VK_RIGHT){
         posX++;
      }
      if (e.getKeyCode() == KeyEvent.VK_LEFT){
         posX--;
      }
   }

   @Override
   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_UP){
         posY++;
      }
      if (e.getKeyCode() == KeyEvent.VK_DOWN){
         posY--;
      }
      if (e.getKeyCode() == KeyEvent.VK_RIGHT){
         posX++;
      }
      if (e.getKeyCode() == KeyEvent.VK_LEFT){

      }
   }

   @Override
   public void keyReleased(KeyEvent e) {

      if (e.getKeyCode() == KeyEvent.VK_UP){
         posX = posX;
      }
      if (e.getKeyCode() == KeyEvent.VK_DOWN){

      }
   }
}
