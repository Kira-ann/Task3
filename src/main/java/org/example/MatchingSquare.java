package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MatchingSquare extends JPanel  implements ActionListener{
    private String where = "null";
    private int count = 0;
    public byte goodJob = -1;
    private boolean result = false;
    private Image wrong;
    private Image job;
    private int mouseX;
    private int mouseY;
    private boolean movement;
    private int[] movement2 = new int[2];
    private final int[] posion = new int[2];  // 0 - ось Х; 1 - oсь Y

    private int[] condition = new int[]{0,0,0,0};
    @Override
    public void actionPerformed(ActionEvent e) {}
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
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
            if (key == KeyEvent.VK_ENTER) {
                result = !result;
                where = "null";
            }
            moving();
            comparisonSquare();
            goodJob = -1;
            repaint();
        }
    }
    public class CustomListener implements MouseListener, MouseMotionListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            count++;
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();
            boolean xNull = mouseX > posion[0] && (mouseX < posion[0] + 150);
            boolean yNull = mouseY > posion[1] && mouseY < posion[1] + 300;
            if (xNull && yNull) {
                movement2[0] = mouseX;
                movement2[1] = mouseY;
                movement = !movement;

            }
            if (count % 2 == 0){
                comparisonSquare();
            }
            else goodJob = -1;
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {

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

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();
            if (movement) {
                posion[0] += (mouseX - movement2[0]);
                posion[1] += (mouseY - movement2[1]);
                movement2[0] = mouseX;
                movement2[1] = mouseY;
                repaint();
            }
        }
    }

    public MatchingSquare(){
        setBackground(Color.black);
        newPosion();
        addKeyListener(new FieldKeyListener());
        addMouseMotionListener(new CustomListener());
        addMouseListener(new CustomListener());
        setFocusable(true);
        loadImages();
        moving();
    }
    private void newPosion(){
        posion[0] = 1000;
        posion[1] = 300;
    }
    private void comparisonSquare(){
        condition[2] = posion[1] - 300;
        condition[3] = - posion[1] + 300;
        condition[0] = - posion[0] + 248;
        condition[1] = posion[0] - 248;
        int ans = 0;
        for (int i = 0; i < 4; i++){
            if (condition[i] < 6 && condition[i] > -6){
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
        Color RedColor = new Color(255, 0, 0);
        connectionFirst.setColor(RedColor);
        connectionFirst.fillRect(posion[0], posion[1], 200, 200);
        Color BlueColor = new Color(0, 0, 255);
        connectionFirst.setColor(BlueColor);
        connectionFirst.drawOval(198, 250,300, 300);
        Color white = new Color(255, 255, 255);
        connectionFirst.setColor(white);
        Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 50);
        connectionFirst.setFont(font);
        if (result) {
            for (int j = 0; j < 4; j++) {
                connectionFirst.drawString(name[j] + ":  " +String.valueOf(condition[j]), 900, 100 + j * 70);
            }
        }
        if (goodJob == 1) {
            connectionFirst.drawImage(job, 500, 100, this);
            connectionFirst.drawString("Great job", 590, 160);
        }
        if (goodJob == 0) {
            connectionFirst.drawImage(wrong, 500, 100, this);
            connectionFirst.drawString("Wrong", 590, 160);
        }
    }
    private void moving() {
        if(where.equals("right")){
            posion[0] += 4;
        }
        if(where.equals("left")){
            posion[0] -= 4;
        }
        if(where.equals("up")){
            posion[1] -= 4;
        }
        if(where.equals("down")){
            posion[1] += 4;
        }
    }
    private void loadImages(){
        ImageIcon greatWork = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\goodWork2.png");
        job = greatWork.getImage();
        ImageIcon badJob = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\wrong.png");
        wrong = badJob.getImage();
    }
}

