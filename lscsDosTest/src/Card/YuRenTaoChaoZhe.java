package Card;

import java.util.Random;

import utils.Utils;

import Role.Player;

public class YuRenTaoChaoZhe extends Person {
	public YuRenTaoChaoZhe(){
		this.setCrystai(2);
		this.setName("鱼人踏潮者");
		this.setHp(1);
		this.setAtt(2);
	}
	public YuRenTaoChaoZhe(int x,int y){
		this.setCrystai(0);
		this.setName("鱼人卫士");
		this.setHp(x);
		this.setAtt(y);
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z) {

		if(c.getType()!="[AI]"){
			if("h".equals(z.substring(0,1))){//说明是打出牌需要放到桌面上
				if(z.length()<2){
					System.out.println("你不能出此牌，请按正规格式出牌。h代表你的手牌,第一个数字代表黑铁矮人编号，第二个数字代表buff对象编号10以内的数字为1-9代表友方随从号，11-19表示对方随从号");
				}else{
					c.setCrystai(c.getCrystai()-a.getCrystai());
					c.getEqiup().add(a);
					c.getEqiup().add(new YuRenTaoChaoZhe(1,1));
					c.getHandcard().remove(a);
				}
				
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
								Utils.remove(b, a, d, c);
							}
						}
						p.setHp(hp-attracted);
						for(int f=0;f<c.getEqiup().size();f++){
							if(c.getEqiup().get(f).getHp()<=0){
//								c.getEqiup().remove(a);
								Utils.remove(a, b, c, d);
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
							c.setCrystai(c.getCrystai()-a.getCrystai());
							c.getEqiup().add(new YuRenTaoChaoZhe(1,1));
							c.getEqiup().add(a);
							c.getHandcard().remove(a);
			}else if("d".equals(z.substring(0,1))){//ai场上的黑铁矮人发动攻击
				Person ai=(Person) a;//
				Person bb=(Person) b;//
				if(b.getHp()>0){//若场上有随从则攻击随从
					b.setHp(bb.getHp()-ai.getAtt());
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
						cc.setHp(cc.getHp()-bb.getAtt());
						a.setHp(a.getHp()-bb.getAtt());
						if(a.getHp()<=0){
//							c.getEqiup().remove(a);
							Utils.remove(a, b, c, d);
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
