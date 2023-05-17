package org.example;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
public class MouseEvents extends Applet implements MouseListener, MouseMotionListener {
    String msg = "";
    int mouseX = 0, mouseY = 0; // координаты курсора мыши

    public void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*mouseX = e.getX();
            mouseY = e.getY();
            System.out.println(movement[0]);
            if (mouseX > posion[0][0] && (mouseX < posion[0][0] + 150) && mouseY > posion[0][1] && mouseY < posion[0][1] + 300) {
                movement2[0][0] = mouseX;
                movement2[0][1] = mouseY;
                System.out.println(movement[0]);
            }
            if (mouseX > posion[1][0] && (mouseX < posion[1][0] + 150) && mouseY > posion[1][1] && mouseY < posion[1][1] + 300) {
                movement2[1][0] = mouseX;
                movement2[1][1] = mouseY;
            }*/
    }
    // обработать событие нажатия кнопки мыши
    public void mousePressed(MouseEvent me) {
        // сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Down"; // Кнопка мыши нажата
        System.out.println("Туту");
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    // обработать событие отпускания кнопки мыши


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    // обработать событие перемещения мыши
    public void mouseMoved(MouseEvent me) {
        /*mouseX = e.getX();
            mouseY = e.getY();
            if (movement[0]) {
                posion[0][0] = mouseX - movement2[0][0];
                posion[0][1] = mouseY - movement2[0][1];
                repaint();
            }
            if (movement[1]) {
                posion[1][0] += mouseX - movement2[1][0];
                posion[1][1] += mouseY - movement2[1][1];
                repaint();
            }*/
    }

    public void paint(Graphics g) {
        g.drawString(msg, mouseX, mouseY);
    }
}
