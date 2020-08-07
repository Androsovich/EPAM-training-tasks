package com.epam.winter.java.lab.controller;

import com.epam.winter.java.lab.model.Author;
import com.epam.winter.java.lab.model.Book;
import com.epam.winter.java.lab.model.library.UserInfo;
import com.epam.winter.java.lab.service.book.BookService;
import com.epam.winter.java.lab.service.library.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class BookController {

    private LibraryService libraryService;
    private BookService bookService;

    @Resource
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Resource
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/user/booksView", method = GET)
    public String viewBooks(Model model) {
        Map<Book, Author> bookAuthorMap = libraryService.getBooks();
        model.addAttribute("mapBooks", bookAuthorMap);
        return "book/booksView";
    }

    @RequestMapping(value = "/security/add/book", method = GET)
    public String viewBooksAddUser(@RequestParam Integer cardId, Model model) {
        UserInfo userInfo = libraryService.getUserInfo(cardId);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("books", bookService.getAll());
        return "book/addUserBook";
    }

    @RequestMapping(value = "/security/add/addBook", method = GET)
    public String addBookUser(@RequestParam Integer cardId, Integer bookId) {
        libraryService.addBookToUser(cardId, bookId);
        return "redirect:/home";
    }

    @RequestMapping(value = "/security/delivery", method = GET)
    public String deliveryBook(@RequestParam Integer deliveryId, Integer bookId) {
        libraryService.deliveryBook(deliveryId, bookId);
        return "redirect:/home";
    }
}
