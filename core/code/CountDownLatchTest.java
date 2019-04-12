package Concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	static CountDownLatch c = new CountDownLatch(10);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "run");
				c.countDown();
			}, "Thread" + i).start();
		}
		c.await();
		System.out.println("main end");
	}
	
	//CountDownLatch使得一(多)个线程必须等待其他线程完成操作后再执行
	//维护一个计数器(父类的int state),主线程先执行await方法，如果此时计数器大于0，则阻塞等待。当一个线程完成任务后，计数器值减1。直到计数器为0时，表示所有的线程已经完成任务，等待的主线程被唤醒继续执行
}
