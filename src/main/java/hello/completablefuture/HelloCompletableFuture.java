package hello.completablefuture;

import java.util.concurrent.CompletableFuture;

public class HelloCompletableFuture {

    public static void main(String[] args) {

        normalFind();
        System.out.println("---------");
        completableFutureFind();
        System.out.println("---------");
        completableFutureFind2();

    }

    private static void normalFind() {
        long start = System.currentTimeMillis();
        Product product = ProductService.findProduct("IPhonex");
        Double bestPrice = PriceService.findBestPrice(product.getName());

        Double rate = ExchangeService.lookupExchangeRate("USD", 100.0);
        Double localPrice = exchange(bestPrice, rate);

        System.out.println("Local price: " + localPrice);
        System.out.printf("Time: %d ms", (System.currentTimeMillis() - start));
    }

    private static Double exchange(Double bestPrice, Double rate) {
        return bestPrice/rate;
    }

    private static void completableFutureFind() {
        long start = System.currentTimeMillis();
        CompletableFuture<Product> completableFutureProduct =
                CompletableFuture.supplyAsync(() -> ProductService.findProduct("IPhonex"));

        CompletableFuture<Double> completableFuturePrice =
                CompletableFuture.supplyAsync(() -> PriceService.findBestPrice(completableFutureProduct.join().getName()));

        CompletableFuture<Double> completableFutureRate =
                CompletableFuture.supplyAsync(() -> ExchangeService.lookupExchangeRate("USD", 100.0));

        Double localPrice = exchange(completableFuturePrice.join(), completableFutureRate.join());

        System.out.println("Local price: " + localPrice);
        System.out.printf("Time: %d ms", (System.currentTimeMillis() - start));
    }

    private static void completableFutureFind2() {
        long start = System.currentTimeMillis();
        CompletableFuture<Product> completableFutureProduct =
                CompletableFuture.supplyAsync(() -> ProductService.findProduct("IPhonex"));

        CompletableFuture<Double> completableFutureRate =
                CompletableFuture.supplyAsync(() -> ExchangeService.lookupExchangeRate("USD", 100.0));

        CompletableFuture<Double> completableFutureLocalPrice = completableFutureProduct
                .thenCompose(p -> CompletableFuture.supplyAsync(() -> PriceService.findBestPrice(p.getName())))
                .thenCombine(completableFutureRate, (price, rate) -> {
                    return exchange(price, rate);
                });


        System.out.println("Local price: " + completableFutureLocalPrice.join());
        System.out.printf("Time: %d ms", (System.currentTimeMillis() - start));
    }
}
