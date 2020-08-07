package com.epam.winter.java.lab.service.library;

import com.epam.winter.java.lab.model.*;
import com.epam.winter.java.lab.model.library.CardUser;
import com.epam.winter.java.lab.model.library.UserInfo;
import com.epam.winter.java.lab.service.author.AuthorService;
import com.epam.winter.java.lab.service.book.BookService;
import com.epam.winter.java.lab.service.card.CardService;
import com.epam.winter.java.lab.service.delivery.DeliveryService;
import com.epam.winter.java.lab.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class LibraryService {
    private AuthorService authorService;
    private DeliveryService deliveryService;
    private CardService cardService;
    private UserService userService;
    private BookService bookService;

    @Resource
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Resource
    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @Resource
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public List<CardUser> getAllCards() {
        List<CardUser> cardUsers = new ArrayList<>();
        List<SubscriptionCard> cards = cardService.getAll();
        cards.forEach(s -> {
            User user = userService.getById(s.getUserId());
            cardUsers.add(new CardUser(user, s));
        });
        return cardUsers;
    }

    public UserInfo getUserInfo(Integer cardId) {
        User user = userService.getUserByCardId(cardId);
        List<Delivery> deliveries = deliveryService.getDeliveryByCard(cardId);
        Map<Delivery, Book> mapBooks = new HashMap<>();
        deliveries.forEach(s -> mapBooks.put(s, bookService.getBookByDeliveryId(s.getId())));
        return new UserInfo(user, cardId, mapBooks);
    }

    public Map<Book, Author> getBooks() {
        List<Book> books = bookService.getAll();
        return IntStream.range(0, books.size())
                .boxed()
                .collect(Collectors.toMap(books::get, i -> authorService.getById(books.get(i).getAuthorId())));
    }

    public void addUser(User user) {
        final int userId = userService.save(user);
        cardService.save(new SubscriptionCard(userId, LocalDate.now()));
    }

    public void addBookToUser(Integer idCard, Integer idBook) {
        bookService.reduceAmount(idBook);
        deliveryService.save(new Delivery(idCard, idBook, LocalDate.now(), null));
    }

    public void deliveryBook(Integer deliveryId, Integer bookId) {
        deliveryService.deliveryBook(deliveryId);
        bookService.increaseAmount(bookId);
    }
}

