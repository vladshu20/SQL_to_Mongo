package by.shumchyk.logic;

import java.util.List;
import java.util.Map;

public class Translator {
    public static void translator(Map<String, List<String>> cmdSQL) {
        StringBuilder query = new StringBuilder("db.");




        //where(cmdSQL,select(cmdSQL,from(cmdSQL,query)));
        from(cmdSQL, query);
        select(cmdSQL, query);
        where(cmdSQL, query);
        limit(cmdSQL, query);
        offset(cmdSQL, query);


        System.out.println(query);



    }

    private static StringBuilder limit(Map<String, List<String>> cmdSQL, StringBuilder query) {
        return query.append(".limit(").append(cmdSQL.get("LIMIT").iterator().next().toString()).append(")");
    }

    private static StringBuilder offset(Map<String, List<String>> cmdSQL, StringBuilder query) {
        return query.append(".skip(").append(cmdSQL.get("OFFSET").iterator().next().toString()).append(")");
    }

    private static StringBuilder from(Map<String, List<String>> cmdSQL, StringBuilder query) {
        for (String val : cmdSQL.get("FROM")
        ) {
            query.append(val).append(".");
        }
        return query;
    }

    private static StringBuilder select(Map<String, List<String>> cmdSQL, StringBuilder query) {
        int counter = 0;

        if (query.toString().matches("^(db)\\.\\w+\\.")) {
            query.append("find()");
        }

        query.insert(query.indexOf("(") + 1, "{},{}");


        for (String val : cmdSQL.get("SELECT")) {
            if (val.equals("*")) break;

            query.insert(query.lastIndexOf("}"), val + ":1");
            if (counter < cmdSQL.get("SELECT").size()) {
                query.insert(query.indexOf(val) + val.length() + ":1".length(), ",");
            }
            counter++;

        }
        query.insert(query.lastIndexOf("}"), "_id:0");
        return query;
    }

    private static StringBuilder where(Map<String, List<String>> cmdSQL, StringBuilder query) {


        int counter = 0;

        if (cmdSQL.containsKey("WHERE")) {
            for (String val : cmdSQL.get("WHERE")
            ) {

                query.insert(query.indexOf("}"), val);

                //counter++;
                if (++counter < cmdSQL.get("WHERE").size()) {
                    query.insert(query.indexOf(val) + val.length(), ",");
                }


                switch (val){
                    case ">":
                        query.replace(query.indexOf("}") - val.length()-2, query.indexOf("}"), ":{$gt:}");
                        break;
                    case "<":
                        query.replace(query.indexOf("}") - val.length()-2, query.indexOf("}"), ":{$lt:}");
                        break;
                    case "=":
                        query.replace(query.indexOf("}") - val.length()-2, query.indexOf("}"), ":");
                        break;
                    case ">=":
                        query.replace(query.indexOf("}") - val.length()-2, query.indexOf("}"), ":{$gte:}");
                        break;
                    case "<=":
                        query.replace(query.indexOf("}") - val.length()-2, query.indexOf("}"), ":{$lte:}");
                        break;
                }




            }


        }
        return query;
    }
}
