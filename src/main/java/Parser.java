import dao.RequestDAO;
import dao.StatisticDAO;
import javafx.collections.ObservableList;
import model.Request;
import model.Statistic;
import utils.CollectionHelper;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.IntStream;

public class Parser {

    Map result;

    public Parser() {
        this.result = new HashMap<String, Integer>();
    }

    static final int START = 0;
    static final int KEYWORD = 1;
    static final int DIVIDER = 2;
    static final int END = 3;

    int state = START;
    static private HashSet<Character> symbols /*= new HashSet<Character>()*/;

    static {
        symbols = new HashSet<Character>();
        symbols.add('`');
        symbols.add('~');
        symbols.add('!');
        symbols.add('@');
        symbols.add('#');
        symbols.add('$');
        symbols.add('%');
        symbols.add('^');
        symbols.add('<');
        symbols.add('>');
        symbols.add(' ');
        symbols.add('&');
        symbols.add('*');
        symbols.add('(');
        symbols.add(')');
        symbols.add('-');
        symbols.add('_');
        symbols.add('–');
        symbols.add('—');
        symbols.add('/');
        symbols.add('|');
        symbols.add('\\');
        symbols.add('"');
        symbols.add('\'');
        symbols.add('+');
        symbols.add(',');
        symbols.add('.');
        symbols.add('[');
        symbols.add(']');
        symbols.add('{');
        symbols.add('}');
        symbols.add(':');
        symbols.add(';');
        symbols.add('?');
        symbols.add('\n');
        symbols.add('\r');
        symbols.add('\t');
        System.out.println("static");
    }

    public ObservableList read(String file, String url) throws IOException {

        IntStream fileStream = file.chars();
        int symbol = 0;
        StringBuilder word = new StringBuilder();
        PrimitiveIterator.OfInt iterator = fileStream.iterator();

        while (state != END) {
            symbol = (char) iterator.nextInt();

            switch (state) {
                case START:
                    if (!iterator.hasNext()) {
                        state = END;
                    } else if (symbols.contains((char) symbol)) {
                        state = DIVIDER;
                    } else {
                        state = KEYWORD;
                        word.append((char) symbol);

                    }

                    break;
                case KEYWORD:
                    if (!iterator.hasNext()) {
                        state = END;
                        addWord(word);
                    } else if (symbols.contains((char) symbol)) {
                        addWord(word);
                        word.delete(0, word.length());
                        state = DIVIDER;
                    } else {
                        word.append((char) symbol);
                    }
                    break;
                case DIVIDER:
                    if (!iterator.hasNext()) {
                        state = END;
                    } else if (!symbols.contains((char) symbol)) {
                        state = KEYWORD;
                        word.append((char) symbol);
                    }
                    break;
            }
        }
        result.entrySet().
                stream()
                .
                        forEach(System.out::println);
        long unixTimestamp = Instant.now().getEpochSecond();
        Request request = new Request(url, unixTimestamp);
        RequestDAO requestDAO = new RequestDAO();
        requestDAO.insert(request);

        return CollectionHelper.mapToObservableList(result);
    }

    void addWord(StringBuilder word) {
        if (result.containsKey(word.toString())) {
            result.put(word.toString(), (Integer) result.get(word.toString()) + 1);
        } else {
            result.put(word.toString(), 1);
        }
    }
}