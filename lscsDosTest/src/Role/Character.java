package Role;

import init.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import skill.CharacterAbstractSkill;
import Card.Card;
import Card.Dict;
import Card.Equipment;
import Card.Person;


public abstract class Character implements CharacterAbstractSkill{
	private String name;//Ӣ������
	private int maxhp=30;//Ӣ����Ѫ��
	private int maxCrystai=1;//Ӣ����ˮ��
	private boolean skill;//�Ƿ����Ӣ�ۼ���
	private boolean skipUseCard; // �Ƿ�����غ�
	private int hp; // ����ĵ�ǰѪ�������ܳ�������character.life
	private int Crystai;//Ӣ�۵�ǰˮ��
	private boolean isatt;//�Ƿ���Թ���
	
	public boolean isIsatt() {
		return isatt;
	}
	public void setIsatt(boolean isatt) {
		this.isatt = isatt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxhp() {
		return maxhp;
	}
	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}
	public int getMaxCrystai() {
		return maxCrystai;
	}
	public void setMaxCrystai(int maxCrystai) {
		this.maxCrystai = maxCrystai;
	}

	public boolean isSkill() {
		return skill;
	}
	public void setSkill(boolean skill) {
		this.skill = skill;
	}
	public boolean isSkipUseCard() {
		return skipUseCard;
	}
	public void setSkipUseCard(boolean skipUseCard) {
		this.skipUseCard = skipUseCard;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getCrystai() {
		return Crystai;
	}
	public void setCrystai(int crystai) {
		Crystai = crystai;
	}
	@Override
	public abstract void characterskill(Player who, Player towho,int r);
	@Override
	public void useCard(Character p, Character ai, Card pma, Card aima) {
		System.out.println("���ƽ׶�...");
		
	}
	@Override
	public void endUseCard() {
		// TODO Auto-generated method stub
		if(this.maxCrystai<10){
			this.maxCrystai=this.maxCrystai+1;
		}
		this.skipUseCard=false;
		
	}
	
	public void UseCard(Player who, Player towho) {
		System.out.println("���ƽ׶�...");
		// ��ʾ�����б�
		// �����������
		// ����Ƿ���Ч����Ч�򷵻�
		// ִ������Ӧ�ķ��� switch
		// ѭ����ֱ��ѡ�����ƣ�
		if (who.getType() == "(AI)") {
			// ****����ֵ�AI���˴�����AI�ķ�����****
//			System.out.println("----------------------"+who.getCrystai()+"==========");
			int n = who.getHandcard().size();
			if (n == 0) {
				return;
			}
			List<Card> tmplist=new ArrayList();//��ʱ����
			tmplist.addAll(who.getHandcard());//��ai��ȫ�����Ʒŵ���ʱ��
			tmplist=Sort.sortdesc(tmplist);//����ʱ������
			Card b =null;
			for(int i=0;i<tmplist.size();i++){
				int result=who.getCrystai()-tmplist.get(i).getCrystai();
				if(result>=0){//֤���ܹ�������
//					who.setCrystai(result);//��ˮ��������ÿ������������
					who.setTmpCard(tmplist.get(i));
					ArrayList<Card> tmp = new ArrayList<Card>();
					tmp.addAll(who.getHandcard());
					tmp.remove(tmplist.get(i));
					who.setHandcard(tmp);
					// ����Card��use����
					System.out.println(who.getType().toString() + "���ƣ�"
							+ tmplist.get(i).getName().toString());
					Card a= who.getTmpCard();
					if(towho.getEqiup().size()<1){//���Է�����û�����趨bΪ��
						b =null;
					}else{
						for(int z=0;z<towho.getEqiup().size();z++){
							Person pp=(Person) towho.getEqiup().get(z);
							if(pp.getSpecial().contains(Dict.DERIDe)){//����г�������Ŀ����ǳ�����ӷ���ֱ��Ŀ����
								b=pp;
								break;
							}else{
								b=null;
							}
						}
//						b =towho.getEqiup().get(new Random().nextInt(towho.getEqiup().size()));
					}
					String z ="h";
					if(who.getEqiup().size()>0){//˵��������ڳ���
						int num =new Random().nextInt(who.getEqiup().size())+1;
						z=z+1+num;
					}else if(towho.getEqiup().size()>0){
						int num = new Random().nextInt(towho.getEqiup().size())+1;
						z=z+11+num;
					}
					System.out.println("-----------------------------------"+z+"----------------------------------");
					a.cardused(a, b, who, towho,z);
//					if(("0").equals(a.getFlag())){//����ʶΪ0�������Ʒŵ�����
//						a.cardused(a, b, who, towho,"h");
//						who.getEqiup().add(a);
//					}else{
//						a.cardused(a, b, who, towho,"h");
//					}
				}
			}
			if(who.getCrystai()>=2){//���ж���ˮ�� ����Ӣ�ۼ���
				who.getCharacter().characterskill(who, towho, 2);
			}
			for(int t=0;t<who.getEqiup().size();t++){
				if(towho.getEqiup().size()<1){//���Է�����û�����趨bΪ��
					b =null;
					if (who.getEqiup().get(t).cardused(who.getEqiup().get(t),b, who,towho,"d")) {
						// �ɹ������
						System.out.println();
						try {
							Thread.sleep(999);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					} 
				}else{
					for(int z=0;z<towho.getEqiup().size();z++){
						Person pp=(Person) towho.getEqiup().get(z);
						if(pp.getSpecial().contains(Dict.DERIDe)){//����г�������Ŀ����ǳ�����ӷ���ֱ��Ŀ����
							b=pp;
							break;
						}else{
							b=null;
//							b =towho.getEqiup().get(new Random().nextInt(towho.getEqiup().size()));//
						}
					}
					if (who.getEqiup().get(t).cardused(who.getEqiup().get(t),b, who,towho,"d")) {
						// �ɹ������
						System.out.println();
						try {
							Thread.sleep(999);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					} 
				}
			}
				try {
					Thread.sleep(999);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
			return;
		}
		while (true) { // ��ҳ��ƹ��̣�һֱ���Գ��ƣ�ֱ��ѡ�����ƻ�������

			System.out.println(who.getType().toString() + "����..." + "\n");
			who.printHandCard(who,towho);
			System.out.println("\n"+"�������������"+"\n"+"0Ϊ������pass��-1xΪ�����������ܣ�-2Ϊ���������������ܣ�dXX������ӽ��й�����hXX����������еĿ��ƣ���һ��XΪ���Ƶڼ��ţ��ڶ�������Է����ϵ���ӣ�0����Է�,help��ѯ˫��״̬");
			System.out.print("=>");
			Scanner sc = new Scanner(System.in);
			try { // �������������쳣����
				String z=sc.next();
//				System.out.println(z);
//				int r = sc.nextInt();
				// int��������ʾ��������к�[����������ʾ����ʱ��0��Ϊ���ƣ�������Ŷ�+1��ʾ���ڴ���ʱҪ-1]
				if ("0".equals(z)) { // ����0�غϽ���
					return;
				}
				if ("-1".equals(z.substring(0,2))) {
					if(z.length()==3){
						if(who.getEquipment().size()>0){
							Card equipment =who.getEquipment().get(0);//������ֻ��һ��
							Equipment e= (Equipment)equipment;//ǿת
							e.useEquipment(null,null, who, towho, z);
						}else{
							System.out.println("��û��װ������");
						}
					}else{
						System.out.println("�����������");
					}
				} else if ("-2".equals(z)) {
					if(who.getCharacter().isSkill()){
						who.getCharacter().useSkill(who, towho,Integer.valueOf(z));
					}
				} else if ("help".equals(z)) {
					who.printInfo(who, towho);
					towho.printInfo(who, towho);
				} else if(z.substring(0,1).equals("h")){//����
					Card a = null;
					Card b = null;
					String num=String.valueOf(z).substring(0,1);
					String num1=String.valueOf(z).substring(1,2);	
					if(z.length()==3){
						String num2=String.valueOf(z).substring(2,3);
						if(towho.getEqiup().size()>=Integer.valueOf(num2)){
							int numm2=Integer.valueOf(num2);
							if(numm2!=0){
								b=towho.getEqiup().get(Integer.valueOf(num2)-1);
							}
						}
					}
					if(who.getHandcard().size()>=Integer.valueOf(num1)){
						a=who.getHandcard().get(Integer.valueOf(num1)-1);
					}
					
					// ����Card��cardused����
					a.cardused(a,b,who,towho,z);
					System.out.println();
//					if (a.cardused(a,b,who,towho,z)) {
						// �ɹ���������
//						who.getHandcard().remove(Integer.valueOf(num1)-1);
						//} else {
						// �����ܴ򣬽�Ԥ������ƷŻ�
						//who.addhandCard(who.getTmpCard());
						//System.out.println("�������ڲ��ܳ���");
					//}
				}else if("d".equals(z.substring(0,1))){
					Card a = null;
					Card b = null;
					String num1=String.valueOf(z).substring(1,2);
					String num2=String.valueOf(z).substring(2,3);
					if(who.getEqiup().size()>=Integer.valueOf(num1)){
						a=who.getEqiup().get(Integer.valueOf(num1)-1);
					}
					if(towho.getEqiup().size()>=Integer.valueOf(num2)){
						int numm2=Integer.valueOf(num2);
						if(numm2!=0){
							b=towho.getEqiup().get(Integer.valueOf(num2)-1);
						}
					}
					// ����Card��cardused����
					a.cardused(a,b,who,towho,z);
					System.out.println();
//					if () {
						// �ɹ���������
						
//					} else {
//						// �����ܴ򣬽�Ԥ������ƷŻ�
//						who.addhandCard(who.getTmpCard());
//						System.out.println("�������ڲ��ܳ���");
//					}
				}else{
					System.out.println("�����������");
				}
//				else if(r<10){
//					System.out.println(who.getType().toString() + "���ƣ�"
//							+ who.getHandcard().get(r - 1).getName().toString());
//					// Ԥ����ƣ���ʱ������tmpCard���洢������ƣ���Ч��Ż�
//					if(who.getCrystai()>=who.getHandcard().get(r - 1).getCrystai()){
//						int suicongxiaohao=who.getHandcard().get(r - 1).getCrystai();
//						who.setTmpCard(who.getHandcard().get(r - 1));
//						if("0".equals(who.getHandcard().get(r - 1).getFlag())){
//							who.getEqiup().add(who.getHandcard().get(r - 1));//���������ӷŵ�����
//						}
////						who.getEqiup().add(who.getHandcard().get(r - 1));//���������ӷŵ�����
//						// ������˸��ӣ�����Ϊֱ�ӵ�������ɾ�������ᴥ��ĳЩ����
//						ArrayList<Card> tmp = new ArrayList<Card>();
//						tmp.addAll(who.getHandcard());
//						tmp.remove(r - 1);
//						who.setHandcard(tmp);
//						who.setCrystai(who.getCrystai()-suicongxiaohao);
//					}else{
//						System.out.println("��û���㹻�ķ���");
//					}
//					
//
//				}else{
//					System.out.println("����ȷ����");
//				}

			} catch (Exception e) {// ��������쳣����Ч����
				System.out.println("��������룬���������룡");
				e.printStackTrace();
			}
		}

	}
	
	//Ӣ��Ĭ�ϼ��ܣ�ÿ��Ӣ�۶�����д
	public void useSkill(Player who, Player towho,int r){
		who.getCharacter().characterskill(who, towho, r);
	}
}
