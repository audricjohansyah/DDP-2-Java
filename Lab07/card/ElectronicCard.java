package card;

import item.Item;

public class ElectronicCard extends Card implements Topupable{
    public ElectronicCard(String companyName, double balance, String type){
        super(companyName, balance, type);
    }

    // method membayar yang mengatur balance setelah melakukan pembayaran
    @Override
    public void pay(Item item){
        setBalance(getBalance() - item.getPrice());
    }

    // method topup yang menambahkan balance sebanyak amount
    @Override
    public void topup(double amount){
        setBalance(getBalance() + amount);
    }

}
