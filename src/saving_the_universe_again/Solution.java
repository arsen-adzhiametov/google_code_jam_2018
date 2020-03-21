package saving_the_universe_again;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            final int shieldPower = in.nextInt();
            String robotProgram = in.next();
            final int hacks = calculateHacks(shieldPower, robotProgram);
            String response = hacks < 0 ? "IMPOSSIBLE" : String.valueOf(hacks);
            System.out.println("Case #" + i + ": " + response);
        }
    }

    private static int calculateHacks(final int shieldPower, final String robotProgram) {
        int hacks = 0;
        int currentDamage = calculateDamage(robotProgram);
        if (currentDamage <= shieldPower) {
            return hacks;
        }

        if (nothingToHack(robotProgram)) {
            return -1;
        }

        String hackedProgram = robotProgram;
        String nextHackedProgram = null;
        while (calculateDamage(hackedProgram) > shieldPower) {
            nextHackedProgram = hackRobot(hackedProgram);
            if (hackedProgram.equals(nextHackedProgram)){
                return -1;
            } else {
                hackedProgram = nextHackedProgram;
                hacks++;
            }
        }

        return hacks;
    }

    private static String hackRobot(String program) {
        int positionToHack = program.lastIndexOf("CS");
        if (positionToHack < 0) {
            return program;
        }
        String hackedProgram = swapCommand(program, positionToHack);
        return hackedProgram;
    }

    private static String swapCommand(final String program, int positionToHack) {

        char[] commands = program.toCharArray();

        char temp = commands[positionToHack];
        commands[positionToHack] = commands[positionToHack+1];
        commands[positionToHack+1] = temp;

        return new String(commands);
    }

    private static boolean nothingToHack(String robotProgram) {
        return !robotProgram.contains("C");
    }

    private static int calculateDamage(String robotProgram) {
        int damage = 0;
        int power = 1;
        String[] commands = robotProgram.trim().split("");
        for (int i = 0; i < commands.length; i++) {
            if(commands[i].equals("C")) {
                power = power * 2;
            }
            else if (commands[i].equals("S")) {
                damage += power;
            }
        }
        return damage;
    }
}
