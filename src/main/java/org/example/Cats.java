package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cats extends JPanel implements ActionListener{
    private byte goodJob = -1;
    private Image job;
    private int count = 0;
    private Image wrong;
    private Image redCat;
    private Image blueCat;
    private int mouseY;
    private final boolean[] movement = new boolean[2]; // 0 - ось Х; 1 - oсь Y, красная - 0, синяя - 1
    private final int[][] movement2 = new int[2][2];
    private int mouseX;
    private String where = "null";
    private boolean result = false;
    public final int[][] position = new int[][] {{400,250}, {900,250}};  // 0 - ось Х; 1 - oсь Y, красная - 0, синяя - 1
    private byte numberFigure = 2;
    private final int[] condition = new int[]{0,0,0,0};
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT){
                System.out.println('y');
                where = "left";
            }
            if(key == KeyEvent.VK_RIGHT){
                where = "right";
            }
            if(key == KeyEvent.VK_UP){
                where = "up";
            }
            if(key == KeyEvent.VK_DOWN){
                where = "down";
            }
            if(key == KeyEvent.VK_1){
                numberFigure = 1;
                where = "null";
            }
            if(key == KeyEvent.VK_2){
                numberFigure = 2;
                where = "null";
            }
            if (key == KeyEvent.VK_ENTER) {
                result = !result;
                where = "null";
            }
            a();
            comparisonCats();
            repaint();
        }
    }

    public class CustomListener implements MouseListener, MouseMotionListener{
        public void mouseClicked(MouseEvent e) {
            count++;
            mouseX = e.getX();
            mouseY = e.getY();
            boolean xNull = mouseX > position[0][0] && (mouseX < position[0][0] + 150);
            boolean yNull = mouseY > position[0][1] && mouseY < position[0][1] + 300;
            boolean xOne = mouseX > position[1][0] && (mouseX < position[1][0] + 150);
            boolean yOne = mouseY > position[1][1] && mouseY < position[1][1] + 300;
            if (xOne && yOne && xNull && yNull && (movement[0] || movement[1])){
                movement[0] = false;
                movement[1] = false;
            }
            else if (xOne && yOne) {
                movement2[1][0] = mouseX;
                movement2[1][1] = mouseY;
                movement[1] = !movement[1];
            }
            else if (xNull && yNull) {
                movement2[0][0] = mouseX;
                movement2[0][1] = mouseY;
                movement[0] = !movement[0];
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
                position[0][0] += (mouseX - movement2[0][0]);
                position[0][1] += (mouseY - movement2[0][1]);
                movement2[0][0] = mouseX;
                movement2[0][1] = mouseY;
                repaint();
            }
            if (movement[1]) {
                position[1][0] += mouseX - movement2[1][0];
                position[1][1] += mouseY - movement2[1][1];
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
        newPosition();
        a();
        setFocusable(true);
    }
    private void newPosition(){
        position[0][0] = 400;
        position[0][1] = 250;
        position[1][0] = 900;
        position[1][1] = 250;
    }
    public void comparisonCats(){     // публичный только для теста
        condition[0] = position[0][0] - position[1][0];
        condition[1] = position[1][0] - position[0][0];
        condition[2] = position[0][1] - position[1][1];
        condition[3] = position[1][1] - position[0][1];
        int ans = 0;
        for (int i = 0; i < 4; i++){
            if (condition[i] < 6 && condition[i] > -6){
                ans++;
                System.out.println(condition[i]);
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
        connectionFirst.drawImage(redCat, position[0][0], position[0][1],this);
        connectionFirst.drawImage(blueCat, position[1][0], position[1][1],this);
        if (result) {
            Color write = new Color(255, 255, 255);
            connectionFirst.setColor(write);
            Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 30);
            connectionFirst.setFont(font);
            for (int j = 0; j < 4; j++) {
                connectionFirst.drawString(name[j] + ":  " +condition[j], 50, 50 + j * 50);
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
            position[numberFigure - 1][0] += 4;
        }
        if(where.equals("left")){
            position[numberFigure -1][0] -= 4;
        }
        if(where.equals("up")){
            position[numberFigure - 1][1] -= 4;
        }
        if(where.equals("down")){
            position[numberFigure - 1][1] += 4;
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
    public byte getGoodJob() {
        return goodJob;
    }
}

