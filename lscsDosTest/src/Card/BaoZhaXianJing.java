package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class BaoZhaXianJing extends CardSkill {
	public BaoZhaXianJing(){
		this.setCrystai(2);
		this.setName("爆炸陷阱");
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
				c.getHandcard().remove(a);//技能用完后将技能牌去除
			}else{
				System.out.println("不能存放相同的奥秘在场上");
			}
			return true;
		}else{
			System.out.println("你没有足够的水晶");
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
		if(b==null&&aa.getIsatt()){//打脸时触发。。。。
			d.getAoMi().remove(this);
			List<Card> list=c.getEqiup();
			List<Card> listResult= new ArrayList();
			for(int i=0;i<list.size();i++){
				Person p =(Person) list.get(i);
				p.setHp(p.getHp()-2);
				System.out.println(p.getName()+"触发爆炸陷阱，受到2点伤害。剩余血量为"+p.getHp());
				if(p.getHp()<=0){
					System.out.println(p.getName()+"已死亡");
				}else{
					listResult.add(p);
				}
			}
			list.removeAll(list);
			list.addAll(listResult);
			c.setHp(c.getHp()-2);
			if(c.getHp()<=0){
				System.out.println(c.getType()+"已阵亡");
				System.out.println("感谢您的参与");
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
