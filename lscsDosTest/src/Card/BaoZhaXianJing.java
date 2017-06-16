package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class BaoZhaXianJing extends CardSkill {
	public BaoZhaXianJing(){
		this.setCrystai(2);
		this.setName("��ը����");
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z){
		if(a.getCrystai()<=c.getCrystai()){
			boolean flag =true;
			for(int i=0;i<c.getAoMi().size();i++){
				if(this.getName().equals(a.getName())){
					flag=false;
					break;
				}
			}
			if(flag){
				c.setCrystai(c.getCrystai()-a.getCrystai());
				c.getAoMi().add(a);
				c.getHandcard().remove(a);//��������󽫼�����ȥ��
			}else{
				System.out.println("���ܴ����ͬ�İ����ڳ���");
			}
			return true;
		}else{
			System.out.println("��û���㹻��ˮ��");
			return false;
		}
		
	}
	@Override
	public boolean conditions(Card a,Card b,Player c,Player d){
		if(b==null&&a.getFlag().equals("0")){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public void result(Card a,Card b,Player c,Player d){
		Person aa = (Person)a;
		if(b==null&&aa.getIsatt()){//����ʱ������������
			d.getAoMi().remove(this);
			List<Card> list=c.getEqiup();
			List<Card> listResult= new ArrayList();
			for(int i=0;i<list.size();i++){
				Person p =(Person) list.get(i);
				p.setHp(p.getHp()-2);
				System.out.println(p.getName()+"������ը���壬�ܵ�2���˺���ʣ��Ѫ��Ϊ"+p.getHp());
				if(p.getHp()<=0){
					System.out.println(p.getName()+"������");
				}else{
					listResult.add(p);
				}
			}
			list.removeAll(list);
			list.addAll(listResult);
			c.setHp(c.getHp()-2);
			if(c.getHp()<=0){
				System.out.println(c.getType()+"������");
				System.out.println("��л���Ĳ���");
				System.exit(0);
			}
		}
//		List list2= list;
//		for(int i=0;i<list2.size();i++){
//			Person p =(Person) list2.get(i);
//			if(p.getHp()<=0){
//				list2.remove(p);
//			}
//			list.removeAll(list);
//			list.addAll(list2);
//		}
	}
}
