package by.shumchyk.logic;

import java.util.*;

public class Parser {

    public static Map<String, List<String>> parse(String[] args) {

        ArrayList<String> keyWordsList = new ArrayList<>();
        keyWordsList.add("SELECT");
        keyWordsList.add("FROM");
        keyWordsList.add("WHERE");
        keyWordsList.add("AND");
        keyWordsList.add("OR");
        keyWordsList.add("LIKE");

        ArrayList<String> id = new ArrayList<>();
        Map smth = new HashMap();
        String key = "";

        for (int i = 0; i < args.length; i++) {
            if (keyWordsList.contains(args[i])) {
                if (!id.isEmpty()) {
                    smth.put(key, id);
                }
                key = args[i];
                id = new ArrayList<>();
                continue;
            }
            id.add(args[i]);
        }
        smth.put(key, id);
        return smth;

    }

    public static Map<String, List<String>> parse(String str) {

        ArrayList<String> keyWordsList = new ArrayList<>();

        keyWordsList.add("SELECT");
        keyWordsList.add("FROM");
        keyWordsList.add("WHERE");
        keyWordsList.add("AND");
        keyWordsList.add("LIKE");
        keyWordsList.add("LIMIT");
        keyWordsList.add("OFFSET");

        List<String> id = new LinkedList<>();
        Map smth = new HashMap();
        String key = "";

        for (String token : str.split("\\s*(\\s|,|\\.)\\s*")) {
            if (keyWordsList.contains(token)) {
                if (!id.isEmpty()) {
                    smth.put(key, id);
                }
                key = token;
                id = new LinkedList<>();
                continue;
            }
            id.add(token);
        }
        smth.put(key, id);

        return smth;

    }
}

