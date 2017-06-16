import init.Initial;

import java.util.Random;
import java.util.Scanner;
import Role.Character;
import Role.Player;
import Role.PlayerAI;

public class RunsGame {
	public static void main(String[] args) throws Exception{
		Initial.cardInit(); 
		Initial.list();                                     //��ӡӢ���б�
		Initial.putCharacter();                             //Ӣ�ۼ�ֵ�Է�����ֵ
		Initial.setKeyMap();                                //�������ݸ�ֵ�����Ƶ�key��Ӧ��int���֣�����֮��Ĳ�����
		Scanner sc = new Scanner(System.in);			    //�����������
		Character c1 = Initial.hm.get(Initial.name[sc.nextInt()]);  //�������к� newһ��Ӣ��
		Player p1 = new Player(c1,c1.getMaxhp());           //�������1��Ϊ���佫���Ը�ֵ
		int i =new Random().nextInt(1);
		Initial.cAI =Initial.hm.get(Initial.name[i]);
		Initial.p2 = new PlayerAI(Initial.cAI,Initial.cAI.getMaxhp());
		System.out.println("���ѡ���ˣ�"+c1.getName());
		Thread.sleep(999);
		System.out.println("AIѡ���ˣ�"+Initial.p2.getCharacter().getName());
		Initial.line();
		Thread.sleep(999);
		int shaizi= new Random().nextInt(100);
		if(shaizi>50){
			p1.giveCard(p1,3);                       //���֣���ʼ3��
			Initial.p2.getluckmoney(Initial.p2);
			Initial.p2.giveCard(Initial.p2, 4);
			Initial.who=true;
		}else{//������ʼ4��
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
