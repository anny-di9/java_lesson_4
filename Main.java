package java_3;

public class Main {

    public static void main(String[] args) {

        char[] c = {'A'};
        Object abc = new Object();

        class WaitNotifyClass implements Runnable {
            private char currentLetter;
            private char nextLetter;

            public WaitNotifyClass(char currentLetter, char nextLetter) {
                this.currentLetter = currentLetter;
                this.nextLetter = nextLetter;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (abc) {
                        try {
                            while (c[0] != currentLetter)
                                abc.wait();
                            System.out.print(currentLetter);
                            c[0] = nextLetter;
                            abc.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();


                        }
                    }
                }
            }
        }
        new Thread(new WaitNotifyClass('A', 'B')).start();
        new Thread(new WaitNotifyClass('B', 'C')).start();
        new Thread(new WaitNotifyClass('C', 'A')).start();
    }
}