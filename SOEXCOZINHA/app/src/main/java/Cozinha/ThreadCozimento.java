package Cozinha;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class ThreadCozimento extends Thread{

private int min;
private int max;
private int id;
private int tipo;
private String name;
private Random cozimento;
private Semaphore entrega;

public ThreadCozimento(int num, Semaphore entrega){
	this.id = num;
	this.entrega = entrega;
	this.tipo = id % 2;
	
	switch (tipo){
		case 1:
	this.min = 500;
	this.max = 800;
	this.name = "Sopa de Cebola";
		break;
		case 0:
	this.min= 600;
	this.max= 1200;
	this.name = "Lasanha à bolonhesa";
		break;
	}
}


@Override
public void run(){
	
	cozinha(cozimento.nextInt(min-max)+ min);
	try {
		entrega.acquire();
		serve();
	}
	catch (InterruptedException e){
		e.printStackTrace();
	}
	finally {
		entrega.release();
	}
}

private void cozinha(int tempo){

	int tempoCozinhando = tempo;
	
	while (tempoCozinhando<tempo){
		try{
			Thread.sleep(100);
			tempoCozinhando += 100;
			System.out.println("Cozinhando: "+
				(tempoCozinhando/(tempo/100))+
				"%"+
			".");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	System.out.println(name + " finalizada!");
}


private void serve(){

	try {
		Thread.sleep(500);
		System.out.println("Prato #"+
			 id+
			 "("+
			 name+
			 ")"+
			" foi entregue!"+
		".");
	}
	catch(InterruptedException e){
		e.printStackTrace();
	}
	
	
}

}
