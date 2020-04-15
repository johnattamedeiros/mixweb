package com.mixapp.app.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class SteamScrapService {

    public String getSteamImage(String steamUrl) throws IOException {
        Document doc = Jsoup.connect(steamUrl).get();
        String imageUrl = doc.getElementsByClass("playerAvatarAutoSizeInner").select("img").attr("src");
        return imageUrl;
    }

}
