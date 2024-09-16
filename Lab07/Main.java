import card.Card;
import card.ElectronicCard;
import card.GiftCard;
import card.Topupable;
import item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();

    public static void main(String[] args) {
        initItems();
        showMenu();
    }

    private static void initItems() {
        System.out.println("\n========== Buat Item ==========");
        System.out.printf("Masukkan jumlah item: ");
        int jumlahItem = in.nextInt();
        in.nextLine();

        for (int i = 0; i < jumlahItem; i++) {
            System.out.printf("========== Item ke-%d ==========%n", i + 1);
            System.out.print("Nama: ");
            String name = in.nextLine();

            System.out.print("Harga: ");
            int price = in.nextInt();
            in.nextLine();

            Item item = new Item(name, price);
            items.add(item);
        }
    }

    private static void showMenu() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n========== Menu ==========");
            System.out.println("1. Tambahkan Kartu");
            System.out.println("2. Tampilkan Daftar Kartu");
            System.out.println("3. Beli Item");
            System.out.println("4. Topup");
            System.out.println("5. Keluar");
            System.out.print("Masukan pilihan: ");
            int pilihan = in.nextInt();
            in.nextLine();

            isRunning = execute(pilihan);
        }
    }

    private static boolean execute(int pilihan) {
        switch (pilihan) {
            case 1 -> addCard();
            case 2 -> showCard();
            case 3 -> buyItem();
            case 4 -> topup();
            default -> {
                return false;
            }
        }
        return true;
    }

    public static void addCard() {
        System.out.println("\n========== Tambah Kartu ==========");
        System.out.print("Company Name: ");
        String companyName = in.nextLine();

        System.out.print("Tipe: ");
        String cardType = in.nextLine().toUpperCase();

        System.out.print("Balance: ");
        double balance = in.nextDouble();
        in.nextLine();

        Card card = createCard(companyName, balance, cardType);
        cards.add(card);

        System.out.println("Berhasil menambahkan kartu");
    }

    private static Card createCard(String companyName, double balance, String type) {
        // membuat kartu berdasarkan tipe
        if(type.equalsIgnoreCase("gift")){
            Card giftCard = new GiftCard(companyName, balance, type);
            return giftCard;
        }
        else if(type.equalsIgnoreCase("electronic")){
            Card electronicCard = new ElectronicCard(companyName, balance, type);
            return electronicCard;
        }
        return null;
    }

    private static void showCard() {
        sortListOfCards();
        printListOfCards();
    }

    public static void buyItem() {
        System.out.println("\n========== Daftar Item ==========");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("[%d] %s%n", i, items.get(i));
        }
        System.out.print("Pilihan Item: ");
        int pilihan = in.nextInt();
        in.nextLine();

        // pilih item
        Item itemPilihan = items.get(pilihan);

        showCard();
        System.out.print("Pilih id Kartu yang tersedia: ");
        int cardId = in.nextInt();
        in.nextLine();

        // pilih kartu
        Card card = getCardById(cardId);

        // beli item dengan kartu yang dipilih
        if(card.getBalance() < itemPilihan.getPrice()){
            System.out.printf("Uang tidak cukup!\n");
        }
        else{
            if(card instanceof GiftCard){
                card.pay(itemPilihan);
                System.out.printf("Item %s dengan harga %d berhasil dibeli%n", itemPilihan.getName(), itemPilihan.getPrice());
            }
            else if (card instanceof ElectronicCard){
                card.pay(itemPilihan);
                System.out.printf("Item %s dengan harga %d berhasil dibeli%n", itemPilihan.getName(), itemPilihan.getPrice());
            }
        }
    }
        
    

    public static void topup() {
        showCard();
        System.out.print("Pilih id Kartu yang tersedia: ");
        int cardId = in.nextInt();
        in.nextLine();

        // pilih kartu
        Card card = getCardById(cardId);

        System.out.print("Amount: ");
        double amount = in.nextDouble();
        in.nextLine();

        // TODO: topup kartu yang dipilih
        if(card instanceof GiftCard){
            System.out.println("Kartu yang dipilih tidak bisa topup"); // tidak bisa top up
        }
        else if (card instanceof ElectronicCard){
            ((ElectronicCard)card).topup(amount); // bisa top up
            System.out.println("Berhasil topup kartu");
        }

    }

    private static void printListOfCards() {
        System.out.printf("========== Daftar Kartu ==========%n");

        for (int i = 0; i < cards.size(); i++) {
            System.out.printf("[%d] %s %n", i, cards.get(i));
        }
    }

    private static Card getCardById(int id) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getId() == id)
                return card;
        }
        return null;
    }

    private static void sortListOfCards() {
        Collections.sort(cards);
    }
}