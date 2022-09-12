import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    private final int[] price;
    private final String[] product;
    private int[] basket;

    public Basket(int[] price, String[] product) {
        this.price = price;
        this.product = product;
        if (basket == null) {
            basket = new int[product.length];
        }
    }

    public void saveBin(File file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(this);
        out.close();
    }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Basket basketObjekt = (Basket) in.readObject();
        in.close();
        return basketObjekt;
    }

    public void printList() {
        System.out.println("Список товаров в магазине:");
        for (int i = 0; i < product.length; i++) {
            System.out.println(i + 1 + ". " + product[i] + " по " + price[i] + " руб.");
        }
    }

    public void addToCart(int amount, int productNum) {
        basket[productNum] += amount;
    }

    public void printCart() {
        System.out.println("Ваша корзина: ");
        int sumProduct = 0;
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] > 0) {
                System.out.println(product[i] + " " + basket[i] + " шт. по " + price[i] +
                        " руб/шт." + " на " + (price[i] * basket[i]) + " руб. в сумме");
                sumProduct += (basket[i] * price[i]);
            }
        }
        System.out.println("Итого товаров в корзине на сумму: " + sumProduct + " руб.");
    }

    @Override
    public String toString() {
        return "Basket{" +
                "price=" + Arrays.toString(price) +
                ", product=" + Arrays.toString(product) +
                ", basket=" + Arrays.toString(basket) +
                '}';
    }
}