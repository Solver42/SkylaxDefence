package se.SkyLax.MPT.Utility;


public class Money {
	private int amount = 3400;
	
	public void killedABeast()
	{
		amount += 400;
		
	}
	
	public String getAmount()
	{
		return Integer.toString(amount);
	}
	public int getAmountInt()
	{
		return amount;
	}
	
	public void boughtTowerOfCost(int cost)
	{
		amount -=cost;
	}

//	
//	public void updatePanText()
//	{
//		ButtonHandler.setCasch(this.amount);
//	}
}
