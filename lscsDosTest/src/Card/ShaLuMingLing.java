package Card;

import utils.Utils;
import Role.Player;

public class ShaLuMingLing extends CardSkill {
	public ShaLuMingLing(){
		this.setAttr(3);
		this.setCrystai(3);
		this.setName("ɱ¾����");
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z){
		int jiacheng = 0;//���˼ӳɣ�
		boolean flag=false;
		for(int i=0;i<c.getEqiup().size();i++){//�����Ƿ��з��˼ӳɵ����
			Person card = (Person)c.getEqiup().get(i);
				if(card.getSpecial().contains(Dict.MAGICUP)){//�ж��Ƿ���ڷ������
					jiacheng++;
				}
				System.out.println("------------------"+card.getType());
			if(card.getType()==2){//����Ұ��
				flag =true;
			}
			
		}
		if(flag){//����Ұ�޹�����2
			jiacheng=jiacheng+2;
		}
		if(a.getCrystai()<=c.getCrystai()){
			c.setCrystai(c.getCrystai()-a.getCrystai());
			int attr=this.getAttr()+jiacheng;
			if(a!=null&&b==null){//���ܴ���
				d.setHp(d.getHp()-this.getAttr()-jiacheng);
				System.out.println(c.getType()+"ʹ��"+a.getName()+d.getType()+"�ܵ�"+attr+"���˺�");
				if(d.getHp()<=0){
					System.out.println(d.getType()+"������");
					System.out.println("��л���Ĳ���");
					System.exit(0);
				}
			}else if(a!=null&&b!=null){//���ܴ��
				Person bb=(Person) b;
				if(bb.getSpecial().contains(Dict.MAGICIMMUNITY)||bb.getSpecial().contains(Dict.HIDE)){
					System.out.println(b.getName()+"���ܳ�Ϊ����Ŀ��");
					c.setCrystai(c.getCrystai()+a.getCrystai());
					return true;	
				}
				CardSkill card = (CardSkill)a;//ǿת
				int att =card.getAttr();
				b.setHp(b.getHp()-att-jiacheng);
				System.out.println(c.getType()+"ʹ��"+a.getName()+b.getName()+"�ܵ�"+attr+"���˺�,ʣ��"+b.getHp()+"Ѫ");
				if(b.getHp()<=0){//���ܽ��Է���Ӵ���
//					d.getEqiup().remove(b);
					Utils.remove(b, a, d, c);
				}
			}
			c.getHandcard().remove(a);//��������󽫼�����ȥ��
			return true;
		}else{
			System.out.println("��û���㹻��ˮ��");
			return false;
		}
		
	}
}
