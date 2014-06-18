package hello.rxjava;

import rx.Observable;
import rx.functions.Action1;

public class HelloRx {

	public static void main(String[] args) {
		
		hello("John", "Jane", "Julee");
		
	}

	public static void hello(String... names) {
		Observable.from(names).subscribe(new Action1<String>() {

			@Override
			public void call(String s) {
				System.out.println("Hello " + s + "!");
			}

		});
	}

}
