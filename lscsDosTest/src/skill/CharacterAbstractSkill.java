package skill;
import Card.Card;
import Role.Character;
import Role.Player;

public interface CharacterAbstractSkill {
	/**
	 * P ������
	 * ai��ʾ����
	 * pma �ѷ����
	 * aima �з����
	 */
	public void characterskill(Player who, Player towho, int r);
	/**
	 * 
	 * @param p ������
	 * @param ai ��ʾ�з�
	 * @param pma �ѷ����
	 * @param aima �з����
	 */
	public void useCard(Character p,Character ai,Card pma ,Card aima);
	/**
	 * �����غ�
	 */
	public void endUseCard();
	
}
