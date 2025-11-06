package Encounters;

import java.util.Random;
import java.util.Scanner;

import Entities.Monster;
import Entities.Player;
import Main.GameLogic;
import Story.Story;
import World.Dungeon;

public class Encounters {

	//static Player player;
	
	public static Dungeon currentDungeon;
	public static int dungeonLvl = 1; //1. Village abandonné, 2. Plaines dangereuses, 3. Fort Gobelins, 4. Salle du boss
	private static Random random = new Random();
	private static Scanner scanner = new Scanner(System.in);
	
	//Methode pour gérer le combat
	public static void handleMonster(Player player, int x, int y)
	{
		Monster monster = createRandomMonster();
		System.out.println("Vous rencontrez un " + monster.getName() + " ennemi ! Battez-vous !");
		
		while(player.isAlive() && monster.isAlive()) {
			System.out.println("\n---COMBAT---");
			System.out.println(player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp());
			System.out.println(monster.getName() + " HP: " + monster.getHp() + "/" + monster.getMaxHp());
			System.out.println("Que voulez-vous faire ?\n(1) Attaquer\n(2) Fuir le combat");
			int choice = scanner.nextInt();
			int playerDmg = Math.max(1, player.getAttack() - monster.getDefense());
			int monsterDmg = Math.max(1, monster.getAttack() - player.getDefense());
			
			//Logique d'attaque
			if(choice == 1) {
				
				monster.takeDmg(playerDmg);
				System.out.println("Vous infligez " + playerDmg + " dégats à " + monster.getName());
				
				if(monster.isAlive()) {
					player.takeDmg(monsterDmg);
					System.out.println(monster.getName() + " vous inflige " + monsterDmg + " dégats");
				}
			} else if (choice == 2){
				if(random.nextBoolean()) {
					System.out.println("Vous avez réussi à fuir !");
					return;
				} else {
					System.out.println("Vous n'avez pas réussi à fuir le combat...");
					player.takeDmg(monsterDmg);
					System.out.println(monster.getName() + " vous inflige " + monsterDmg + " pendant votre tentative de fuite");
				}
			}
		}
		
		//Resultat combat
		if(!player.isAlive()) {
			System.out.println("Vous êtes mort. Game Over");
			GameLogic.isRunning = false;
		} else {
			System.out.println("Vous avez vaincu " + monster.getName() + "!");
			player.getXp(monster.getXpOnDeath());
			player.getGold(monster.getGoldOnDeath());
			System.out.println("Vous avez gagné " + monster.getXpOnDeath() + " EXP et " + monster.getGoldOnDeath() + " Pièces.");
			player.setPos(player, x, y);
			currentDungeon.clearTile(x, y);
		}
	}

	//Creation de monstre aleatoire en fonction du nv du donjon
	private static Monster createRandomMonster() 
	{
		if(dungeonLvl == 1) {
			return new Monster("Slime", 30, 5, 2, 10, 5);
		} else if (dungeonLvl == 2) {
			if (random.nextBoolean()) {
				return new Monster("Loup", 50, 10, 4, 20, 10);
			} else {
				return new Monster("Meute de Loups", 70, 15, 3, 30, 15);
			}
		} else if (dungeonLvl == 3) {
			return new Monster("Gobelin", 60, 12, 6, 25, 12);
		}
		return new Monster("Slime", 30, 5, 2, 10, 5);
	}

	public static void handleObstacle(Player player, int x, int y) 
	{
		System.out.println("Un rocher vous bloque le chemin");
		System.out.println("Voulez-vous le détruire ?\n(1) Oui\n(2) Non");
		int choice = scanner.nextInt();
		
		if(choice == 1) {
			System.out.println("Vous détruisez le rocher.");
			//Math.random()
			System.out.println("Malheureusement vous vous blessez en détruisant l'obstacle et perdez 5 PV.");
			//Entities.Player player = player.takeDmg();
			currentDungeon.clearTile(x, y);
			player.setPos(player, x, y);
		} else {
			System.out.println("Vous faites demi-tour.");
		}
	}

	public static void handleExit(Player player) 
	{
		System.out.println("BRAVO ! Vous avez trouvé la sortie du Donjon !");
		dungeonLvl++;
		if (dungeonLvl == 2) {
			Story.Act1();
			player.setPos(player, 0, 0);
			currentDungeon = new Dungeon(dungeonLvl, player);
		} else if (dungeonLvl == 3) {
			Story.Act2();
			player.setPos(player, 0, 0);
			currentDungeon = new Dungeon(dungeonLvl, player);
		} else if (dungeonLvl == 4) {
			Story.Act3();
			player.setPos(player, 0, 0);
			currentDungeon = new Dungeon(dungeonLvl, player);
		} else {
			Story.End();
		}
		
	}
	
	public static void handleBoosFight(Player player)
	{
		Monster boss = new Monster("Ogre, Boss du Fort", 300, 30, 10, 1000, 500);
		
		System.out.println("Vous avez vaincu " + boss.getName() + "! Le Fort est libéré !");
		dungeonLvl++;
		handleExit(player);
	}

	public static void handleShop() 
	{
		System.out.println("Vous trouvez un magasin !");		
	}
}
