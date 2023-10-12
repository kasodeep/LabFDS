package study;

import java.util.Stack;

public class DelimiterMatching {

    public static void main(String[] args) {
        String s = "({}[])";
        int n = s.length();

        boolean flag = false;
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[')
                st.push(ch);
            else {
                if (st.isEmpty()) {
                    flag = true;
                    break;
                }
                if ((st.peek() == '(' && ch == ')') || (st.peek() == '{' && ch == '}') || (st.peek() == '[' && ch == ']'))
                    st.pop();
                else {
                    flag = true;
                    break;
                }
            }
        }
        System.out.println(st.size() == 0 && !flag);
    }
}
