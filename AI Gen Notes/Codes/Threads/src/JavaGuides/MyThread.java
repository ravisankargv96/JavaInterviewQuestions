package JavaGuides;

/**
 * Create & Start Thread
 * 1. Extending Thread Class
 * 2. Using Runnable Functional Interface.
 * 3. Using Lambda (Since Runnable is a Functional Interface).
 * */

//Extending the Thread class
class MyThread extends Thread {

    @Override
    public void run(){
        System.out.println("Thread is running");
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread(); // create a thread object
        thread.start();
    }
}

// Implementing the Runnable Interface
class MyRunnable implements Runnable {
    @Override
    public void run(){
        // printing 5 values
        for(int i = 0; i < 5; i++){
            System.out.println("Thread is running: "+ i);
        }
    }

    public static void main(String[] args){
        MyRunnable myRunnable = new MyRunnable();

        // creating & start thread.
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}

// Using Lambda Expressions
class LambdaThreadExample {
    public static void main(String[] args) {

        Runnable task = () -> {
            // Runnable is a functional interface, so we are implement Runnable.run():void method here
            for(int i = 0; i < 5; i++){
                System.out.println("Thread running: "+ i);
            }
        };

        // creating & starting Thread.
        Thread thread = new Thread(task);
        thread.start();
    }
}