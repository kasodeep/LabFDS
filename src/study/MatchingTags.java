package study;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

public class MatchingTags {

    private static final HashSet<String> set = new HashSet<>();
    private static final String[] arr = {"area", "base", "br", "col", "embed", "hr", "img", "input", "link", "meta", "param", "source", "track", "wbr"};

    private static void selfClosingCreate() {
        Collections.addAll(set, arr);
    }

    public static void main(String[] args) {

        // To store the contents.
        StringBuilder text = new StringBuilder();
        Stack<String> st = new Stack<>();
        selfClosingCreate();

        try {
            // Opening a File.
            File file_create = new File("D:\\Java\\LabFDS\\src\\study\\text.html");
            FileInputStream fin = new FileInputStream(file_create);
            int ch;
            while ((ch = fin.read()) != -1) {
                text.append((char) ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean ans = true;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '<') {
                if (text.charAt(i + 1) != '/') {

                    // Find the tag name.
                    int index = i;
                    while (i < text.length() && (text.charAt(i) != '>' && text.charAt(i) != ' ')) i++;
                    String temp = text.substring(index + 1, i);

                    // Check for self-closing.
                    while(i < text.length() && (text.charAt(i) != '>')) i++;
                    if (i < text.length() && text.charAt(i - 1) == '/') {
                        if (!set.contains(temp)) {
                            ans = false;
                            break;
                        }
                        continue;
                    }

                    // Push onto the stack.
                    st.push(temp);
                } else {
                    int temp = i + 1;
                    while (i < text.length() && text.charAt(i) != '>') i++;
                    String check = text.substring(temp + 1, i);

                    if (st.isEmpty() || !st.peek().equals(check)) {
                        ans = false;
                        break;
                    }
                    st.pop();
                }
            }
        }

        if (ans && st.size() == 0) {
            System.out.println("Proper");
        }else{
            System.out.println("Not Proper");
        }
    }
}
