package s13o.test.search.index.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s13o.test.search.index.api.DocStore;
import s13o.test.search.index.api.Index;
import s13o.test.search.index.api.TokFactory;
import s13o.test.search.index.api.Vocabulary;

/**
 * @author {@link "mailto:roman.solodovnichenko@gmail.com" "romanso"}
 * @since 2/18/2018
 */
@Configuration
public class SimpleConfig {

    @Bean(name = "tokFactory")
    public TokFactory tokFactory(){
        return new SimpleTokFactory();
    }

    @Bean(name = "vocabulary")
    public Vocabulary toVocabulary(){
        return new SimpleVocabulary();
    }

    @Bean(name = "docStore")
    public DocStore toDocStore(){
        return new SimpleDocStore();
    }

    @Bean(name = "index")
    public Index toIndex(DocStore storage, Vocabulary vocabulary, TokFactory factory){
        return new SimpleIndex(storage, vocabulary, factory);
    }

}
