package com.zenbrowser.a1.browser.core;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.zenbrowser.a1.browser.config.Html_Index;
import com.zenbrowser.a1.browser.config.Html_Record;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crawler_API implements Runnable {
    private final int Crawler_ID;
    private ArrayList<String> url_list;
    private final Thread thread;
    private final ReentrantLock mutex = new ReentrantLock();
    static Logger logger = Logger.getLogger(Crawler_API.class.getName());

    public Crawler_API(int Crawler_ID, ArrayList<String> url_list) {
        this.url_list = url_list;
        this.Crawler_ID = Crawler_ID;
        this.thread = new Thread(this);
        this.thread.start();

        logger.log(Level.INFO, "Thread: " + thread.getId() + " Crawler " + Crawler_ID + " Created.");
    }


    @Override
    public void run(){crawl();}
    private void crawl() {
        while(!url_list.isEmpty()) {
            String url;
            try {
                mutex.lock();
                url = "https://" + url_list.get(0);
                url_list.remove(0);
            } finally {
                mutex.unlock();
            }

            logger.log(Level.INFO, "Crawler " + this.Crawler_ID + ": Fetching page from " + url);
            HtmlPage page = request(url);
            if (page != null) {
                Html_Record doc = buildDocument(page);
                Html_Index index = new Html_Index(doc);
            }
        }

    }

    private HtmlPage request(String url) {
        HtmlPage Page = null;

        try (WebClient webClient = new WebClient()) {
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            Page = webClient.getPage(url);
            }
        catch (IOException e) {
            logger.log(Level.WARNING, "Crawler " + Crawler_ID + ": Unable to fetch the page from " + url);
        }

        return Page;
    }

    private Html_Record buildDocument(HtmlPage htmlPage){
        return new Html_Record(UUID.randomUUID().toString(), htmlPage);
    }

    public Thread getThread() {
        return thread;
    }
}
