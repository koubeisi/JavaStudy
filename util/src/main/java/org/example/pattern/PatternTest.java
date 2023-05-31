package org.example.pattern;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author KouBeisi
 * @since 2021/9/24
 */
public class PatternTest {


    @Test
    void test0(){
        String regex = "^once";
        Pattern pattern = Pattern.compile(regex);
        final Matcher matcher0 = pattern.matcher("once");
        final Matcher matcher1 = pattern.matcher("once is");

        System.out.println(matcher0.matches());
        System.out.println(matcher1.matches());
    }

    @Test
    void test(){
        String reg = "^1[0-9]{10}";
        Pattern pattern = Pattern.compile(reg);
        final Matcher matcher = pattern.matcher("13347697035");
        System.out.println(matcher.matches());
    }
}
