package ClassesPackage;

public class CardStructure {
	
	private int id;
	private String name;
	private int attack;
	private int defense;
	private String cardtype;
	
	public CardStructure(int id, String name, int attack, int defense, String cardtype) {
		super();
		this.id = id;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.cardtype = cardtype;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	
	
	
}
