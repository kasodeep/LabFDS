package study;

import java.util.Stack;

public class MatchingTags {

    public static void main(String[] args) {

        StringBuilder text = new StringBuilder("<html> " + "<p> This is my file. </p> " + "</html>");
        Stack<String> st = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == '<'){
                if(text.charAt(i + 1) != '/'){

                    // Find the tag name.
                    int temp = i;
                    while(i < text.length() && text.charAt(i) != '>') i++;

                    // Push onto the stack.
                    st.push(text.substring(temp + 1, i));
                }else{
                    int temp = i + 1;
                    while(i < text.length() && text.charAt(i) != '>') i++;
                    String check = text.substring(temp +1, i);

                    if(st.isEmpty() || !st.peek().equals(check)){
                        System.out.println("Not Proper");
                        break;
                    }
                    st.pop();
                }
            }
        }

        if(st.size() == 0){
            System.out.println("Proper");
        }
    }
}
