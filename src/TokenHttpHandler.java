import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class TokenHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        String request = httpExchange.getRequestURI().toString().replace("/", "");
        HttpResponseSender httpResponseSender = new HttpResponseSender();

        String response;

        if (request.length() > 0) {
            if (request.matches("[0-9]+")) {
                try {
                    int length = Integer.parseInt(request);
                    TokenGenerator tokenGenerator = new TokenGenerator();
                    response = tokenGenerator.getRandomHash(length);
                } catch (NumberFormatException e) {
                    response = "Only integers allowed!";
                }

            } else {
                response = "Only numbers allowed!";
            }
        } else {
            response = "Please specify the length of your token via http://random-token.xyz/LENGTH";
        }

        httpResponseSender.sendPlainText(httpExchange, response);
    }
}
