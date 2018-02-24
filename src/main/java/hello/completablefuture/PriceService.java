package hello.completablefuture;

public class PriceService {

    public static Double findBestPrice(String productName) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100.0;
    }
}
