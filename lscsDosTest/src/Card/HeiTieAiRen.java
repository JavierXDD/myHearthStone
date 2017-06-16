package Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.Utils;

import Role.Player;

public class HeiTieAiRen extends Person{
	public HeiTieAiRen(){
		this.setCrystai(4);
		this.setName("黑铁矮人");
		this.setHp(4);
		this.setAtt(4);
		List array = new ArrayList();
		array.add(Dict.FIGHTINSPIRE);
		this.setSpecial(array);
	}
	@Override
	/**
	 * 黑铁矮人的战吼
	 */
	public boolean fightSpire(Card a, Card b, Player c, Player d, String z) {
		if(z.length()==3){
			int num =Integer.valueOf(z.substring(2));
			Person pc=(Person) c.getEqiup().get(num-1);
			pc.setAtt(pc.getAtt()+2);
		}else if(z.length()==4){
			int num =Integer.valueOf(z.substring(3));
			Person pc=(Person) d.getEqiup().get(num-1);
			pc.setAtt(pc.getAtt()+2);
		}else{
			System.out.println("请正确输入");
			return false;
		}
	return true;
	}
	/**
	 * 此方法不在使用。。。
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param z
	 * @return
	 */
	public boolean carduseds(Card a,Card b,Player c,Player d,String z) {
		
		if(c.getType()!="[AI]"){
			if("h".equals(z.substring(0,1))){//说明是打出牌需要放到桌面上
				if(c.getEqiup().size()>0){//说明ai方有随从
					int addattrp=new Random().nextInt(c.getEqiup().size());
//					if(addattrp<10){
//						if(c.getEqiup().size()>=addattrp-1){
							Person pc=(Person) c.getEqiup().get(addattrp-1);
							pc.setAtt(pc.getAtt()+2);
							c.getEqiup().add(a);
							c.getHandcard().remove(a);
//						}else{
//							System.out.println("请正确输入");
//						}
//					}
				}else if(d.getEqiup().size()>0){//黑铁矮人始终会给人加攻
					int addattrp=new Random().nextInt(d.getEqiup().size());
//					if(d.getEqiup().size()>=addattrp-11){
						Person apc=(Person) d.getEqiup().get(addattrp-11);
						apc.setAtt(apc.getAtt()+2);
						c.getEqiup().add(a);
						c.getHandcard().remove(a);
//					}else{
//						System.out.println("请正确输入");
//					}
				}else{//双方都没随从
					c.getEqiup().add(a);
					c.getHandcard().remove(a);
				}
				c.setCrystai(c.getCrystai()-a.getCrystai());
			}else if("d".equals(z.substring(0,1))){
				Player aid =(Player) d;
				Person p=(Person)a;
				Person aip=(Person)b;
				if(p.getIsatt()){
					if(a!=null&&b==null){//直接打脸
						int attract=p.getAtt();
						System.out.println(d.getType()+"受到"+attract+"点伤害，剩余生命为"+(aid.getHp()-attract));
						aid.setHp(aid.getHp()-attract);
						if(aid.getHp()<=0){
							System.out.println(aid.getType()+"已阵亡");
							System.out.println("感谢您的参与");
							System.exit(0);
						}
					}else if(a!=null&&b!=null){
						int attract=p.getAtt();//攻击随从的ap
						int hp=p.getHp();//攻击随从的hp
						int attracted=aip.getAtt();//被攻击随从的ap
						int hped=aip.getHp();//被攻击随从的hp
						aip.setHp(hped-attract);
						for(int e=0;e<d.getEqiup().size();e++){
							if(d.getEqiup().get(e).getHp()<=0){
//								d.getEqiup().remove(b);
								Utils.remove(b, b, d, aid);
							}
						}
						p.setHp(hp-attracted);
						for(int f=0;f<c.getEqiup().size();f++){
							if(c.getEqiup().get(f).getHp()<=0){
								Utils.remove(a, b, c, d);
//								c.getEqiup().remove(a);
							}
						}
						System.out.println("["+d.getType()+"]"+b.getName()+"受到"+"["+c.getType()+"]"+a.getName()+attract+"点伤害，并对其造成"+attracted+"点伤害");
					}
					p.setIsatt(false);//本轮已经攻击过，修改为不可在攻击特殊人物需要重写
				}else{
					System.out.println("该随从不能进行攻击");
				}
			}
			return true;
		}else{
			if("h".equals(z.substring(0,1))){//说明是打出牌需要放到桌面上
				int addattrp=c.getEqiup().size();
				int addattrped=d.getEqiup().size();
					if(addattrp<10&&addattrp>0){
						if(c.getEqiup().size()>=addattrp-1){
							Person pc=(Person) c.getEqiup().get(new Random().nextInt(addattrp-1));
							pc.setAtt(pc.getAtt()+2);
							c.getEqiup().add(a);
							c.getHandcard().remove(a);
						}
					}else if(addattrped>0&&addattrped<10){
						if(d.getEqiup().size()>0){
							Person apc=(Person) d.getEqiup().get(new Random().nextInt(d.getEqiup().size())-1);
							apc.setAtt(apc.getAtt()+2);
							c.getEqiup().add(a);
							c.getHandcard().remove(a);
						}else{
							c.getEqiup().add(a);
							c.getHandcard().remove(a);
						}
					}
				
					c.setCrystai(c.getCrystai()-a.getCrystai());
			}else if("d".equals(z.substring(0,1))){//ai场上的黑铁矮人发动攻击
				Person ai=(Person) a;//AI卡牌
				Person bb=(Person) b;//玩家卡牌
				if(b.getHp()>0){//若场上有随从则攻击随从
					b.setHp(bb.getHp()-4);
					a.setHp(a.getHp()-bb.getAtt());
					if(a.getHp()<=0){
//						c.getEqiup().remove(a);
						Utils.remove(a, b, c, d);
					}else if(bb.getHp()<=0){
//						d.getEqiup().remove(b);
						Utils.remove(b, a, d, c);
					}
					ai.setIsatt(false);
				}else{
					if(d.getEqiup().size()>0){
						Person cc=(Person) d.getEqiup().get(new Random().nextInt(d.getEqiup().size()-1));
						cc.setHp(cc.getHp()-4);
						a.setHp(a.getHp()-bb.getAtt());
						if(a.getHp()<=0){
							Utils.remove(a, b, c, d);
//							c.getEqiup().remove(a);
						}else if(cc.getHp()<=0){
//							d.getEqiup().remove(cc);
							Utils.remove(cc, b, d, c);
						}
						ai.setIsatt(false);
					}else{
						ai.setIsatt(false);
					}
				}
				
			}
			
			
			
			
			
			
			
			
			
			return true;
		}
		}
		
}
