package com.grid.cuncurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletebleFutureExample {

    static ExecutorService customExecutorService = Executors.newSingleThreadExecutor();

    private static String process(){
        sleep(1000);
        System.out.println("Current Execution thread where the supplier is executed - "
                + Thread.currentThread().getName());
        return "Hello Test";
    }

    private static CompletableFuture<String> createFuture(){
        return CompletableFuture.supplyAsync(CompletebleFutureExample::process, customExecutorService);
    }

    private static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = createFuture();

        String value = stringCompletableFuture.get();

        sleep(3000);

        System.out.println("Completed Processing. Returned value - " + value);

        customExecutorService.shutdown();

    }
}
