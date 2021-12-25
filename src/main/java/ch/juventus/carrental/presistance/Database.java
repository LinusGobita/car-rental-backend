package ch.juventus.carrental.presistance;

import java.io.IOException;

public interface Database {
    String loadHelloWorldGreeting();

    String dbAsString() throws IOException;
}
