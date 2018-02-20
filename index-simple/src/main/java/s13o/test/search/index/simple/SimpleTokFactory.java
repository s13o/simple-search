package s13o.test.search.index.simple;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import s13o.test.search.index.api.Document;
import s13o.test.search.index.api.TokFactory;
import s13o.test.search.index.api.Token;
import s13o.test.search.index.api.Tokenizer;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@Component
public class SimpleTokFactory implements TokFactory {
    /**
     * Here we should check content type of the Document and choose most appropriate Tokenizer.
     * The only "plain text"" is supported by current implementation below.
     */
    @Override
    public Tokenizer create(@NotNull Document document) {
        for (final CharSequence content = document.getContent(); content != null && content.length() > 0; ) {
            // this one should be faster than content.split("\\s")
            // and potentially less memory could be needed for huge text
            // (actually some InputStream should be used instead of CharSequence but for this)
            return (d) -> {
                final Enumeration<Object> en = new StringTokenizer(d.getContent().toString(), " \t\n\r\f", true);
                return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                new Iterator<Token>() {
                                    private int offset = 0;

                                    public Token next() {
                                        String str = (String) en.nextElement();
                                        int len = str.length();
                                        try {
                                            return new Token(offset, str.trim().toLowerCase());
                                        } finally {
                                            offset += len;
                                        }
                                    }

                                    public boolean hasNext() {
                                        return en.hasMoreElements();
                                    }
                                },
                                Spliterator.ORDERED), false)
                        .filter((t) -> !StringUtils.isEmpty(t.getToken()));
            };
        }
        return (d) -> Stream.empty();
    }
}
