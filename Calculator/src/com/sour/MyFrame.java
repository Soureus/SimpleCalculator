package com.sour;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class MyFrame extends JFrame implements ActionListener {

    double num1;
    double num2;
    double result;
    String op = "";

    JPanel buttonPanel;
    JLabel topLabel;

    Border labelBorder = BorderFactory.createLineBorder(Color.lightGray, 3);
    Border margin  = new EmptyBorder(10,10,10,10);


    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");
    JButton button0 = new JButton("0");

    JButton decButton = new JButton(".");
    JButton equalsButton = new JButton("=");
    JButton plusButton = new JButton("+");
    JButton minusButton = new JButton("-");
    JButton divideButton = new JButton("/");
    JButton timeButton = new JButton("*");

    JButton deleteButton = new JButton("←");
    JButton resetButton = new JButton("C");


    JButton[] numbers = {button0, button1,button2,button3,button4, button5, button6, button7, button8, button9,};
    JButton[] functions = {decButton, equalsButton, plusButton, minusButton, divideButton, timeButton};

    MyFrame(){

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(20,20,20));

        topLabel = new JLabel("");
        topLabel.setBorder(new CompoundBorder(labelBorder, margin));
        topLabel.setBackground(new Color(20,20,20));
        topLabel.setForeground(Color.white);
        topLabel.setFont(new Font(null, Font.PLAIN, 25));
        topLabel.setBounds(5,5,200,50);
        topLabel.setVerticalAlignment(0);
        topLabel.setOpaque(false);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(5, 60, 200, 170);
        buttonPanel.setBackground(new Color(20,20,20));
        buttonPanel.setLayout(new GridLayout(4,4,2,2));

        deleteButton.setBounds(7,240, 97, 35);
        deleteButton.setFocusable(false);
        deleteButton.setFont(new Font(null, Font.PLAIN, 25));
        deleteButton.setBackground(new Color(40,40,40));
        deleteButton.setForeground(Color.white);
        deleteButton.addActionListener(this);


        resetButton.setBounds(107,240,97,35 );
        resetButton.setFocusable(false);
        resetButton.setBackground(new Color(40,40,40));
        resetButton.setForeground(Color.white);
        resetButton.addActionListener(this);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(plusButton);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(minusButton);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        buttonPanel.add(timeButton);
        buttonPanel.add(button0);
        buttonPanel.add(decButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(divideButton);

        //adds ActionListeners to all buttons
        for(int i =0;i<6;i++) {
            functions[i].addActionListener(this);
            functions[i].setFocusable(false);
            functions[i].setBackground(new Color(40,40,40));
            functions[i].setForeground(Color.white);
        }

        equalsButton.setBackground(new Color(0, 65, 65));
        decButton.setBackground(Color.BLACK);

        for(int i =0;i<10;i++) {
            numbers[i].addActionListener(this);
            numbers[i].setFocusable(false);
            numbers[i].setBackground(Color.black);
            numbers[i].setForeground(Color.white);
        }


        this.add(resetButton);
        this.add(deleteButton);
        this.add(topLabel);
        this.add(buttonPanel);
        this.setSize(225,320);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //for typing numbers
        for (int i = 0; i <10; i++) {
            if (e.getSource() == numbers[i]){
                topLabel.setText(topLabel.getText().concat(String.valueOf(i)));
            }
        }

        //for decimal
        if (e.getSource() == functions[0]){
            topLabel.setText(topLabel.getText().concat("."));
        }

        //for delete
        if (e.getSource() == deleteButton) {
            String text = topLabel.getText();
            topLabel.setText("");
            for (int i = 0; i <text.length() - 1; i++) {
                topLabel.setText(topLabel.getText() + text.charAt(i));
            }
        }

        //for reset
        if (e.getSource() == resetButton){

            op = "";
            topLabel.setText("");

        }

        //for choosing operator
        if (e.getSource()== functions[2] || e.getSource()== functions[3] || e.getSource()== functions[4] || e.getSource()== functions[5]){

            num1 = Double.parseDouble(topLabel.getText());

                for (int i = 2; i < 6; i++) {
                    if (e.getSource() == functions[i]) {
                        switch (functions[i].getText()) {
                            case "+":
                                op = "+";
                                i = 6;
                                break;
                            case "-":
                                op = "-";
                                i = 6;
                                break;
                            case "/":
                                op = "/";
                                i = 6;
                                break;
                            case "*":
                                op = "*";
                                i = 6;
                                break;
                        }
                        topLabel.setText("");
                        System.out.println("Operator: " + op);
                    }
                }
        }

        //for equals button
        if (e.getSource() == equalsButton){
            num2 = Double.parseDouble(topLabel.getText());
            switch (op){
                case "+" : result = num1 + num2;
                break;
                case "-" : result = num1 - num2;
                break;
                case "/" : result = num1 / num2;
                break;
                case "*" : result = num1 * num2;
                break;
            }
            topLabel.setText(String.valueOf(result));
            num1 = result;
            op = "";

        }

    }
}
