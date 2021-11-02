package br.com.alura.io.games;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Forca implements Game{

    private final ArrayList<String> world = new ArrayList<>();

    private final ArrayList<String> hiddenWorld = new ArrayList<>();

    private int attempts = 5;

    public Forca() {
        try {
            this.setWorld();
        } catch (FileNotFoundException $e) {
            $e.printStackTrace();
        }
        this.setHiddenWorld();
        this.start();
    }

    private void setWorld() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("palavras.txt"));

        int numberOfWorlds = scanner.nextInt();

       int randomNumber = this.getRandomNumber(numberOfWorlds, 1);

        for (int i = 0; i < randomNumber; i++) {
            scanner.nextLine();
        }

        String world = scanner.nextLine();

        this.world.addAll(
                Arrays.asList(world.split(""))
        );
    }

    private void setHiddenWorld() {
        int sizeWorld = this.world.size();

        for (int i = 0; i < sizeWorld; i++) {
            this.hiddenWorld.add("_");
        }
    }

    int getRandomNumber(int maximum, int minimun) {
        Random random = new Random();

        return random.nextInt(maximum - minimun) + minimun;
    }

    private int getAttempts() {
        return this.attempts;
    }

    private void showHiddenWorld() {
        showWorld(this.hiddenWorld);
    }

    private void showOriginalWorld() {
        showWorld(this.world);
    }

    private void showWorld(ArrayList<String> world) {
        world.forEach((item) -> {
            System.out.print(item.toUpperCase() + " ");
        });
    }


    private boolean changeUnderscoreToLetter(String letter) {
        boolean hasLetter = false;

        for (int i = 0; i < this.world.size(); i++ ) {
            if(letter.equalsIgnoreCase(this.world.get(i))) {
                this.hiddenWorld.set(i, letter);
                hasLetter = true;
            }
        }

        return hasLetter;
    }

    private boolean hasAttempts() {
        return this.attempts > 0;
    }

    private void removeAttempts() {
        this.attempts--;
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("A palavra é: ");
        this.showHiddenWorld();
        System.out.println();

        boolean noWinGame = true;

        while (noWinGame && this.hasAttempts()) {
            System.out.println("Informe uma letra");
            String letter = sc.next();

            boolean hasLetter = this.changeUnderscoreToLetter(letter);

            if(hasLetter) {
                System.out.println("Essa letra existe na palavra");
            } else {
                System.out.println("Essa letra não existe na palavra");
                this.removeAttempts();
            }

            noWinGame = this.hiddenWorld.contains("_");

            System.out.println("Você ainda tem " + this.getAttempts() + " tentativas");
            this.showHiddenWorld();
            System.out.println();
        }

        if(!this.hasAttempts()) {
            System.out.print("Você perdeu a palavra era: ");
            this.showOriginalWorld();
        } else {
            System.out.print("Você ganhou a palavra era: ");
            this.showHiddenWorld();
        }

    }
}
