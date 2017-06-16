package init;


/*
*��ʼ����*
*��ȡ�佫����
*��ȡ��������
*/
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Card.*;
import Role.Character;
import Role.PlayerAI;
import Role.RexxarRemar;

public class Initial {
	public static String[] name = new String[1];                  //Ӣ�����ֵ�string����
	public static String[] cardKey = new String[30];             //�������ݵ�string����
	public static String[] cardKeyai = new String[30];             //�������ݵ�string����
	public static Card[] card1 = new Card[30];                      //30����
	public static Card[] cardai = new Card[30];                      //30����
	public static List<Card> listCard = new ArrayList<Card>();      //�ƶѼ���
	public static List<Card> listCardAi = new ArrayList<Card>();      //�ƶѼ���
	public static List<Card> throwlistCard = new ArrayList<Card>(); //�����ƶ�
	public static Card tmpCheckCard;                                //����ж��Ƶ���ʱ����
	public static Character cAI ;                     //AI�½��佫---���� XXX
	public static PlayerAI p2 ;   //AI��ҹ���
	public static boolean who ;                                      //������������TΪ��ң�FΪAI����ʼΪT
	public static HashMap<String, String> hmp = new HashMap<String, String>();    //�Ƶļ�ֵ��<Card.name,index>
	public static HashMap<String,Character> hm = new HashMap<String,Character>(); //����ļ�ֵ��
	public static List<Card> listPerson=new ArrayList<Card>();	//�ʹӼ���
	 // ��̬��������ʼ������
	public static void cardInit() throws Exception {                                    
		Initial.setCardKey();
		Initial.setCardKeyAI();
		cardKeyai[29]="TaiYuanXiNiu";
		System.out.println(cardKeyai[29]);
		for (int i = 0; i < 30; i++) {
			Initial.card1[i] = new Card();
			Initial.card1[i].setId("user"+i);
			Initial.card1[i].setName(Initial.cardKey[i]);
			Initial.card1[i].setKey(Initial.cardKey[i]);
			Card o = Initial.card1[i];
			//System.out.println("Card."+Initial.cardKey[i]);
			Class classes =Class.forName("Card."+Initial.cardKey[i]);
			Object own = classes.newInstance();
			Card perCard =(Card) own;
			perCard.setId("user"+i);
			perCard.setKey(perCard.getName());
			perCard.setName(perCard.getName());
			Initial.listCard.add(perCard);
		}
		
		for (int i = 0; i < 30; i++) {
			Initial.cardai[i] = new Card();
			Initial.cardai[i].setId("ai"+i);
			Initial.cardai[i].setKey(Initial.cardKeyai[i]);
			Initial.cardai[i].setName(Initial.cardKeyai[i]);
			Card o = Initial.cardai[i];
			
			Class classes =Class.forName("Card."+Initial.cardKeyai[i]);
			Object own = classes.newInstance();
			Card perCard =(Card) own;
			perCard.setId("ai"+i);
			perCard.setKey(perCard.getName());
			perCard.setName(perCard.getName());
			Initial.listCardAi.add(perCard);
		}
			for (int i = 0; i < Initial.card1.length; i++) {// ����˳��
				Random r = new Random();
				Card cardtmp = Initial.listCard.get(i);
				cardtmp.setId(i+"test");
				cardtmp.setName(cardtmp.getKey());
				Initial.listCard.remove(i);
				Initial.listCard.add(r.nextInt(Initial.listCard.size()), cardtmp);
				
			}
			for (int i = 0; i < Initial.cardai.length; i++) {// ����˳��
				Random r = new Random();
				Card cardtmp = Initial.listCardAi.get(i);
				cardtmp.setId(i+"ai");
				cardtmp.setName(cardtmp.getKey());
				Initial.listCardAi.remove(i);
				Initial.listCardAi.add(r.nextInt(Initial.listCardAi.size()), cardtmp);
				
			}
			//��ӡ����,������
//			for (int i = 0; i < Initial.listCard.size(); i++) {
//				
//				System.out.println(Initial.listCard.get(i));
//			}
			
	}
	public static void list(){ //********************************     //��ӡ�佫�б��Թ�ѡ��
		Initial.line();
		System.out.println("=======��ӭ�������װ����̨¯ʯ��˵=======");
		Initial.line();
		name[0]= "�׿���";
		System.out.println("���������ѡ��Ӣ��...");
		for(int i=0;i<name.length ;i++){
			System.out.println(i+"--"+name[i]);
		}
		System.out.print("=>");
	} 
	public static void setCardKey() throws Exception{  
		//��cardkey.cdk�ļ��ж�ȡ��������
		//��Ŀ�����ļ���·��
		String path = System.getProperty("user.dir")+"\\src";
		
		FileInputStream fis = new FileInputStream(path+"\\cdk.cdk");
		byte[] buff = new byte[1024];
		int r = 0;
		String str = new String();
		while((r=fis.read(buff))>0){                                  //����ȡ�����ݴ����str��ʱ�ַ�����
			str=new String(buff,0,r);
		}
		cardKey = str.split("��");                                    //��split�������ָ�str��ֵ����̬����cardkey
	}
//	public static void setCardPerson(){
//		listPerson.add(new TaiYuanXiNiu());
//		listPerson.add(new ChongFengZhu());
//		listPerson.add(new DongWuHuoBan());
//		listPerson.add(new HeiTieAiRen());
//		listPerson.add(new HuoCheWang());
//		listPerson.add(new JingLinglong());
//		listPerson.add(new LeiOuKe());
//		listPerson.add(new LuMangHuoJianBing());
//		listPerson.add(new MaFengZhuRu());
//		listPerson.add(new MiSha());
//		listPerson.add(new PoSuiCanYangJiSi());
//		listPerson.add(new QingXingLieBao());
//		listPerson.add(new XunMengLong());
//		listPerson.add(new XunShouShi());
//		listPerson.add(new ZhongShi());
//		
//	}
	public static void setCardKeyAI() throws Exception{  
		//��Ŀ�����ļ���·��
		String path = System.getProperty("user.dir")+"\\src";//��aicardkeyai.cdk�ļ��ж�ȡ��������
		FileInputStream fis = new FileInputStream(path+"\\AIcdk.cdk");
		byte[] buff = new byte[1024];
		int r = 0;
		String str = new String();
		while((r=fis.read(buff))>0){                                  //����ȡ�����ݴ����str��ʱ�ַ�����
			str=new String(buff,0,r);
		}
		cardKeyai = str.split("��");                                    //��split�������ָ�str��ֵ����̬����cardkey
	}
	public static void setKeyMap(){}
	
	//**********Map �佫�Ͷ���ļ�ֵ�������������ѡ��*******************
	public static void putCharacter(){
		hm.put("�׿���", new RexxarRemar());
	}
	//��ӡ�ָ���
	public static void line(){
		System.out.println("========================================");
	}
}
	