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
		this.type = "(���)"; // AI����д
	}

	public Player() {
	}

	private Character character; // ���ѡ������� ����Ÿ��ַ���
	private String type; // ������ͣ�AI������,�����ڴ�ӡ
	private ArrayList<Card> handcard = new ArrayList<Card>(10);// ���ϣ��洢����,���Ϊ10��
	private ArrayList<Card> cardList = new ArrayList<Card>(30);// ���ϣ�ѡ�������,Ϊ30��
	private ArrayList<Card>  eqiup = new ArrayList<Card>(9);; // �ѷ���ӣ���ೡ����9��С��
	private Card tmpCard; // ��ʱ�� ÿ�γ��ƣ���ʱ������һ��
	private int crystai=1;
	private int maxCrystai=1;
	private int hp=30; // ����ĵ�ǰѪ�������ܳ�������character.life
	private ArrayList<Card> AoMi=new ArrayList<Card>();//���ذ���;
	private ArrayList<Card> Equipment = new ArrayList<Card>();//����
	
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


	// ���ֳ�ʼ����4����
	public void giveCard(Player who, int a) {
		System.out.println("���Ƹ���" + who.getType().toString());
		if("(���)".equals(who.getType())){
			for (int i = 0; i < a; i++) {
				ArrayList<Card> tmpc = new ArrayList<Card>();
				tmpc.addAll(0, this.getHandcard());
				tmpc.add(Initial.listCard.get(0));
				System.out.print("[����" + (i + 1) + "]" + "\t");
				Initial.listCard.remove(0);
				who.setHandcard(tmpc);
			}
		}else{
			for (int i = 0; i < a; i++) {
				ArrayList<Card> tmpc = new ArrayList<Card>();
				tmpc.addAll(0, this.getHandcard());
				tmpc.add(Initial.listCardAi.get(0));
				System.out.print("[����" + (i + 1) + "]" + "\t");
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
	 * ����
	 */
	public void checkCard() {
		Initial.listCard.size();
		ArrayList<Card> tmpc = new ArrayList<Card>();// ������ʱ����tmpc
		tmpc.addAll(this.getHandcard());//�����Ʒŵ���ʱ����
		this.getHandcard().removeAll(this.getHandcard());//������ȫ������
		int i =0;
		while(true){
			System.out.println("��ʼѡ�ƣ��������ƺŽ��л���,����0��ʾȷ�ϣ������ڵ������У�");
			for(int m=0;m<tmpc.size();m++){
				System.out.print(m+1+"--"+tmpc.get(m).getName()+"    ");
			}
			Scanner sc = new Scanner(System.in);
			try { // �������������쳣����
				int r = sc.nextInt();//��ȡ����������
				if(r==0){
					break;
				}else if(tmpc.size()==5&&r==1){
					System.out.println("���˱Ҳ��ɷŻ��ƿ���");
				}else{
					Initial.listCard.add(tmpc.get(r-1));//���Ƴ������ƷŻؿ���
					tmpc.remove(r-1);//����ʱ�����Ŀ���ȥ��
					i=i+1;//��¼��ȡ������
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(i!=0){//˵������ѽ��л���
			for(int j=0;j<i;j++){
				int o =new Random().nextInt(30-tmpc.size());
				tmpc.add(Initial.listCard.get(o));//������ƿ��л�ȡһ����
				Initial.listCard.remove(o);
			}
		}
		this.setHandcard(tmpc);//����ʱ������ƷŻ�������
	}

	public void Start(Player who, Player towho) {
		System.out.println("   ****** �ֵ�" + who.getType().toString() + "���� ******");
		Initial.line();
		System.out.println("�غϿ�ʼ�׶�..." + "\n");
		// ����ʹ��Ӣ�ۼ���
		this.getCharacter().setSkill(true);
	}
	public void AddCard(Player who, int num, Player towho) {
		// System.out.println("���ƽ׶�...");
		System.out.print(who.getType() + "���ƣ�");
		for (int i = 0; i < num; i++) {
			ArrayList<Card> tmpc = new ArrayList<Card>();// ������ʱ����tmpc
			tmpc.addAll(0, who.getHandcard()); // ������ȫ���ŵ���ʱ������
			// �ƶѼ�⣬���û���ˣ��������ƶ��е���add��ȥ
			if(who.getType().equals("(���)")){
				if (Initial.listCard.size() == 0) {
//					Initial.listCard.addAll(Initial.throwlistCard);
//					Initial.throwlistCard.clear();
					who.setHp(who.getHp()-1);
				}
				tmpc.add(Initial.listCard.get(0)); // ��ȡ�ƶ��еĵ�һ�ţ��Ž���ʱ����
				System.out.print("[" + Initial.listCard.get(0).getName() + "]"); // ��ʾ��������
				Initial.listCard.remove(0); // �ƶ��Ƴ���������
				who.setHandcard(tmpc); // ����ʱ����tmpc����Ϊ��ǰ����
			}else{
				if (Initial.listCardAi.size() == 0) {
//					Initial.listCardAi.addAll(Initial.throwlistCard);
//					Initial.throwlistCard.clear();
					who.setHp(who.getHp()-1);
				}
				tmpc.add(Initial.listCardAi.get(0)); // ��ȡ�ƶ��еĵ�һ�ţ��Ž���ʱ����
				System.out.print("[" + Initial.listCardAi.get(0).getKey() + "]"); // ��ʾ��������
				Initial.listCardAi.remove(0); // �ƶ��Ƴ���������
				who.setHandcard(tmpc); // ����ʱ����tmpc����Ϊ��ǰ����
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
			p.setIsatt(true);//�غϽ���ʱ����ӱ�Ϊ���Թ���
		}
		System.out.println(this.getCharacter().getName() + "�ĻغϽ���");
		System.out.println();
	}
	public void addhandCard(Card c) {
		this.handcard.add(c);
	}

	public void printInfo(Player p,Player top) {
		Initial.line();
		// ��ӡѪ��
		this.printHp();
		// ��ӡ����
		this.printHandCard(p,top);
		// ��ӡ�������
//		for (int i = 0; i < 10; i++) {
//			if (this.getEqiup().get(i) == null) {
//				System.out.print("�ѷ����" + (i + 1) + ":��" + "��");
//			} else {
//				System.out.print("�ѷ����" + (i + 1) + ":"
//						+ this.getEqiup().get(i).toString() + "��");
//			}
//		}
		System.out.println();
		Initial.line();
		System.out.println("");
	}

	// ��ӡѪ������
	public void printHp() {
//		System.out.print(this.toString() + "��ǰѪ����");
//		for (int i = 0; i < this.getCharacter().getHp(); i++) {
//			System.out.print(this.getCharacter().getHp());
//		}
//		System.out.println(); // ��ӡ��Ѫ����ʾ
	}

	// ��ӡ����
	public void printHandCard(Player p,Player top) {
		System.out.println(this.getType().toString() + "��ǰˮ����"+this.getCrystai());
		System.out.println("�Է�Ѫ����"+top.getHp());
		System.out.print("�Է���ӣ�");
		for(int b=0;b<top.getEqiup().size();b++){
			Person psed=(Person)top.getEqiup().get(b);
			System.out.print(b+1+"--"+top.getEqiup().get(b).getName()+"  "+psed.getCrystai()+psed.getAtt()+psed.getHp()+"  ");
		}
		System.out.println();
		System.out.println("Ӣ��Ѫ����"+this.getHp());
		System.out.print("�ҷ����: ");
		for(int a=0;a<this.eqiup.size();a++){
			Person ps=(Person)this.getEqiup().get(a);
			System.out.print(a+1+"--"+this.eqiup.get(a).getName()+""+this.getEqiup().get(a).getCrystai()+ps.getAtt()+this.getEqiup().get(a).getHp()+"       ");
		}
		System.out.println();
		System.out.println(this.getType().toString() + "��ǰ���ƣ�");
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
		System.out.println("��ǰ���黹ʣ"+Initial.listCard.size()+"��");
		System.out.println();
	}
	public void UseCard(Player p, Player toP){
		
		this.getCharacter().UseCard(p,toP);
	}
}
