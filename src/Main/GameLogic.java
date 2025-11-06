package Main;

import java.util.Scanner;
import Entities.Player;
import Entities.Player.PlayerClass;
import World.Dungeon;
import World.Tile;
import Story.Story;
import Encounters.Encounters;

public class GameLogic {

	public static Player player;
	public static boolean isRunning;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void startGame()
	{
		isRunning = true;
		
		setupPlayer();
		
		System.out.println("---Prologue---\nVous vous réveillez après plusieurs heures...");
		System.out.println("Votre village vient d'être détruit par une ordes de gobelins\nVos parents, vos amis, vos voisins, plus personnes n'est là...");
		System.out.println("Vous décidez de vous venger et partez à la recherche des gobelins qui ont fait ça.");
		
		Encounters.currentDungeon = new Dungeon(Encounters.dungeonLvl, player);
		
		gameLoop();
		
		System.out.println("Merci d'avoir joué !");
	}
	
	private static void gameLoop()
	{
		while(isRunning) {
			Encounters.currentDungeon.displayMap();
			//showPlayerStats();
			
			//Demander l'action
			System.out.println("Choisissez une action : \n(1) Haut\n(2) Bas\n(3) Gauche\n(4) Droite\n(5) Infos " + player.getName());
			System.out.println("(6) Inventaire\n(7) Quitter le jeu");
			int input = scanner.nextInt();
			if(input == 5) {
				showPlayerStats();
			} else if (input == 6) {
				//showPlayerInventory();
			} else if (input == 7) {
				isRunning = false;
			} else {
				processMovement(input);
			}
		}
	}

	private static void setupPlayer()
	{
		boolean nameSet = false;
		boolean classSet = false;
		String name;
		PlayerClass playerClass;
		
		//Choix du nom du joueur
		do {
			System.out.println("Quel est votre nom ?\nEntrez votre nom :");
			name = scanner.next();
			System.out.println("Ton nom est : " + name + ", c'est bien ça ?");
			System.out.println("(1) Oui !\n(2) Non, je veux changer de nom");
			int input = scanner.nextInt();
			if(input == 1) {
				nameSet = true;
			}
		} while (!nameSet);
		
		//Choix de la classe
		do {
			System.out.println(name + ", choisissez votre classe :");
			System.out.println("(1) Chevalier (Défense +)");
			System.out.println("(2) Archer (PV-, Attaque+, Défense-");
			System.out.println("(3) Barbare (PV+, Attaque+, Défense--");
			System.out.println("(4) Mage (PV-, Attaque-, Défense++, Mana");
			
			int choice = scanner.nextInt();
			
			//Définir le choix de la classe en fonction de l'entier entrer
			switch(choice) {
				case 1: playerClass = PlayerClass.CHEVALIER; break;
				case 2: playerClass = PlayerClass.ARCHER; break;
				case 3: playerClass = PlayerClass.BARBARE; break;
				case 4: playerClass = PlayerClass.MAGE; break;
				default: playerClass = PlayerClass.CHEVALIER;
			}
			System.out.println("Vous avez choisi la classe " + playerClass + "!");
			System.out.println("Confirmez-vous ce choix ? \n(1) Oui !\n(2) Non, je veux changer de classe !");
			int input = scanner.nextInt();
			if(input == 1) {
				classSet = true;
			}
		} while (!classSet);
		player = new Player(name, playerClass);
		Story.Intro();
	}
	
	private static void showPlayerStats() {
		System.out.println("---");
		System.out.println(player.getName() + " | " + player.getPlayerClass());
		System.out.println(player.getName() + " | PV: " + player.getHp() + "/" + player.getMaxHp() + " | EXP: " + player.getXp(0) + " | Pièces: " + player.getGold(0));
		if (player.getPlayerClass() == PlayerClass.MAGE) {
			System.out.println("Mana: " + player.getMana());
		}
	}

	private static void processMovement(int input) {
		int newX = player.getX();
		int newY = player.getY();
		
		input = scanner.nextInt();
		switch (input){
			case 1: newX--; break; //newX--
			case 2: newX++; break; //newX++
			case 3: newY--; break; //newY--
			case 4: newY++; break; //newY++
			default: System.out.println("Entrez un choix entre 1 et 7.");
			return;
		}
		
		//Verifier bords de la map
		if(newX < 0 || newX >= 12 || newY < 0 || newY >= 12) {
			System.out.println("Vous ne pouvez avancé dans le mur.");
			return;
		}
		
		//Verifier la case où le joueur veut aller
		Tile targetTile = Encounters.currentDungeon.getTileAt(newX, newY);
		
		switch(targetTile) {
			case Tile.EMPTY: 
				System.out.println("Vous avancez d'une case");
				player.setPos(player, newX, newY);
				break;
			case Tile.OBSTACLE:
				Encounters.handleObstacle(player, newX, newY);
				break;
			case Tile.MONSTER:
				Encounters.handleMonster(player, newX, newY);
				break;
			case Tile.SHOP:
				Encounters.handleShop();
				player.setPos(player, newX, newY);
				break;
			case Tile.EXIT:
				Encounters.handleExit(player);
				break;
		}
		
	}

	/*
	private static void handleExit() {
		
	}

	private static void handleMonster(int newX, int newY) {
		
	}

	private static void handleObstacle(int newX, int newY) {
		
	}
	*/
	
}
