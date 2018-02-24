package hello.rxjava;

import io.reactivex.Flowable;

public class HelloRx {

	public static void main(String[] args) {
		Flowable.just("Hello world")
				.subscribe(System.out::println);
	}

}
