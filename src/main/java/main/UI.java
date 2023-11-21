package main;

import handler.Handle;

import javax.swing.*;

public class UI implements InputAndOutput {
    @Override
    public void showOutput(String output) {
        JOptionPane.showMessageDialog(null,output);
    }

    @Override
    public String getStringInput(String prompt) {
        return JOptionPane.showInputDialog(prompt);
    }

    @Override
    public int getInput(String prompt) {
        return Integer.parseInt(JOptionPane.showInputDialog(prompt));
    }

    public static void main(String[] args) {
        new Handle(new UI()).start();
    }


}
