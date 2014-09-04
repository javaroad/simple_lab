package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**  
 * @title 两个线程依次交替执行
 * @description 假定两个线程分别为红灯、绿灯，执行的期待结果为：红-绿-红-绿-红-绿...
 * @since 2013-8-17 下午12:51:11
 * just test
 * 
 * last test1
 * last test2
 */
public class AlternateRun2 implements Runnable {

    private int tnum = 1;// 线程编号,Thread Number

    private ReentrantLock lock = new ReentrantLock();

    private Condition redCon = lock.newCondition();
    private Condition greenCon = lock.newCondition();

    public static void main(String[] args) {
        new AlternateRun2().run();
    }

    @Override
    public void run() {
        new Thread(new RedThread(), "red light").start();
        new Thread(new GreenThread(), "green light").start();
    }

    class RedThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (tnum != 1) {// 判断是否该自己执行了[采用while不是if是为了防止死锁]
                        redCon.await();
                    }
                    System.out.println(Thread.currentThread().getName()+ " is flashing...");
                    TimeUnit.SECONDS.sleep(1);// 停留时间，便于从控制台观看

                    tnum = 2;
                    greenCon.signal();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class GreenThread implements Runnable {

        @Override
        public void run() {

            while (true) {
                try {
                    lock.lock();
                    while (tnum != 2) {
                        greenCon.await();
                    }
                    System.out.println(Thread.currentThread().getName()+ " is flashing...");

                TimeUnit.SECONDS.sleep(1);// 停留时间，便于从控制台观看

                    tnum = 1;
                    redCon.signal();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

    }

}
