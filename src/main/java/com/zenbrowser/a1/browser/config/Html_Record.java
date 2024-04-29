package com.zenbrowser.a1.browser.config;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Html_Record {
    private String ID;
    private String URL;
    private String Header;
    private String Content;

    public Html_Record(String Id, HtmlPage htmlpage) {
        this.ID = Id;
        this.Header = htmlpage.getTitleText();
        this.URL = htmlpage.getBaseURI();
        this.Content = htmlpage.getBody().asNormalizedText();
    }

    public String getID() {
        return this.ID;
    }

    public String getURL() {
        return this.URL;
    }

    public String getHeader() {
        return this.Header;
    }

    public String getBody() {
        return this.Content;
    }
}
