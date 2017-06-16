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
	private String flag="0";//判断是法术还是随从0代表仆从，1代表法术
	private boolean isatt=false;
	private int type=0;//仆从类型0代表正常，1代表龙类，2代表野兽,默认为正常
	private List<Card> haloed=new ArrayList<Card>();//受到的光环加成--涉及到以后的嗜血和野性咆哮也暂属于光环效果用card比person合适
	private boolean halo=false;//判断是否有光环，若有在usecard时展现出来
	
	
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
		if("h".equals(z.substring(0,1))){//说明是打出牌需要放到桌面上
			Person pa=(Person) a;
			boolean fight=true;
			if(c.getCrystai()>=a.getCrystai()){//若剩余水晶大于卡牌消耗水晶
				if(pa.getSpecial().contains(Dict.FIGHTINSPIRE)){
					fight=pa.fightSpire(pa, b, c, d, z);
				}
				if(fight){
					c.setCrystai(c.getCrystai()-a.getCrystai());
					c.getEqiup().add(a);
					c.getHandcard().remove(a);
				}
				
			}else{
				System.out.println("你没有足够的法力值");
			}
			for(int i=0;i<c.getEqiup().size();i++){//加入光环效果
				Person pp =(Person) c.getEqiup().get(i);
				if(pp.isHalo()==true){
					pp.haloHappening(c,d,a);
				}
			}
		}else if("d".equals(z.substring(0,1))){
			if(d.getAoMi().size()>0){//奥秘
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
						if(pp.getSpecial().contains(Dict.DERIDe)){//说明有嘲讽随从
//					if(.equals(pp.getSpecial())){
							bo=false;
						}
						
				}
				if(bo){
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
						cardfightcard(a, b, c, d, p, aip);
					}
					p.setIsatt(false);//本轮已经攻击过，修改为不可在攻击特殊人物需要重写
				}else{
					if(b==null){
						System.out.println("请先攻击具有嘲讽属性的随从");
					}else{
						Person bp =(Person) b;
						if(!bp.getSpecial().contains(Dict.DERIDe)){
//						if(Dict.DERIDe.equals(bp.getSpecial())){
							System.out.println("请先攻击具有嘲讽属性的随从");
						}else{
							cardfightcard(a, b, c, d, p, aip);
							p.setIsatt(false);//本轮已经攻击过，修改为不可在攻击特殊人物需要重写
						}
					}
				}
			}else{
				System.out.println("该随从不能进行攻击");
			}
			
		}else{
			System.out.println("你输入的有误");
		}
		return true;
	}
	public void cardfightcard(Card a, Card b, Player c, Player d, Person p,
			Person aip) {
		int attract=p.getAtt();//攻击随从的ap
		int hp=p.getHp();//攻击随从的hp
		int attracted=aip.getAtt();//被攻击随从的ap
		int hped=aip.getHp();//被攻击随从的hp
		aip.setHp(hped-attract);
		for(int e=0;e<d.getEqiup().size();e++){
			if(d.getEqiup().get(e).getHp()<=0){
				
				Person bb= (Person) b;//若为光环随从应移除光环效果
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
				Person aa= (Person) a;//若为光环随从应移除光环效果
				if(aa.isHalo()==true){
					aa.haloEnd(c, d, a);
				}
				
//				c.getEqiup().remove(a);
				Utils.remove(a, b, c, d);
			}
		}
		System.out.println("["+d.getType()+"]"+b.getName()+"受到"+"["+c.getType()+"]"+a.getName()+attract+"点伤害，并对其造成"+attracted+"点伤害");
	}
	public void haloHappening(Player c,Player d,Card a){};//具体有光环效果的随从重写
	public void haloEnd(Player c,Player d,Card a){};//具体有光环效果的随从重写
	public void destoryHappening(){}//眼镜蛇和耐心的刺客重写
	public void deadleagueHappening(Card a,Card b,Player c,Player d){}//亡语效果随从重写
	public boolean fightSpire(Card a,Card b,Player c,Player d,String z){return true;}//战吼效果
}
