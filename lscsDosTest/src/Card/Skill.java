package Card;

import Role.Character;

public class Skill extends Card{

	/**
	 * 奥术射击
	 * @param p 己方
	 * @param ai 敌方
	 */
		public void aoshusheji(Character p,Character t,Card m){
			if(t!=null&&m==null){
				if(p.getCrystai()-1>0){
					p.setCrystai(p.getCrystai()-1);
					t.setHp(t.getHp()-2);
				}else{
					System.out.println("你没有足够的法力值");
				}
			}else if(m!=null&&t==null){
				if(p.getCrystai()-1>0){
					p.setCrystai(p.getCrystai()-1);
					Person pp =(Person) m;
					pp.setHp(pp.getHp()-2);
				}else{
					System.out.println("你没有足够的法力值");
				}
			}else{
				System.out.println("请选择有效的目标");
			}
		}
}
