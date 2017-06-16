package Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.Utils;

import Role.Player;

public class HeiTieAiRen extends Person{
	public HeiTieAiRen(){
		this.setCrystai(4);
		this.setName("��������");
		this.setHp(4);
		this.setAtt(4);
		List array = new ArrayList();
		array.add(Dict.FIGHTINSPIRE);
		this.setSpecial(array);
	}
	@Override
	/**
	 * �������˵�ս��
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
			System.out.println("����ȷ����");
			return false;
		}
	return true;
	}
	/**
	 * �˷�������ʹ�á�����
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param z
	 * @return
	 */
	public boolean carduseds(Card a,Card b,Player c,Player d,String z) {
		
		if(c.getType()!="[AI]"){
			if("h".equals(z.substring(0,1))){//˵���Ǵ������Ҫ�ŵ�������
				if(c.getEqiup().size()>0){//˵��ai�������
					int addattrp=new Random().nextInt(c.getEqiup().size());
//					if(addattrp<10){
//						if(c.getEqiup().size()>=addattrp-1){
							Person pc=(Person) c.getEqiup().get(addattrp-1);
							pc.setAtt(pc.getAtt()+2);
							c.getEqiup().add(a);
							c.getHandcard().remove(a);
//						}else{
//							System.out.println("����ȷ����");
//						}
//					}
				}else if(d.getEqiup().size()>0){//��������ʼ�ջ���˼ӹ�
					int addattrp=new Random().nextInt(d.getEqiup().size());
//					if(d.getEqiup().size()>=addattrp-11){
						Person apc=(Person) d.getEqiup().get(addattrp-11);
						apc.setAtt(apc.getAtt()+2);
						c.getEqiup().add(a);
						c.getHandcard().remove(a);
//					}else{
//						System.out.println("����ȷ����");
//					}
				}else{//˫����û���
					c.getEqiup().add(a);
					c.getHandcard().remove(a);
				}
				c.setCrystai(c.getCrystai()-a.getCrystai());
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
			}else if("d".equals(z.substring(0,1))){//ai���ϵĺ������˷�������
				Person ai=(Person) a;//AI����
				Person bb=(Person) b;//��ҿ���
				if(b.getHp()>0){//������������򹥻����
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
