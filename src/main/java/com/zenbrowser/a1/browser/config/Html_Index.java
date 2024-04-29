package com.zenbrowser.a1.browser.config;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Html_Index {
    private final String connectionString = "jdbc:sqlite:Site_Index.db";
    private Html_Record Doc;
    private StanfordCoreNLP pipeline;
    static Logger logger = Logger.getLogger(Html_Index.class.getName());
    public static Set<String> STOP_WORDS = Set.of("a", "an", "and", "are", "as", "at", "be", "but", "by",
            "for", "if", "in", "into", "is", "it",
            "no", "not", "of", "on", "or", "s", "such",
            "t", "that", "the", "their", "then", "there", "these",
            "they", "this", "to", "was", "will", "with");
    public Html_Index(Html_Record Doc) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, split, pos, lemma");
        this.Doc = Doc;
        this.pipeline = new StanfordCoreNLP(props);
        index();
    }

    public void index() {
        List<String> tokens = this.tokenize(this.Doc.getBody());

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Site_Index.db");
            conn.setAutoCommit(false); // Start transaction
            for (String token : tokens) {
                try (PreparedStatement stmt = conn.prepareStatement("SELECT urls FROM tokens WHERE token = ?")) {
                    stmt.setString(1, token);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String urls = rs.getString("urls") + "," + Doc; // Assuming URLs are stored as comma-separated values
                        try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE tokens SET urls = ? WHERE token = ?")) {
                            updateStmt.setString(1, urls);
                            updateStmt.setString(2, token);
                            updateStmt.executeUpdate();
                        }
                    } else {
                        try (PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO tokens (token, urls) VALUES (?, ?)")) {
                            insertStmt.setString(1, token);
                            insertStmt.setString(2, Doc);
                            insertStmt.executeUpdate();
                        }
                    }
                }
            }
            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> tokenize(String corpus) {
        HashSet<String> tokenSet = new HashSet<>();
        List<String> tokenized = new ArrayList<>();

        Annotation annotation = new Annotation(corpus.toLowerCase());
        pipeline.annotate(annotation);

        for (CoreLabel token : annotation.get(CoreAnnotations.TokensAnnotation.class)) {
            String tk = token.get(CoreAnnotations.LemmaAnnotation.class);
            if (!tokenSet.contains(tk) && !invalidToken(tk)) {
                tokenized.add(tk);
                tokenSet.add(tk);
            }
        }

        return tokenized;
    }

    private boolean invalidToken(String token) {
        return STOP_WORDS.contains(token) || token.matches("[\\p{Punct}\\d]+");
    }

}
