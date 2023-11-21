package main;

public class Console implements InputAndOutput{

    @Override
    public void showOutput(String output) {
        System.out.println(output);
    }

    @Override
    public String getStringInput(String prompt) {
        System.out.println(prompt);
        return new java.util.Scanner(System.in).nextLine();
    }

    @Override
    public int getInput(String prompt) {
        System.out.println(prompt);
        return new java.util.Scanner(System.in).nextInt();
    }
}
