package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Cats extends JPanel  implements ActionListener{
    private byte goodJob = -1;
    private Image job;
    private int count = 0;
    private Image wrong;
    private Image redCat;
    private Image blueCat;
    private int mouseY;
    private boolean[] movement = new boolean[2]; // 0 - ось Х; 1 - oсь Y, красная - 0, синяя - 1
    private int[][] movement2 = new int[2][2];
    private int mouseX;
    private String where = "null";
    private boolean result = false;
    private final int[][] posion = new int[][] {{400,250}, {900,250}};  // 0 - ось Х; 1 - oсь Y, красная - 0, синяя - 1

    private byte numberFigure = 2;
    private int[] c = new int[]{0,0,0,0};
    @Override
    public void actionPerformed(ActionEvent e) {}
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("hh");
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                where = "left";
            }
            if (key == KeyEvent.VK_RIGHT) {
                where = "right";
            }
            if (key == KeyEvent.VK_UP) {
                where = "up";
            }
            if (key == KeyEvent.VK_DOWN) {
                where = "down";
            }
            if (key == KeyEvent.VK_1) {
                numberFigure = 1;
                System.out.println("tyty");
                where = "null";
            }
            if (key == KeyEvent.VK_2) {
                numberFigure = 2;
                where = "null";
            }
            if (key == KeyEvent.VK_ENTER) {
                result = !result;
                where = "null";
            }
            a();
            comparisonCats();
            goodJob = -1;
            repaint();
        }
    }
    public class CustomListener implements MouseListener, MouseMotionListener{
        public void mouseClicked(MouseEvent e) {
            count++;
            mouseX = e.getX();
            mouseY = e.getY();
            boolean xNull = mouseX > posion[0][0] && (mouseX < posion[0][0] + 150);
            boolean yNull = mouseY > posion[0][1] && mouseY < posion[0][1] + 300;
            boolean xOne = mouseX > posion[1][0] && (mouseX < posion[1][0] + 150);
            boolean yOne = mouseY > posion[1][1] && mouseY < posion[1][1] + 300;
            if (xOne && yOne && xNull && yNull && (movement[0] || movement[1])){
                movement[0] = false;
                movement[1] = false;
            }
            else if (xNull && yNull) {
                movement2[0][0] = mouseX;
                movement2[0][1] = mouseY;
                movement[0] = !movement[0];
            }
            else if (xOne && yOne) {
                movement2[1][0] = mouseX;
                movement2[1][1] = mouseY;
                movement[1] = !movement[1];
            }
            if (count % 2 == 0) comparisonCats();
            repaint();
        }
        public void mousePressed(MouseEvent e) {

        }
        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseDragged(MouseEvent e) {

        }

        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();
            if (movement[0]) {
                posion[0][0] += (mouseX - movement2[0][0]);
                posion[0][1] += (mouseY - movement2[0][1]);
                movement2[0][0] = mouseX;
                movement2[0][1] = mouseY;
                repaint();
            }
            if (movement[1]) {
                posion[1][0] += mouseX - movement2[1][0];
                posion[1][1] += mouseY - movement2[1][1];
                movement2[1][0] = mouseX;
                movement2[1][1] = mouseY;
                repaint();
            }
        }
    }

    public Cats(){
        setBackground(Color.black);
        loadImages();
        addKeyListener(new FieldKeyListener());
        addMouseMotionListener(new CustomListener());
        addMouseListener(new CustomListener());
        a();
        setFocusable(true);
    }
    private void comparisonCats(){
        c[0] = posion[0][0] - posion[1][0];
        c[1] = posion[1][0] - posion[0][0];
        c[2] = posion[0][1] - posion[1][1];
        c[3] = posion[1][1] - posion[0][1];
        int ans = 0;
        for (int i = 0; i < 4; i++){
            if (c[i] < 6 && c[i] > -6){
                ans++;
            }
        }
        if (ans == 4){
            goodJob = 1;
        }
        else goodJob = 0;

    }
    @Override
    protected void paintComponent(Graphics connectionFirst) {
        String[] name = new String[]{"right", "left", "up", "down"};
        super.paintComponent(connectionFirst);
        connectionFirst.drawImage(redCat, posion[0][0], posion[0][1],this);
        connectionFirst.drawImage(blueCat, posion[1][0], posion[1][1],this);
        if (result) {
            Color write = new Color(255, 255, 255);
            connectionFirst.setColor(write);
            Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 30);
            connectionFirst.setFont(font);
            for (int j = 0; j < 4; j++) {
                connectionFirst.drawString(name[j] + ":  " +String.valueOf(c[j]), 50, 50 + j * 50);
            }
        }
        if (goodJob == 1) {
            connectionFirst.drawImage(job, 500, 100, this);
            Color white = new Color(255, 255, 255);
            connectionFirst.setColor(white);
            Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 50);
            connectionFirst.setFont(font);
            connectionFirst.drawString("Great job", 590, 160);
        }
        if (goodJob == 0) {
            connectionFirst.drawImage(wrong, 500, 100, this);
            Color white = new Color(255, 255, 255);
            connectionFirst.setColor(white);
            Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 50);
            connectionFirst.setFont(font);
            connectionFirst.drawString("Wrong", 590, 160);
        }
    }

    private void a() {
        if(where.equals("right")){
            posion[numberFigure - 1][0] += 4;
        }
        if(where.equals("left")){
            posion[numberFigure -1][0] -= 4;
        }
        if(where.equals("up")){
            posion[numberFigure - 1][1] -= 4;
        }
        if(where.equals("down")){
            posion[numberFigure - 1][1] += 4;
        }
    }
    private void loadImages(){
        ImageIcon red = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\RedCat.png");
        redCat = red.getImage();
        ImageIcon blue = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\BlueCat.png");
        blueCat = blue.getImage();
        ImageIcon greatWork = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\goodWork2.png");
        job = greatWork.getImage();
        ImageIcon badJob = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\wrong.png");
        wrong = badJob.getImage();
    }
}

