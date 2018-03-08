package edu.knoldus.twitterOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * Created by pallavi on 7/3/18.
 */
public class TwitterOperations {
    //Twitter log = Logger.getLogger(this.getClass) //in code
    private static Twitter twitter = TwitterFactory.getSingleton();
    Query query = new Query("#sath");

    public CompletableFuture<List<Status>> getStatus() {

        CompletableFuture<List<Status>> futureTwitter = CompletableFuture.supplyAsync(() -> {

            List<Status> tweets1 = null;
            query.setCount(100);

            try {
                QueryResult result = twitter.search(query);
                tweets1 = result.getTweets();

            } catch (twitter4j.TwitterException ex) {
                System.out.print(ex.getErrorMessage());
            }
            return tweets1;

        });
        return futureTwitter;

    }

    public CompletableFuture<Integer> getCount() {

        CompletableFuture<Integer> twitterCount = CompletableFuture.supplyAsync(() -> {

            QueryResult result = null;

            try {
                result = twitter.search(query);

                // tweets1 = result.getTweets();
            } catch (twitter4j.TwitterException ex) {
                System.out.print(ex.getErrorMessage());
            }
            return result.getCount();

        });
        return twitterCount;

    }

    public CompletableFuture<Long> gettweetsOnParticularDate() {

        CompletableFuture<Long> futureTweetsOnADay = CompletableFuture.supplyAsync(() -> {

            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            String time = "Wed Jan 31 21:00:00 IST 2018";

            List<Status> tweets1 = null;
            query.setCount(100);
            long count = 0;

            try {
                Date date = dateFormat.parse(time);
                QueryResult result = twitter.search(query);
                tweets1 = result.getTweets();
                count = tweets1.stream().filter(tweet -> tweet.getCreatedAt() == date).count();

            } catch (twitter4j.TwitterException ex) {
                System.out.print(ex.getErrorMessage());
            } catch (java.text.ParseException p) {
                System.out.print(p.getMessage());
            }
            return count;

        });
        return futureTweetsOnADay;
    }

    public CompletableFuture<Long> getAverageRetweetCont() {

        CompletableFuture<Long> futureAverageRetweet = CompletableFuture.supplyAsync(() -> {

            List<Status> tweets1 = null;
            query.setCount(100);
            long retweetCount = 0;

            try {

                QueryResult result = twitter.search(query);
                tweets1 = result.getTweets();
                retweetCount = tweets1.stream().map(tweet -> tweet.getRetweetCount())
                        .reduce(0, (x, y) -> x + y);

            } catch (twitter4j.TwitterException ex) {
                System.out.print(ex.getErrorMessage());
            }

            return retweetCount;

        });
        return futureAverageRetweet;
    }

    public CompletableFuture<Long> getAverageFavouriteCont() {

        CompletableFuture<Long> futurefavouriteRetweet = CompletableFuture.supplyAsync(() -> {

            List<Status> tweets1;
            query.setCount(100);
            long favouriteCount = 0;

            try {

                QueryResult result = twitter.search(query);
                tweets1 = result.getTweets();
                favouriteCount = tweets1.stream().map(tweet -> tweet.getFavoriteCount())
                        .reduce(0, (x, y) -> x + y);
            } catch (twitter4j.TwitterException ex) {
                System.out.print(ex.getErrorMessage());
            }

            return favouriteCount;

        });
        return futurefavouriteRetweet;
    }

}
