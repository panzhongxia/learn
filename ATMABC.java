package day05;

public class ATMABC extends ATMCBC implements IABC {
	@Override
	public boolean drawMoney(double money) {
		// TODO Auto-generated method stub
		if(card.bal-money>=-2000){
			card.bal-=money;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean payTelBill(String tel, double money) {
		// TODO Auto-generated method stub
		if(tel.length()==11){
			if(drawMoney(money)){
				System.out.println("For:"+tel+"Pay Money"+money+"Success!");
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

}
