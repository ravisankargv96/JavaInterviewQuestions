class MyThread extends Thread {
    @Override
    public void run(){

        // if needed you can also create randomThread.start() & Thread1 acts as parent thread for it.
        for(int i = 0; i < 5; i++){
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}

class MyThreadTester {
    public static void main(String[] args) {
        // mainThread will finish, but the process/program terminates (Process finished with exiting code 0)
        // only after finishing executing the userThreads.

        System.out.println("main is starting()");

        Thread thread = new MyThread();
        thread.setName("thread"); // assign name
        thread.start();

        // created instance
        Thread deamon = new MyThread();
        deamon.setDaemon(true); //made it as deamonThread.
        deamon.setName("deamon"); // assign name
        deamon.start();

        System.out.println("main is exiting()");
    }
}
