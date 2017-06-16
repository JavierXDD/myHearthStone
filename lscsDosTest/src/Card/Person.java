package Card;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

import Role.Player;

public class Person extends Card{
	private int att;
	private int hp;
	private List<String> special= new ArrayList<String>();
	public List<String> getSpecial() {
		return special;
	}
	public void setSpecial(List<String> special) {
		this.special = special;
	}
	private String flag="0";//�ж��Ƿ����������0�����ʹӣ�1������
	private boolean isatt=false;
	private int type=0;//�ʹ�����0����������1�������࣬2����Ұ��,Ĭ��Ϊ����
	private List<Card> haloed=new ArrayList<Card>();//�ܵ��Ĺ⻷�ӳ�--�漰���Ժ����Ѫ��Ұ������Ҳ�����ڹ⻷Ч����card��person����
	private boolean halo=false;//�ж��Ƿ��й⻷��������usecardʱչ�ֳ���
	
	
	public List<Card> getHaloed() {
		return haloed;
	}
	public void setHaloed(List<Card> haloed) {
		this.haloed = haloed;
	}
	public boolean isHalo() {
		return halo;
	}
	public void setHalo(boolean halo) {
		this.halo = halo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean getIsatt() {
		return isatt;
	}
	public void setIsatt(boolean isatt) {
		this.isatt = isatt;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
//	public String getSpecial() {
//		return special;
//	}
//	public void setSpecial(String special) {
//		this.special = special;
//	}
	public int getAtt() {
		return att;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z) {
		if("h".equals(z.substring(0,1))){//˵���Ǵ������Ҫ�ŵ�������
			Person pa=(Person) a;
			boolean fight=true;
			if(c.getCrystai()>=a.getCrystai()){//��ʣ��ˮ�����ڿ�������ˮ��
				if(pa.getSpecial().contains(Dict.FIGHTINSPIRE)){
					fight=pa.fightSpire(pa, b, c, d, z);
				}
				if(fight){
					c.setCrystai(c.getCrystai()-a.getCrystai());
					c.getEqiup().add(a);
					c.getHandcard().remove(a);
				}
				
			}else{
				System.out.println("��û���㹻�ķ���ֵ");
			}
			for(int i=0;i<c.getEqiup().size();i++){//����⻷Ч��
				Person pp =(Person) c.getEqiup().get(i);
				if(pp.isHalo()==true){
					pp.haloHappening(c,d,a);
				}
			}
		}else if("d".equals(z.substring(0,1))){
			if(d.getAoMi().size()>0){//����
				for(int i=0;i<d.getAoMi().size();i++){
					CardSkill card = (CardSkill) d.getAoMi().get(i);
					boolean flag=card.conditions(a, b, c, d);
					if(flag==true){
						card.result(a, b, c, d);
					}
				}
			}
			Player aid =(Player) d;
			Person p=(Person)a;
			Person aip=(Person)b;
			
			if(p.getIsatt()&&p.getHp()>0){

				boolean bo=true;
				for(int i =0;i<d.getEqiup().size();i++){
					Person pp = (Person) d.getEqiup().get(i);
						if(pp.getSpecial().contains(Dict.DERIDe)){//˵���г������
//					if(.equals(pp.getSpecial())){
							bo=false;
						}
						
				}
				if(bo){
					if(a!=null&&b==null){//ֱ�Ӵ���
						int attract=p.getAtt();
						System.out.println(d.getType()+"�ܵ�"+attract+"���˺���ʣ������Ϊ"+(aid.getHp()-attract));
						aid.setHp(aid.getHp()-attract);
						if(aid.getHp()<=0){
							System.out.println(aid.getType()+"������");
							System.out.println("��л���Ĳ���");
							System.exit(0);
						}
						
					}else if(a!=null&&b!=null){
						cardfightcard(a, b, c, d, p, aip);
					}
					p.setIsatt(false);//�����Ѿ����������޸�Ϊ�����ڹ�������������Ҫ��д
				}else{
					if(b==null){
						System.out.println("���ȹ������г������Ե����");
					}else{
						Person bp =(Person) b;
						if(!bp.getSpecial().contains(Dict.DERIDe)){
//						if(Dict.DERIDe.equals(bp.getSpecial())){
							System.out.println("���ȹ������г������Ե����");
						}else{
							cardfightcard(a, b, c, d, p, aip);
							p.setIsatt(false);//�����Ѿ����������޸�Ϊ�����ڹ�������������Ҫ��д
						}
					}
				}
			}else{
				System.out.println("����Ӳ��ܽ��й���");
			}
			
		}else{
			System.out.println("�����������");
		}
		return true;
	}
	public void cardfightcard(Card a, Card b, Player c, Player d, Person p,
			Person aip) {
		int attract=p.getAtt();//������ӵ�ap
		int hp=p.getHp();//������ӵ�hp
		int attracted=aip.getAtt();//��������ӵ�ap
		int hped=aip.getHp();//��������ӵ�hp
		aip.setHp(hped-attract);
		for(int e=0;e<d.getEqiup().size();e++){
			if(d.getEqiup().get(e).getHp()<=0){
				
				Person bb= (Person) b;//��Ϊ�⻷���Ӧ�Ƴ��⻷Ч��
				if(bb.isHalo()==true){
					bb.haloEnd(d, c, b);
				}
//				d.getEqiup().remove(b);
				Utils.remove(b, a, d, c);
			}
		}
		p.setHp(hp-attracted);
		for(int f=0;f<c.getEqiup().size();f++){
			if(c.getEqiup().get(f).getHp()<=0){
				Person aa= (Person) a;//��Ϊ�⻷���Ӧ�Ƴ��⻷Ч��
				if(aa.isHalo()==true){
					aa.haloEnd(c, d, a);
				}
				
//				c.getEqiup().remove(a);
				Utils.remove(a, b, c, d);
			}
		}
		System.out.println("["+d.getType()+"]"+b.getName()+"�ܵ�"+"["+c.getType()+"]"+a.getName()+attract+"���˺������������"+attracted+"���˺�");
	}
	public void haloHappening(Player c,Player d,Card a){};//�����й⻷Ч���������д
	public void haloEnd(Player c,Player d,Card a){};//�����й⻷Ч���������д
	public void destoryHappening(){}//�۾��ߺ����ĵĴ̿���д
	public void deadleagueHappening(Card a,Card b,Player c,Player d){}//����Ч�������д
	public boolean fightSpire(Card a,Card b,Player c,Player d,String z){return true;}//ս��Ч��
}
