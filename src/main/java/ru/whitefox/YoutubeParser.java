package ru.whitefox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class YoutubeParser {

    static Image image;
    static String subscribersCount;
    static String chanelTitle;

    public static void update(){
        try {
            Document document = Jsoup.connect(Options.url).get();

            int indexAvatar = document.body().toString().indexOf("avatar");
            int indexAvatarUrlStart = document.body().toString().indexOf("http", indexAvatar);
            int indexAvatarUrlEnd = document.body().toString().indexOf("\"", indexAvatarUrlStart);
            URL avatarUrl = new URL(document.body().toString().substring(indexAvatarUrlStart, indexAvatarUrlEnd));
            image = ImageIO.read(avatarUrl);

            int indexSubscribersCountText = document.body().toString().indexOf("subscriberCountText");
            int indexSubscribersStart = document.body().toString().indexOf("simpleText", indexSubscribersCountText) + 13;
            int indexSubscribersEnd = document.body().toString().indexOf("\"", indexSubscribersStart);
            subscribersCount = document.body().toString().substring(indexSubscribersStart, indexSubscribersEnd);

            int indexChanelMetadata = document.body().toString().indexOf("channelMetadataRenderer");
            int indexChanelTitleStart = document.body().toString().indexOf("title", indexChanelMetadata) + 8;
            int indexChanelTitleEnd = document.body().toString().indexOf("\"", indexChanelTitleStart);
            chanelTitle = document.body().toString().substring(indexChanelTitleStart, indexChanelTitleEnd);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
