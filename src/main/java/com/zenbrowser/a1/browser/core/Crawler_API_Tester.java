
package com.zenbrowser.a1.browser.core;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Crawler_API_Tester {
    public static void main(String[] args){
        ArrayList<String> url_list = new ArrayList<>();

        try {
            File topmill = new File("JDevBrowser/src/main/resources/top-1m.txt");
            Scanner myReader = new Scanner(topmill);

            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                url_list.add(data);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("An error with reading the file occurred.");
            e.printStackTrace();
        }


        ArrayList<Crawler_API> bots = new ArrayList<Crawler_API>();
        bots.add(new Crawler_API(1, url_list));
        bots.add(new Crawler_API(2, url_list));
        bots.add(new Crawler_API(3, url_list));
        bots.add(new Crawler_API(4, url_list));

        for (Crawler_API w : bots){
            try {
                w.getThread().join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
