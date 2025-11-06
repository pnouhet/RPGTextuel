package Entities;

public class Player extends Entity{

	public enum PlayerClass { CHEVALIER, ARCHER, MAGE, BARBARE }
	
	private PlayerClass playerClass;
	private int x, y;
	private int xp;
	private int gold;
	private int mana;
	private int maxHp;
	
	//Constructeur
	public Player(String name, PlayerClass playerClass) {
		// maxHp, attack, defense
		super(name, 100, 10, 5);
		this.playerClass = playerClass;
		this.xp = 0;
		this.gold = 0;
		this.x = 0;
		this.y = 0;
		
		//Stats différentes en fonction de la classe
		if(playerClass == PlayerClass.ARCHER) {
			this.setMaxHp(90);
			this.hp = 90;
			this.attack = 11;
			this.defense = 4;
		} else if (playerClass == PlayerClass.BARBARE) {
			this.setMaxHp(110);
			this.hp = 110;
			this.attack = 12;
			this.defense = 3;
		} else if (playerClass == PlayerClass.CHEVALIER) {
			this.setMaxHp(100);
			this.defense = 10;
		} else if (playerClass == PlayerClass.MAGE) {
			this.setMaxHp(100);
			this.attack = 8;
			this.defense = 8;
			this.mana = 50;
		}
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	public void setPos(Player player, int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getGold(int g)
	{
		return gold;
	}
	
	public int getXp(int xp)
	{
		return xp;
	}
	
	public PlayerClass getPlayerClass()
	{
		return playerClass;
	}
	
	public int getMana()
	{
		return mana;
	}
}
