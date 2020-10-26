package com.thread;

// P.525 데몬 쓰레드
class TT extends Thread{

	@Override
	public void run() {
		while(true){
			System.out.println("Thread ....");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}



public class Test4 {

	public static void main(String[] args) throws InterruptedException {
		TT tt = new TT();
		tt.setDaemon(true);
		tt.start();
//		tt.stop();   <- 이렇게 쓰지 않는다.
//		tt.interrupt();
		Thread.sleep(10000);
		System.out.println("End Application....");
	

	}

}
