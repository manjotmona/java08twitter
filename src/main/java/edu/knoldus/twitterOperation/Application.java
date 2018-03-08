package edu.knoldus.twitterOperation;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import twitter4j.Status;

/**
 * Created by pallavi on 7/3/18.
 */
public class Application {
    public static void main(String[] args) {
        TwitterOperations twitterOperations = new TwitterOperations();
        CompletableFuture<List<Status>> status = twitterOperations.getStatus();
        CompletableFuture<Void> statusResult =
                status.thenAccept(value -> System.out.println("\n\n\n\n\n@@@ " + value));

        CompletableFuture<Integer> count = twitterOperations.getCount();
        CompletableFuture<Void> countResult =
                count.thenAccept(value -> System.out.println("\n\n\n\n\n\nCOUNT " + value));

        CompletableFuture<Long> longCompletableFuture =
                twitterOperations.gettweetsOnParticularDate();
        CompletableFuture<Void> dateResult = longCompletableFuture
                .thenAccept(value -> System.out.println("\n\n\n\n\n perDay" + value));

        CompletableFuture<Long> averageRetweetCont = twitterOperations.getAverageRetweetCont();
        CompletableFuture<Void> reTweetResult = averageRetweetCont
                .thenAccept(value -> System.out.println("\n\n\n\nRETWEET " + value));

        CompletableFuture<Long> averageFavouriteCont = twitterOperations.getAverageFavouriteCont();
        CompletableFuture<Void> reFavouriteResult = averageFavouriteCont
                .thenAccept(value -> System.out.println("\n\n\n\nFavourite " + value));
        try {
            Thread.sleep(1000000);
        } catch (java.lang.InterruptedException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
