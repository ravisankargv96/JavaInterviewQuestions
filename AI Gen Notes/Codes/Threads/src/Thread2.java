class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}


class MyRunnableTester {
    public static void main(String[] args) {
        // mainThread will finish, but the process/program terminates (Process finished with exiting code 0)
        // only after finishing executing the userThreads.

        System.out.println("main is starting()");

        // new Thread(Runnable)

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.setName("thread");
        thread.start();


        System.out.println("main is exiting()");
    }
}

