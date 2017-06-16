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
	private String name;//英雄名字
	private int maxhp=30;//英雄总血量
	private int maxCrystai=1;//英雄总水晶
	private boolean skill;//是否可用英雄技能
	private boolean skipUseCard; // 是否结束回合
	private int hp; // 人物的当前血量，不能超过上限character.life
	private int Crystai;//英雄当前水晶
	private boolean isatt;//是否可以攻击
	
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
		System.out.println("出牌阶段...");
		
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
		System.out.println("出牌阶段...");
		// 显示操作列表
		// 接受输入参数
		// 检查是否有效；无效则返回
		// 执行所对应的方法 switch
		// 循环，直到选择弃牌；
		if (who.getType() == "(AI)") {
			// ****如果轮到AI，此处调用AI的方法。****
//			System.out.println("----------------------"+who.getCrystai()+"==========");
			int n = who.getHandcard().size();
			if (n == 0) {
				return;
			}
			List<Card> tmplist=new ArrayList();//临时区域
			tmplist.addAll(who.getHandcard());//将ai的全部手牌放到临时区
			tmplist=Sort.sortdesc(tmplist);//在临时区排序
			Card b =null;
			for(int i=0;i<tmplist.size();i++){
				int result=who.getCrystai()-tmplist.get(i).getCrystai();
				if(result>=0){//证明能够出此牌
//					who.setCrystai(result);//扣水晶费用在每张牌中做。。
					who.setTmpCard(tmplist.get(i));
					ArrayList<Card> tmp = new ArrayList<Card>();
					tmp.addAll(who.getHandcard());
					tmp.remove(tmplist.get(i));
					who.setHandcard(tmp);
					// 调用Card的use方法
					System.out.println(who.getType().toString() + "出牌："
							+ tmplist.get(i).getName().toString());
					Card a= who.getTmpCard();
					if(towho.getEqiup().size()<1){//若对方场上没牌则设定b为空
						b =null;
					}else{
						for(int z=0;z<towho.getEqiup().size();z++){
							Person pp=(Person) towho.getEqiup().get(z);
							if(pp.getSpecial().contains(Dict.DERIDe)){//如果有嘲讽的随从目标就是嘲讽随从否则直接目标脸
								b=pp;
								break;
							}else{
								b=null;
							}
						}
//						b =towho.getEqiup().get(new Random().nextInt(towho.getEqiup().size()));
					}
					String z ="h";
					if(who.getEqiup().size()>0){//说明有随从在场上
						int num =new Random().nextInt(who.getEqiup().size())+1;
						z=z+1+num;
					}else if(towho.getEqiup().size()>0){
						int num = new Random().nextInt(towho.getEqiup().size())+1;
						z=z+11+num;
					}
					System.out.println("-----------------------------------"+z+"----------------------------------");
					a.cardused(a, b, who, towho,z);
//					if(("0").equals(a.getFlag())){//若标识为0，将此牌放到场上
//						a.cardused(a, b, who, towho,"h");
//						who.getEqiup().add(a);
//					}else{
//						a.cardused(a, b, who, towho,"h");
//					}
				}
			}
			if(who.getCrystai()>=2){//若有多余水晶 进行英雄技能
				who.getCharacter().characterskill(who, towho, 2);
			}
			for(int t=0;t<who.getEqiup().size();t++){
				if(towho.getEqiup().size()<1){//若对方场上没牌则设定b为空
					b =null;
					if (who.getEqiup().get(t).cardused(who.getEqiup().get(t),b, who,towho,"d")) {
						// 成功则继续
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
						if(pp.getSpecial().contains(Dict.DERIDe)){//如果有嘲讽的随从目标就是嘲讽随从否则直接目标脸
							b=pp;
							break;
						}else{
							b=null;
//							b =towho.getEqiup().get(new Random().nextInt(towho.getEqiup().size()));//
						}
					}
					if (who.getEqiup().get(t).cardused(who.getEqiup().get(t),b, who,towho,"d")) {
						// 成功则继续
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
		while (true) { // 玩家出牌过程，一直可以出牌，直到选择弃牌或者无牌

			System.out.println(who.getType().toString() + "出牌..." + "\n");
			who.printHandCard(who,towho);
			System.out.println("\n"+"输入序号来出牌"+"\n"+"0为不出牌pass，-1x为发动武器技能，-2为发动人物主动技能，dXX代表随从进行攻击，hXX代表出手牌中的卡牌，第一个X为手牌第几张，第二个代表对方场上的随从，0代表对方,help查询双方状态");
			System.out.print("=>");
			Scanner sc = new Scanner(System.in);
			try { // 参数输入需做异常处理
				String z=sc.next();
//				System.out.println(z);
//				int r = sc.nextInt();
				// int参数，表示输入的序列号[！！由于显示序列时候0作为弃牌，所有序号都+1显示，在处理时要-1]
				if ("0".equals(z)) { // 参数0回合结束
					return;
				}
				if ("-1".equals(z.substring(0,2))) {
					if(z.length()==3){
						if(who.getEquipment().size()>0){
							Card equipment =who.getEquipment().get(0);//武器就只有一个
							Equipment e= (Equipment)equipment;//强转
							e.useEquipment(null,null, who, towho, z);
						}else{
							System.out.println("你没有装备武器");
						}
					}else{
						System.out.println("你输入的有误");
					}
				} else if ("-2".equals(z)) {
					if(who.getCharacter().isSkill()){
						who.getCharacter().useSkill(who, towho,Integer.valueOf(z));
					}
				} else if ("help".equals(z)) {
					who.printInfo(who, towho);
					towho.printInfo(who, towho);
				} else if(z.substring(0,1).equals("h")){//手牌
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
					
					// 调用Card的cardused方法
					a.cardused(a,b,who,towho,z);
					System.out.println();
//					if (a.cardused(a,b,who,towho,z)) {
						// 成功则不做处理
//						who.getHandcard().remove(Integer.valueOf(num1)-1);
						//} else {
						// 若不能打，将预打出的牌放回
						//who.addhandCard(who.getTmpCard());
						//System.out.println("此牌现在不能出！");
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
					// 调用Card的cardused方法
					a.cardused(a,b,who,towho,z);
					System.out.println();
//					if () {
						// 成功则不做处理
						
//					} else {
//						// 若不能打，将预打出的牌放回
//						who.addhandCard(who.getTmpCard());
//						System.out.println("此牌现在不能出！");
//					}
				}else{
					System.out.println("你输入的有误");
				}
//				else if(r<10){
//					System.out.println(who.getType().toString() + "出牌："
//							+ who.getHandcard().get(r - 1).getName().toString());
//					// 预打出牌，临时变量，tmpCard，存储打出的牌，无效则放回
//					if(who.getCrystai()>=who.getHandcard().get(r - 1).getCrystai()){
//						int suicongxiaohao=who.getHandcard().get(r - 1).getCrystai();
//						who.setTmpCard(who.getHandcard().get(r - 1));
//						if("0".equals(who.getHandcard().get(r - 1).getFlag())){
//							who.getEqiup().add(who.getHandcard().get(r - 1));//将打出的随从放到场上
//						}
////						who.getEqiup().add(who.getHandcard().get(r - 1));//将打出的随从放到场上
//						// 这里如此复杂，是因为直接调用手牌删除方法会触发某些技能
//						ArrayList<Card> tmp = new ArrayList<Card>();
//						tmp.addAll(who.getHandcard());
//						tmp.remove(r - 1);
//						who.setHandcard(tmp);
//						who.setCrystai(who.getCrystai()-suicongxiaohao);
//					}else{
//						System.out.println("你没有足够的法力");
//					}
//					
//
//				}else{
//					System.out.println("请正确输入");
//				}

			} catch (Exception e) {// 输入出现异常，无效处理
				System.out.println("错误的输入，请重新输入！");
				e.printStackTrace();
			}
		}

	}
	
	//英雄默认技能，每个英雄都需重写
	public void useSkill(Player who, Player towho,int r){
		who.getCharacter().characterskill(who, towho, r);
	}
}
