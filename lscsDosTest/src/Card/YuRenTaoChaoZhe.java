package Card;

import java.util.Random;

import utils.Utils;

import Role.Player;

public class YuRenTaoChaoZhe extends Person {
	public YuRenTaoChaoZhe(){
		this.setCrystai(2);
		this.setName("����̤����");
		this.setHp(1);
		this.setAtt(2);
	}
	public YuRenTaoChaoZhe(int x,int y){
		this.setCrystai(0);
		this.setName("������ʿ");
		this.setHp(x);
		this.setAtt(y);
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z) {

		if(c.getType()!="[AI]"){
			if("h".equals(z.substring(0,1))){//˵���Ǵ������Ҫ�ŵ�������
				if(z.length()<2){
					System.out.println("�㲻�ܳ����ƣ��밴�����ʽ���ơ�h�����������,��һ�����ִ���������˱�ţ��ڶ������ִ���buff������10���ڵ�����Ϊ1-9�����ѷ���Ӻţ�11-19��ʾ�Է���Ӻ�");
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
						int attract=p.getAtt();//������ӵ�ap
						int hp=p.getHp();//������ӵ�hp
						int attracted=aip.getAtt();//��������ӵ�ap
						int hped=aip.getHp();//��������ӵ�hp
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
						System.out.println("["+d.getType()+"]"+b.getName()+"�ܵ�"+"["+c.getType()+"]"+a.getName()+attract+"���˺������������"+attracted+"���˺�");
					}
					p.setIsatt(false);//�����Ѿ����������޸�Ϊ�����ڹ�������������Ҫ��д
				}else{
					System.out.println("����Ӳ��ܽ��й���");
				}
			}
			return true;
		}else{
			if("h".equals(z.substring(0,1))){//˵���Ǵ������Ҫ�ŵ�������
							c.setCrystai(c.getCrystai()-a.getCrystai());
							c.getEqiup().add(new YuRenTaoChaoZhe(1,1));
							c.getEqiup().add(a);
							c.getHandcard().remove(a);
			}else if("d".equals(z.substring(0,1))){//ai���ϵĺ������˷�������
				Person ai=(Person) a;//
				Person bb=(Person) b;//
				if(b.getHp()>0){//������������򹥻����
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
