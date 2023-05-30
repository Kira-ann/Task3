package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        // menu
        JFrame frameMenu = new JFrame();
        JButton a = new JButton("Cats");
        a.setBounds(200, 100, 200, 200);
        frameMenu.add(a);
        JButton b = new JButton("Square");
        b.setBounds(600, 100, 200, 200);
        frameMenu.add(b);
        JButton c = new JButton("Chess");
        c.setBounds(1000, 100, 200, 200);
        frameMenu.add(c);
        JButton back = new JButton("back");
        frameMenu.setTitle("Синоптофор");
        frameMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameMenu.setSize(1440, 900);
        frameMenu.setLayout(new BorderLayout());
        frameMenu.setLocation(0, 0);
        frameMenu.setVisible(true);
        //cats
        JFrame frameCats = new JFrame();
        frameCats.setTitle("Синоптофор");
        frameCats.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameCats.setSize(1440, 900);
        frameCats.setLayout(new BorderLayout());
        frameCats.setLocation(0, 0);
        //matSq
        JFrame frameMatSq = new JFrame();
        frameMatSq.setTitle("Синоптофор");
        frameMatSq.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameMatSq.setSize(1440, 900);
        frameMatSq.setLayout(new BorderLayout());
        frameMatSq.setLocation(0, 0);
        //GF
        JFrame frameGF = new JFrame();
        frameGF.setTitle("Синоптофор");
        frameGF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameGF.setSize(1440, 900);
        frameGF.setLayout(new BorderLayout());
        frameGF.setLocation(0, 0);
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMenu.setVisible(false);
                frameCats.setVisible(true);
                frameCats.add(back, BorderLayout.EAST);
                frameCats.add(new Cats());
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMenu.setVisible(false);
                frameMatSq.setVisible(true);
                frameMatSq.add(back, BorderLayout.EAST);
                frameMatSq.add(new MatchingSquare());
            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMenu.setVisible(false);
                frameGF.setVisible(true);
                frameGF.add(back, BorderLayout.EAST);
                frameGF.add(new Chess());
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMenu.setVisible(true);
                frameCats.setVisible(false);
                frameMatSq.setVisible(false);
                frameGF.setVisible(false);
            }
        });
    }
    public static void main(String[] args) {
        new Main();
    }
}