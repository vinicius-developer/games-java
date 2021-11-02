package br.com.alura.io;

import br.com.alura.io.games.Forca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println("+      Escolha um jogo      +");
        System.out.println("+++++++++++++++++++++++++++++");

        System.out.println("Forca = 1");

        System.out.print("Insira o número que representa o jogo: ");
        int game = scanner.nextInt();

        switch (game) {
            case 1:
                new Forca();
        }
    }
}
