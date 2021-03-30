public class Task_1 {

    volatile int status = 0;
    public static void main(String[] args) {
        Task_1 lock = new Task_1();
        Thread t1 = new Thread(new Task("A", lock,1));
        Thread t2 = new Thread(new Task("B", lock,2));
        Thread t3 = new Thread(new Task("C", lock,3));
        t1.start();
        t2.start();
        t3.start();
    }
}

class Task implements Runnable {

    private String message;
    private final Task_1 lock;
    private int potok;

    Task(String text, Task_1 lock, int p) {
        message = text;
        this.lock = lock;
        this.potok = p;
    }

    @Override
    public void run() {

        while(lock.status < 13) {
            synchronized (lock) {

                while(!((lock.status % 3) == 0) && potok == 1){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while(!((lock.status % 3) == 1) && potok == 2){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while(!((lock.status % 3) == 2) && potok == 3){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(message);
                lock.status++;
                lock.notifyAll();
            }
        }
    }
}
