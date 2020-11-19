package pl.coderslab.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        Book book = new Book();
        book.setTitle("Thinking in Java");
        book.setDescription("Bruce Eckel");
        bookDao.save(book);
        return "Id dodanej książki to:"
                + book.getId();

    }
    @RequestMapping("/add")
    @ResponseBody
    public String addBook(){
        Publisher pub =publisherDao.findById(1);
        Author author= authorDao.findById(2);
        Author author2= authorDao.findById(3);
        List<Author> authorList = Arrays.asList(author, author2);
        Book book = new Book();
        book.setTitle("Wojna i pokój");
        book.setDescription("Epopeja rosyjska");
        book.setPublisher(pub);
        book.setAuthorLis(authorList);
        bookDao.save(book);

        return book.toString();
    }

    @RequestMapping("/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }
    @RequestMapping("/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";}

    @RequestMapping("/get/all")
    @ResponseBody
    public String getAll() {
        bookDao.getAll().forEach(b -> System.out.println(b.getTitle()));
        return "ok";
    }

    @RequestMapping("/getByPub/{pub}")
    @ResponseBody
    public String getAllPub(@PathVariable long pub) {
        Publisher publisher = publisherDao.findById(pub);
        bookDao.getAll(publisher).forEach(b -> System.out.println(b.getTitle()));
        return "ok lista book by pub";}

    @RequestMapping("/getByAut/{aut}")
    @ResponseBody
    public String getAllAut(@PathVariable long aut) {
        Author author = authorDao.findById(aut);
        bookDao.getAll(author).forEach(b -> System.out.println(b.getTitle()));
        return "ok lista book by aut "+ author.getLastName()+" miłość";}

}
