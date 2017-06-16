package Role;

import init.Initial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Card.Card;
import Card.CardSkill;
import Card.Equipment;
import Card.Person;
import Card.XingYunBi;

public class Player {
	public Player(Character character, int hp) {
		this.character = character;
		this.type = "(玩家)"; // AI会重写
	}

	public Player() {
	}

	private Character character; // 玩家选择的人物 ，存放各种方法
	private String type; // 玩家类型，AI或者人,仅用于打印
	private ArrayList<Card> handcard = new ArrayList<Card>(10);// 集合，存储手牌,最多为10张
	private ArrayList<Card> cardList = new ArrayList<Card>(30);// 集合，选择的牌组,为30张
	private ArrayList<Card>  eqiup = new ArrayList<Card>(9);; // 友方随从，最多场上有9个小怪
	private Card tmpCard; // 临时区 每次出牌，临时区都放一下
	private int crystai=1;
	private int maxCrystai=1;
	private int hp=30; // 人物的当前血量，不能超过上限character.life
	private ArrayList<Card> AoMi=new ArrayList<Card>();//储藏奥秘;
	private ArrayList<Card> Equipment = new ArrayList<Card>();//武器
	
	public ArrayList<Card> getEquipment() {
		return Equipment;
	}

	public void setEquipment(ArrayList<Card> equipment) {
		Equipment = equipment;
	}

	public ArrayList<Card> getAoMi() {
		return AoMi;
	}

	public void setAoMi(ArrayList<Card> aoMi) {
		AoMi = aoMi;
	}

	public int getCrystai() {
		return crystai;
	}

	public void setCrystai(int crystai) {
		this.crystai = crystai;
	}

	public int getMaxCrystai() {
		return maxCrystai;
	}

	public void setMaxCrystai(int maxCrystai) {
		this.maxCrystai = maxCrystai;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public ArrayList<Card> getEqiup() {
		return eqiup;
	}

	public void setEqiup(ArrayList<Card> eqiup) {
		this.eqiup = eqiup;
	}


	public Card getTmpCard() {
		return tmpCard;
	}

	public void setTmpCard(Card tmpCard) {
		this.tmpCard = tmpCard;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Card> getHandcard() {
		return handcard;
	}

	public void setHandcard(ArrayList<Card> handcard) {
		this.handcard = handcard;
	}

	public ArrayList<Card> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}


	// 先手初始发的4张牌
	public void giveCard(Player who, int a) {
		System.out.println("发牌给：" + who.getType().toString());
		if("(玩家)".equals(who.getType())){
			for (int i = 0; i < a; i++) {
				ArrayList<Card> tmpc = new ArrayList<Card>();
				tmpc.addAll(0, this.getHandcard());
				tmpc.add(Initial.listCard.get(0));
				System.out.print("[手牌" + (i + 1) + "]" + "\t");
				Initial.listCard.remove(0);
				who.setHandcard(tmpc);
			}
		}else{
			for (int i = 0; i < a; i++) {
				ArrayList<Card> tmpc = new ArrayList<Card>();
				tmpc.addAll(0, this.getHandcard());
				tmpc.add(Initial.listCardAi.get(0));
				System.out.print("[手牌" + (i + 1) + "]" + "\t");
				Initial.listCardAi.remove(0);
				who.setHandcard(tmpc);
		}
		}
		System.out.println();
	}

	public void getluckmoney(Player who) {
		ArrayList<Card> tmpc = new ArrayList<Card>();
		tmpc.addAll(0, this.getHandcard());
		tmpc.add(luckmoney());
		who.setHandcard(tmpc);
	}

	public Card luckmoney() {
		Card luck = new XingYunBi();
		return luck;
	}

	public void turn(Player p, Player toP) throws InterruptedException {
			this.Start(p, toP);
			Thread.sleep(999);
			Initial.line();
			this.AddCard(p, 1, toP);
			Thread.sleep(999);
			Initial.line();
			this.UseCard(p, toP);
			Thread.sleep(999);
			Initial.line();
			this.End(p, toP);
			Thread.sleep(999);	
		
	}
	/**
	 * 换牌
	 */
	public void checkCard() {
		Initial.listCard.size();
		ArrayList<Card> tmpc = new ArrayList<Card>();// 创建临时集合tmpc
		tmpc.addAll(this.getHandcard());//将手牌放到临时区域
		this.getHandcard().removeAll(this.getHandcard());//将手牌全部消除
		int i =0;
		while(true){
			System.out.println("开始选牌，请输入牌号进行换牌,输入0表示确认，您现在的手牌有：");
			for(int m=0;m<tmpc.size();m++){
				System.out.print(m+1+"--"+tmpc.get(m).getName()+"    ");
			}
			Scanner sc = new Scanner(System.in);
			try { // 参数输入需做异常处理
				int r = sc.nextInt();//获取弃掉的手牌
				if(r==0){
					break;
				}else if(tmpc.size()==5&&r==1){
					System.out.println("幸运币不可放回牌库中");
				}else{
					Initial.listCard.add(tmpc.get(r-1));//将移除的手牌放回卡堆
					tmpc.remove(r-1);//将临时卡区的卡牌去掉
					i=i+1;//记录换取的牌数
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(i!=0){//说明玩家已进行换牌
			for(int j=0;j<i;j++){
				int o =new Random().nextInt(30-tmpc.size());
				tmpc.add(Initial.listCard.get(o));//随机在牌库中获取一张牌
				Initial.listCard.remove(o);
			}
		}
		this.setHandcard(tmpc);//将临时区域的牌放回手牌中
	}

	public void Start(Player who, Player towho) {
		System.out.println("   ****** 轮到" + who.getType().toString() + "出牌 ******");
		Initial.line();
		System.out.println("回合开始阶段..." + "\n");
		// 允许使用英雄技能
		this.getCharacter().setSkill(true);
	}
	public void AddCard(Player who, int num, Player towho) {
		// System.out.println("摸牌阶段...");
		System.out.print(who.getType() + "摸牌：");
		for (int i = 0; i < num; i++) {
			ArrayList<Card> tmpc = new ArrayList<Card>();// 创建临时集合tmpc
			tmpc.addAll(0, who.getHandcard()); // 将手牌全部放到临时集合中
			// 牌堆检测，如果没牌了，将废弃牌堆中的牌add进去
			if(who.getType().equals("(玩家)")){
				if (Initial.listCard.size() == 0) {
//					Initial.listCard.addAll(Initial.throwlistCard);
//					Initial.throwlistCard.clear();
					who.setHp(who.getHp()-1);
				}
				tmpc.add(Initial.listCard.get(0)); // 获取牌堆中的第一张，放进临时集合
				System.out.print("[" + Initial.listCard.get(0).getName() + "]"); // 显示摸到的牌
				Initial.listCard.remove(0); // 牌堆移除摸到的牌
				who.setHandcard(tmpc); // 将临时集合tmpc设置为当前手牌
			}else{
				if (Initial.listCardAi.size() == 0) {
//					Initial.listCardAi.addAll(Initial.throwlistCard);
//					Initial.throwlistCard.clear();
					who.setHp(who.getHp()-1);
				}
				tmpc.add(Initial.listCardAi.get(0)); // 获取牌堆中的第一张，放进临时集合
				System.out.print("[" + Initial.listCardAi.get(0).getKey() + "]"); // 显示摸到的牌
				Initial.listCardAi.remove(0); // 牌堆移除摸到的牌
				who.setHandcard(tmpc); // 将临时集合tmpc设置为当前手牌
			}
		}

		System.out.println("\n");
	}
	public void End(Player who,Player towho) {
		Initial.who = !(Initial.who);
		if(this.getMaxCrystai()<10){
			this.setMaxCrystai(this.getMaxCrystai()+1);
			this.setCrystai(this.getMaxCrystai());
		}else{
			this.setCrystai(this.getMaxCrystai());
		}
		List list=who.getEqiup();
		for(int i=0;i<list.size();i++){
			Person p=(Person) list.get(i);
			p.setIsatt(true);//回合结束时将随从变为可以攻击
		}
		System.out.println(this.getCharacter().getName() + "的回合结束");
		System.out.println();
	}
	public void addhandCard(Card c) {
		this.handcard.add(c);
	}

	public void printInfo(Player p,Player top) {
		Initial.line();
		// 打印血量
		this.printHp();
		// 打印手牌
		this.printHandCard(p,top);
		// 打印场上随从
//		for (int i = 0; i < 10; i++) {
//			if (this.getEqiup().get(i) == null) {
//				System.out.print("友方随从" + (i + 1) + ":无" + "；");
//			} else {
//				System.out.print("友方随从" + (i + 1) + ":"
//						+ this.getEqiup().get(i).toString() + "；");
//			}
//		}
		System.out.println();
		Initial.line();
		System.out.println("");
	}

	// 打印血量方法
	public void printHp() {
//		System.out.print(this.toString() + "当前血量：");
//		for (int i = 0; i < this.getCharacter().getHp(); i++) {
//			System.out.print(this.getCharacter().getHp());
//		}
//		System.out.println(); // 打印出血量显示
	}

	// 打印手牌
	public void printHandCard(Player p,Player top) {
		System.out.println(this.getType().toString() + "当前水晶："+this.getCrystai());
		System.out.println("对方血量："+top.getHp());
		System.out.print("对方随从：");
		for(int b=0;b<top.getEqiup().size();b++){
			Person psed=(Person)top.getEqiup().get(b);
			System.out.print(b+1+"--"+top.getEqiup().get(b).getName()+"  "+psed.getCrystai()+psed.getAtt()+psed.getHp()+"  ");
		}
		System.out.println();
		System.out.println("英雄血量："+this.getHp());
		System.out.print("我方随从: ");
		for(int a=0;a<this.eqiup.size();a++){
			Person ps=(Person)this.getEqiup().get(a);
			System.out.print(a+1+"--"+this.eqiup.get(a).getName()+""+this.getEqiup().get(a).getCrystai()+ps.getAtt()+this.getEqiup().get(a).getHp()+"       ");
		}
		System.out.println();
		System.out.println(this.getType().toString() + "当前手牌：");
		for (int i = 0; i < this.getHandcard().size(); i++) {
			if("0".equals(this.getHandcard().get(i).getFlag())){
				Person ps =(Person) this.getHandcard().get(i);
				System.out.print((i + 1) + "--" + "["
						+ ps.getName() +ps.getCrystai()+ps.getAtt()+ this.getHandcard().get(i).getHp()+"]" + " \t ");
			}else if("1".equals(this.getHandcard().get(i).getFlag())){
				CardSkill cs=(CardSkill) this.getHandcard().get(i);
				System.out.print((i + 1) + "--" + "["
						+ cs.getName() +cs.getCrystai()+cs.getAttr()+"]" + " \t ");
			}else{
				System.out.print((i + 1) + "--" + "["
						+ this.getHandcard().get(i).getName() +this.getHandcard().get(i).getCrystai()+"]" + " \t ");
			}
		}
		System.out.println();
		System.out.println("当前牌组还剩"+Initial.listCard.size()+"张");
		System.out.println();
	}
	public void UseCard(Player p, Player toP){
		
		this.getCharacter().UseCard(p,toP);
	}
}
