package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameField extends JPanel  implements ActionListener{
    private String where = "null";
    private int count = 0;
    private boolean result = false;
    private byte goodJob = -1;
    private Image wrong;
    private Image job;
    private int mouseX;
    private int mouseY;
    private boolean[] movement = new boolean[8];
    private int[][] movement2 = new int[8][2];
    private boolean[][] condition = new boolean[8][2];
    private Image figureFirst;
    private Image figureSecondly;
    private final int[][] posion = new int[8][2];  // 0 - ось Х; 1 - oсь Y
    private final int[] comparison = new int[8];
    private byte numberFigure = 9;
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
            if (key == KeyEvent.VK_1) {
                numberFigure = 1;
                where = "null";
            }
            if (key == KeyEvent.VK_2) {
                numberFigure = 2;
                where = "null";
            }
            if (key == KeyEvent.VK_3) {
                numberFigure = 3;
                where = "null";
            }
            if (key == KeyEvent.VK_4) {
                numberFigure = 4;
                where = "null";
            }
            if (key == KeyEvent.VK_5) {
                numberFigure = 5;
                where = "null";
            }
            if (key == KeyEvent.VK_6) {
                numberFigure = 6;
                where = "null";
            }
            if (key == KeyEvent.VK_7) {
                numberFigure = 7;
                where = "null";
            }
            if (key == KeyEvent.VK_8) {
                numberFigure = 8;
                where = "null";
            }
            if (key == KeyEvent.VK_ENTER) {
                result = !result;
                where = "null";
            }
            a();
            comparisonChess();
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
            for (int i = 0; i < 8; i++){
                condition[i][0] = mouseX > posion[i][0] && (mouseX < posion[i][0] + 120);
            }
            for (int i = 0; i < 8; i++){
                condition[i][1] = mouseY > posion[i][1] && mouseY < posion[i][1] + 250;
            }
            for (int i = 0; i < 8; i ++){
                if (condition[i][0] && condition[i][1]) {
                    movement2[i][0] = mouseX;
                    movement2[i][1] = mouseY;
                    movement[i] = !movement[i];
                }
            }
            System.out.println(count);
            if (count % 2 == 0 && count >= 16){

                comparisonChess();
            }
            else goodJob = 2;
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
            for (int i = 0; i < 8; i++){
                if (movement[i]){
                    posion[i][0] += (mouseX - movement2[i][0]);
                    posion[i][1] += (mouseY - movement2[i][1]);
                    movement2[i][0] = mouseX;
                    movement2[i][1] = mouseY;
                    repaint();
                }
            }
        }
    }
    public GameField() {
        setBackground(Color.black);
        loadImages();
        newPosion();
        addKeyListener(new FieldKeyListener());
        addMouseMotionListener(new CustomListener());
        addMouseListener(new CustomListener());
        setFocusable(true);
        a();
    }
    private void newPosion(){
        posion[0][0] = 800;
        posion[0][1] = 80;
        for (int i = 1; i < 4; i++){
            posion[i][0] = posion[i-1][0] + 160;
            posion[i][1] = posion[i-1][1];
        }
        posion[4][0] = 800;
        posion[4][1] = 480;
        for (int i = 5; i < 8; i++){
            posion[i][0] = posion[i-1][0] + 160;
            posion[i][1] = posion[i-1][1];
        }
    }
    private void comparisonChess(){
        for (int i = 0; i < 4; i++){
            comparison[i] = 120 - posion[i][1];
        }
        for (int i = 4; i < 8; i++){
            comparison[i] = 508 - posion[i][1];
        }
        int ans = 0;
        for (int i = 0; i < 8; i++){
            if (comparison[i] < 6 && comparison[i] > -6){
                ans++;
            }
        }
        if (ans == 8){
            goodJob = 1;
        }
        else goodJob = 0;

    }
    @Override
    protected void paintComponent(Graphics chess) {
        super.paintComponent(chess);
        Color white = new Color(255, 255, 255);
        chess.setColor(white);
        Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 60);
        chess.setFont(font);
        if(!result) {
            for (int i = 0; i < 4; i++) {
                chess.drawImage(figureFirst, posion[i][0], posion[i][1], this);
            }
            for (int i = 4; i < 8; i++) {
                chess.drawImage(figureSecondly, posion[i][0], posion[i][1], this);
            }
            Color RedColor = new Color(255, 0, 0);
            chess.setColor(RedColor);
            chess.fillRect(80, 370, 640, 40);
            Color BlueColor = new Color(0, 0, 255);
            chess.setColor(BlueColor);
            chess.fillRect(80, 758, 640, 40);
        }
        else {
            chess.setColor(white);
            String[] name = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
            for (int j = 0; j < 8; j++) {
                chess.drawString(name[j] + ":  " +String.valueOf(comparison[j]), 50, 100 + j * 70);
            }
        }
        if (goodJob == 1) {
            chess.setColor(white);
            chess.drawImage(job, 900, 100, this);
            chess.drawString("Great job", 990, 160);
        }
        if (goodJob == 0) {
            chess.setColor(white);
            chess.drawImage(wrong, 900, 100, this);
            chess.drawString("Wrong", 990, 160);
        }
    }

    public void a() {
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

    private void loadImages() {
        ImageIcon king = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\King.png");
        figureFirst = king.getImage();
        ImageIcon queen = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\Queen.png");
        figureSecondly = queen.getImage();
        ImageIcon greatWork = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\goodWork2.png");
        job = greatWork.getImage();
        ImageIcon badJob = new ImageIcon("C:\\Users\\kira1\\IdeaProjects\\TaskThree\\src\\main\\resources\\Images\\wrong.png");
        wrong = badJob.getImage();
    }
}
