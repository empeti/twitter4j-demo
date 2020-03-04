package com.epam.bigdata.training.twitter4j;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws TwitterException {
        /*Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("bigdata");
        QueryResult results = twitter.search(query);
        List<Status> tweets = results.getTweets();

        for (Status tweet : tweets){
            System.out.println(tweet.getUser().getName() + ": " + tweet.getText() + " ::::: " );
            System.out.println("---------------------------------------------------------");
        }*/

        // Stream

        FilterQuery filterQuery = new FilterQuery().track("bigdata");

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.onStatus(status -> System.out.println(
                status.getText()
                + "\n" +status.getGeoLocation().toString()
                + "\n" + printHashtagEntities(status)
                + "\n" + "----------------------------------------------------------"));
        twitterStream.filter(filterQuery);
        twitterStream.sample();
    }

    private static String printHashtagEntities(Status status) {
        StringBuilder stringBuilder = new StringBuilder();
        for (HashtagEntity hashtag : status.getHashtagEntities()){
            stringBuilder.append(hashtag.getText()).append(", ");
        }
        return stringBuilder.toString();
    }
}
