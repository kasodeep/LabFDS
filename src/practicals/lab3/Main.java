package practicals.lab3;

public class Main {

    public static void main(String[] args) {

        // Application of Stack.
        StringBuilder text = new StringBuilder("<html> " + "<p> This is my file. </p> " + "</html>");
        ArrayStack<String> st = new ArrayStack<>(text.length());

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '<') {
                if (text.charAt(i + 1) != '/') {

                    // Find the tag name.
                    int temp = i;
                    while (i < text.length() && text.charAt(i) != '>') i++;

                    // Push onto the ArrayStack.
                    st.push(text.substring(temp + 1, i));
                } else {
                    int temp = i + 1;
                    while (i < text.length() && text.charAt(i) != '>') i++;
                    String check = text.substring(temp + 1, i);

                    if (st.isEmpty() || !st.top().equals(check)) {
                        System.out.println("Not Proper");
                        break;
                    }
                    st.pop();
                }
            }
        }

        if (st.size() == 0) {
            System.out.println("Proper");
        }

        // Application of Queue.
        ArrayQueue<Task> q = new ArrayQueue<>(4);
        q.enqueue(new Task(1, 3));
        q.enqueue(new Task(2, 2));
        q.enqueue(new Task(3, 5));
        q.enqueue(new Task(4, 1));

        while (!q.isEmpty()) {
            Task temp = q.dequeue();
            temp.time--;
            if (temp.time == 0) {
                System.out.println("Task " + temp.name + " is completed.");
            } else {
                q.enqueue(temp);
            }
        }
    }

    // Inner class Task for saving the time and name of each task.
    public static class Task {
        int name;
        int time;

        public Task(int name, int time) {
            this.name = name;
            this.time = time;
        }
    }
}
