package Card;

import utils.Utils;
import Role.Player;

public class CardSkill extends Card {
	private int attr;
	private String flag="1";
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getAttr() {
		return attr;
	}

	public void setAttr(int attr) {
		this.attr = attr;
	}
	@Override
	public boolean cardused(Card a,Card b,Player c,Player d,String z){
		int jiacheng = 0;//���˼ӳɣ�
		for(int i=0;i<c.getEqiup().size();i++){//�����Ƿ��з��˼ӳɵ����
			Person card = (Person)c.getEqiup().get(i);
				if(card.getSpecial().contains(Dict.MAGICUP)){//�ж��Ƿ���ڷ������
					jiacheng++;
				}
		}
		if(a.getCrystai()<=c.getCrystai()){
			int attr =this.getAttr()+jiacheng;
			c.setCrystai(c.getCrystai()-a.getCrystai());
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
				if(bb.getSpecial().contains(Dict.MAGICIMMUNITY)||bb.getSpecial().contains(Dict.HIDE)){//ħ���Ǳ��״̬������ֱɱ�������й���
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
	//ȥ����ʵ��
	public boolean conditions(Card a,Card b,Player c,Player d){
			return false;
	}
	//ȥ����ʵ��
	public void result(Card a,Card b,Player c,Player d){
	}
}
