import init.Initial;

import java.util.Random;
import java.util.Scanner;
import Role.Character;
import Role.Player;
import Role.PlayerAI;

public class RunsGame {
	public static void main(String[] args) throws Exception{
		Initial.cardInit(); 
		Initial.list();                                     //打印英雄列表
		Initial.putCharacter();                             //英雄键值对方法赋值
		Initial.setKeyMap();                                //牌面内容赋值，将牌的key对应成int数字，方便之后的操作。
		Scanner sc = new Scanner(System.in);			    //允许输入序号
		Character c1 = Initial.hm.get(Initial.name[sc.nextInt()]);  //根据序列号 new一个英雄
		Player p1 = new Player(c1,c1.getMaxhp());           //创建玩家1，为其武将属性赋值
		int i =new Random().nextInt(1);
		Initial.cAI =Initial.hm.get(Initial.name[i]);
		Initial.p2 = new PlayerAI(Initial.cAI,Initial.cAI.getMaxhp());
		System.out.println("玩家选择了："+c1.getName());
		Thread.sleep(999);
		System.out.println("AI选择了："+Initial.p2.getCharacter().getName());
		Initial.line();
		Thread.sleep(999);
		int shaizi= new Random().nextInt(100);
		if(shaizi>50){
			p1.giveCard(p1,3);                       //先手，起始3张
			Initial.p2.getluckmoney(Initial.p2);
			Initial.p2.giveCard(Initial.p2, 4);
			Initial.who=true;
		}else{//后手起始4张
			p1.getluckmoney(p1);
			p1.giveCard(p1,4);                       
			Initial.p2.giveCard(Initial.p2,3);  
			Initial.who=false;
		}
		p1.checkCard();
		Thread.sleep(999);
		System.out.println("");
		Thread.sleep(999);
		while(Initial.p2.getCharacter().getHp()>0 && p1.getCharacter().getHp()>0){
			Player p = Initial.who?p1:Initial.p2;
			Player toP=(!Initial.who)?p1:Initial.p2;
			p.turn(p,toP);
		}	
	}
}
