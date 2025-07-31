public class Main {

    // Static inner class for bank account
    static class BankAccount {
        private int balance = 1000;

        // Synchronized method to avoid race condition
        public synchronized void withdraw(String user, int amount) {
            System.out.println(user + " is trying to withdraw $" + amount);
            if (balance >= amount) {
                System.out.println(user + " is processing...");
                try {
                    Thread.sleep(100); // simulate delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= amount;
                System.out.println(user + " completed withdrawal. Remaining balance: $" + balance);
            } else {
                System.out.println(user + " failed to withdraw. Not enough balance!");
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Runnable task1 = () -> account.withdraw("Alice", 700);
        Runnable task2 = () -> account.withdraw("Bob", 700);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();
    }
}
