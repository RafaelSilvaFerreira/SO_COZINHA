package Cozinha;

import java.util.concurrent.Semaphore;

public class Main {

public static void main(String[] args) {

	int totalThreads = 5;
	Semaphore banco = new Semaphore (1);
	
	for (int i=1; i<=totalThreads; i++){
			
		Thread t = new ThreadCozimento(i,banco);
		t.start();
	}
}


}
