import logic.Parser;
import logic.Translator;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try {
            String str = args[0];
            Map <String, List<String>> values = Parser.parse(str);
            Translator.translator(values);
        } catch (Exception e) {
            System.out.println("oops, there was an error");
            System.out.println("probably,you haven't passed any string enclosed with \"\" or just any string");

           //e.printStackTrace();
        }

        //test cases

        //String str = "SELECT name,surname FROM class WHERE  mark , qwe LIMIT 5 OFFSET 2";
        //String str = "SELECT * FROM class WHERE  mark , qwe LIMIT 5 OFFSET 2";
        //String str = "SELECT * FROM class WHERE  mark , qwe LIMIT 5 ";
        //String str = "SELECT * FROM class WHERE  mark > 3 ";
        //String str = "SELECT * FROM class";
        //String str = "SELECT name  FROM class";




    }
}
