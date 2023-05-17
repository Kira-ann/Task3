package org.example;

import javax.swing.*;

public class Main extends JFrame{

    public Main() {

        setTitle("Синоптофор");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1440, 900);
        setLocation(0, 0);
        add(new Cats());
        setVisible(true);
    }
    public static void main(String[] args) {
        Main ExerciseChess = new Main();
    }

}//