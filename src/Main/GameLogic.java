package Main;

import java.util.Random;
import java.util.Scanner;
import Entities.Player;
import World.Dungeon;

public class GameLogic {

	private static Player player;
	private static Dungeon currentDungeon;
	private static int dungeonLvl = 1; //1. Village abandonné, 2. Plaines dangereuses, 3. Fort Gobelins, 4. Salle du boss
	private static boolean isRunning;
	private static Scanner scanner = new Scanner(System.in);
	private static Random random = new Random();
	
	public static void startGame()
	{
		isRunning = true;
		
		//setupPlayer();
		
		System.out.println("---Prologue---\nVous vous réveillez après plusieurs heures...");
		System.out.println("Votre village vient d'être détruit par une ordes de gobelins\nVos parents, vos amis, vos voisins, plus personnes n'est là...");
		System.out.println("Vous décidez de vous venger et partez à la recherche des gobelins qui ont fait ça.");
		
		currentDungeon = new Dungeon(dungeonLvl, player);
		
		//gameLoop();
		
		System.out.println("Merci d'avoir joué !");
	}
}
