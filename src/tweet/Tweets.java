package tweet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tweets {

    private static String[] tweets = new String[] {
            "is it reasonable to talk about rivest so much?",
            "@rivest talk in 30 minutes #hype",
            "@alyssap @b_bitdiddle send comments to rivest@mit.edu",
    };
    
    
    public static void main(String[] args) {
        //
        // grammar TODO
        // 
        String regex = "rivest";        
        Pattern pattern = Pattern.compile(regex);

        for (String string : tweets) {
            System.out.println(string);
            Matcher matcher = pattern.matcher(string);
            while (matcher.find()) {
                System.out.println("\t" + matcher.group());
            }
        }
    }
}
