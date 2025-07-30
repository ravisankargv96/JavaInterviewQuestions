class ThreadTester {
    public static void main(String[] args) {

        System.out.println(" mainThread: starting() ");
        // Rewriting the above Runnable implementation using lambda functions

        Thread simpleThread = new Thread(() -> {
            // Runnable.run() : void definition.
            for(int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName()+ " : " + i);
            }
        }, "lamdaThread");

        simpleThread.start();

        System.out.println(" mainThread: stopping() ");
    }
}
