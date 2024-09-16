package card;

import java.util.function.Supplier;

import item.Item;

public class GiftCard extends Card{
    public GiftCard(String companyName, double balance, String type){
        super(companyName, balance, type);
    }
    
    // method membayar yang mengatur balance setelah melakukan pembayaran
    @Override
    public void pay(Item item){
        setBalance(getBalance() - (item.getPrice()*(0.9))); // memberikan diskon 10%
    }

}