package Card;

import java.util.ArrayList;
import java.util.List;

import Role.Player;

public class XunShouShi extends Person{
	public XunShouShi(){
		this.setCrystai(4);
		this.setName("ѱ��ʦ");
		this.setHp(3);
		this.setAtt(4);
		List array = new ArrayList();
		array.add(Dict.FIGHTINSPIRE);
		this.setSpecial(array);
	}
	@Override
	/**
	 * ѱ��ʦ�ĵ�ս������ս���ж������ƣ��ڴ˷������aiҪ������ơ�����
	 */
	public boolean fightSpire(Card a, Card b, Player c, Player d, String z) {
		boolean flag =true;
		if(z.length()==3){
			int num =Integer.valueOf(z.substring(2));
			Person pc=(Person) c.getEqiup().get(num-1);
			if(pc.getType()==2){//�����Ұ����+2+2��������
				pc.setAtt(pc.getAtt()+2);
				pc.setHp(pc.getHp()+2);
				List<String> array = new ArrayList<String>();
				array.add(Dict.DERIDe);
				pc.setSpecial(array);//����
			}else{
				for(int i=0;i<c.getEqiup().size();i++){//����Ƿ���Ұ��
					Person ps=(Person) c.getEqiup().get(i);
					if(ps.getType()==2){//�����Ұ����ս�������Ч
						if(c.getType().equals("(AI)")){//�����ai ����ai�ڸ������п����е㲻���ٴ˿���
							ps.setAtt(pc.getAtt()+2);
							ps.setHp(pc.getHp()+2);
							List<String> array = new ArrayList<String>();
							array.add(Dict.DERIDe);
							ps.setSpecial(array);//����
							break;
						}else{
							flag = false;
							System.out.println("ѱ��ʦ��ս��Ŀ�������Ұ��");
							break;
						}
					}
				}
			}
		}
	return flag;
	}
}