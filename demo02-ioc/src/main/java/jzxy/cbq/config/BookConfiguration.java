package jzxy.cbq.config;

import jzxy.cbq.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: cbq1024
 * @description: BookConfiguration
 * @since 2024/7/8 下午6:11
 */
@Configuration
public class BookConfiguration {

    @Bean
    public Book initBook() {
        Book book = new Book();
        book.setName("明朝那些事儿");
        book.setAuthor("当年明月");
        book.setPrice(100);
        return book;
    }

}
