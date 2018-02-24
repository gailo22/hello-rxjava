package hello.completablefuture;

public class ProductService {

    public static Product findProduct(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Product(name, 100.0);
    }
}
