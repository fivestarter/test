package ru.startsev.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/breaker")
public class Breaker {

    private final BookService bookService;

    @Autowired
    public Breaker(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("")
    public String test() {
        return bookService.readList();
    }
}
