import java.io.IOException;
import java.net.URI;

class MyHandler implements URLHandler {
    String word;
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("%s", word);
        }
        System.out.println("Path: " + url.getPath());
        if (url.getPath().contains("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                if(word == null) {
                word = parameters[1] + System.lineSeparator();
                return String.format(word);
                }
                else {
                    word += parameters[1] + System.lineSeparator();
                    return String.format(word);
                }
            }
        }
        return "404 Not Found!";
        }
    }

public class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new MyHandler());
    }
}

