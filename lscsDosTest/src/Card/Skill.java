package Card;

import Role.Character;

public class Skill extends Card{

	/**
	 * �������
	 * @param p ����
	 * @param ai �з�
	 */
		public void aoshusheji(Character p,Character t,Card m){
			if(t!=null&&m==null){
				if(p.getCrystai()-1>0){
					p.setCrystai(p.getCrystai()-1);
					t.setHp(t.getHp()-2);
				}else{
					System.out.println("��û���㹻�ķ���ֵ");
				}
			}else if(m!=null&&t==null){
				if(p.getCrystai()-1>0){
					p.setCrystai(p.getCrystai()-1);
					Person pp =(Person) m;
					pp.setHp(pp.getHp()-2);
				}else{
					System.out.println("��û���㹻�ķ���ֵ");
				}
			}else{
				System.out.println("��ѡ����Ч��Ŀ��");
			}
		}
}
