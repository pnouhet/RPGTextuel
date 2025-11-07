package Story;

import Main.GameLogic;

public class Story {

	public static void Intro()
	{
		System.out.println("\n--- Prologue : L'Ombre et les Cendres ---");
		System.out.println("Vous vous réveillez après plusieurs heures... La douleur vous arrache un gémissement.");
		System.out.println("Votre village, *Bois-Clair*, n'est plus qu'un amas fumant.");
		System.out.println("Vos parents, vos amis, vos voisins... Plus personne. Seule l'odeur âcre de la destruction subsiste.");
		System.out.println("Une trace boueuse mène à un tunnel de fortune. Les responsables ? Une horde de **Gobelins**.");
		System.out.println("Une rage froide vous anime. Vous décidez de vous venger et partez à la recherche de la source de ce mal. \nLe premier pas mène au **Donjon du Village Abandonné**.");
	}

	//Donjon Niveau 1 (Village)
	public static void Act1() {
		System.out.println("\n--- Acte I : Le Chemin de l'Ouest ---");
		System.out.println("Vous émergez du village, le cœur lourd mais la détermination intacte.");
		System.out.println("Sur le cadavre d'un gobelin, vous trouvez une carte rudimentaire indiquant une route vers l'ouest.");
		System.out.println("Elle mentionne les **Plaines de l'Orage**, une zone infestée de bêtes sauvages et de patrouilles d'éclaireurs.");
		System.out.println("Si les Gobelins s'y aventurent, c'est qu'ils ont une base plus loin. Vous reprenez la route, sachant que les plaines ne seront qu'une étape de plus.");
		System.out.println("\n**Prochain Donjon : Plaines Dangereuses (Niveau 2)**.");
	}

	//Donjon Niveau 2 (Plaines)
	public static void Act2() {
		System.out.println("\n--- Acte II : La Muraille de Pierre ---");
		System.out.println("Après avoir traversé les plaines et vaincu ses dangers, vous apercevez une sinistre construction à l'horizon : le **Fort des Crocs Cassés**.");
		System.out.println("C'est une forteresse rudimentaire mais solide, manifestement le repaire principal de la horde. Des bannières gobelines flottent sur les tours.");
		System.out.println("Vous vous infiltrez par une ancienne canalisation. Il n'y aura pas de repos ici. Vous devez trouver et éliminer le chef de cette meute.");
		System.out.println("\n**Prochain Donjon : Fort Gobelins (Niveau 3)**.");
	}

	//Donjon Niveau 3 (Fort)
	public static void Act3() {
		System.out.println("\n--- Acte III : La Chambre de l'Ogre ---");
		System.out.println("Vous avez atteint le centre du Fort. Les gobelins restants s'enfuient en hurlant devant une immense porte de bois renforcée de fer.");
		System.out.println("Au-delà de cette porte se trouve le responsable, la brute qui a mené la destruction du village.");
		System.out.println("Une ombre massive bouge derrière la porte. Préparez-vous. C'est l'heure d'honorer la mémoire de Bois-Clair.");
		System.out.println("\n**Prochain Donjon : Salle du Boss (Niveau 4)**.");
	}

	// Combat contre le Boss Final
	public static void End() {
		System.out.println("\n--- Épilogue : Un silence bien mérité ---");
		System.out.println("L'Ogre s'effondre dans un vacarme assourdissant. Le silence retombe sur le Fort des Crocs Cassés.");
		System.out.println("Vous avez tenu votre promesse. Le mal qui a détruit votre foyer est éradiqué.");
		System.out.println("Alors que le soleil se lève, vous quittez le fort. La vengeance est accomplie, mais le chemin à parcourir est long. Un nouveau monde vous attend.");
		
		System.out.println("\n***FÉLICITATIONS, " + GameLogic.player.getName() + ". VOUS AVEZ TERMINÉ VOTRE QUÊTE.***");
		GameLogic.isRunning = false;
	}
}
