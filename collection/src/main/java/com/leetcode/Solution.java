package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author koubs
 * @date 2021/4/4
 */
public class Solution {

    public static void main(String[] args) {
    }

    public boolean isUnique(String str) {
        final char[] chars = str.toCharArray();
        Set<Character> set = new HashSet<>(8);
        for (char s : chars) {
            if (!set.add(s)) {
                return false;
            }
        }
        return true;
    }
}
