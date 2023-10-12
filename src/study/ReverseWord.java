package study;

import java.util.Stack;

public class ReverseWord {

    public static void main(String[] args) {
        String s = "Deep";
        StringBuilder ans = new StringBuilder();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            st.push(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            ans.append(st.pop());
        }
        System.out.println(ans);
    }
}
