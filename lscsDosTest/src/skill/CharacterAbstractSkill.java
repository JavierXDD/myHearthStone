package skill;
import Card.Card;
import Role.Character;
import Role.Player;

public interface CharacterAbstractSkill {
	/**
	 * P 代表己方
	 * ai标示电脑
	 * pma 友方随从
	 * aima 敌方随从
	 */
	public void characterskill(Player who, Player towho, int r);
	/**
	 * 
	 * @param p 代表己方
	 * @param ai 标示敌方
	 * @param pma 友方随从
	 * @param aima 敌方随从
	 */
	public void useCard(Character p,Character ai,Card pma ,Card aima);
	/**
	 * 结束回合
	 */
	public void endUseCard();
	
}
