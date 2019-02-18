import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Main {

    public static class Item {
        public long time;
        public String data;

        public Item(long time, String data) {
            this.time = time;
            this.data = data;
        }
    }

    public static void main(String[] args) {
        long first = new Long("102774905591") ;
        long second = new Long("1027749052416");
        Deque<Item> data = new ArrayDeque<>();
        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int idx = line.indexOf("Time: ");
                long time = Long.parseLong(line.substring(idx + 6, line.length()));
                Item item = new Item(time, line);
                data.push(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long start = System.nanoTime();
        while (true) {
            long timePassed = System.nanoTime() - start;
            while (timePassed > data.peekLast().time) {
                Item output = data.pollLast();
                try {
                    System.out.println(output.data + " With a Lag of value: " + (System.nanoTime() - start - output.time));
                } catch (NullPointerException e) {
                    break;
                }
            }
        }
    }
}

