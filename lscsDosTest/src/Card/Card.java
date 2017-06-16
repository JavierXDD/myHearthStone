package Card;

import Role.Character;
import Role.Player;
import Role.PlayerAI;


public  class Card {
	public  Card(){
	}
	public Card (String name,String id,String key,int crystai){
		this.name=name;
		this.id=id;
		this.key=key;
		this.crystai=crystai;
	}
	private String name;
	private String id;
	private String key;
	private String flag;//判断是法术还是随从0代表仆从，1代表法术
	private int crystai;
	private int hp;
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getCrystai() {
		return crystai;
	}
	public void setCrystai(int crystai) {
		this.crystai = crystai;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public  boolean cardused(Card a,Card b,Player c,Player d,String z){
		return true;
	};
	
}
