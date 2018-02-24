package hello.completablefuture;

public class ExchangeService {

    public static Double lookupExchangeRate(String currency, Double rate) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 200.0;
    }
}
