package pl.coderslab.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/bookform")
public class BookFormController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookFormController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @ModelAttribute("publisher")
    public List<Publisher> getPublisher(){
        return publisherDao.getAll();
    }
    @ModelAttribute("authors")
    public List<Author> getAuthors(){
        return authorDao.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RedirectView addForm(Book book){
        System.out.println(book);
        bookDao.save(book);
        return new RedirectView("/bookform/all");}

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/form";
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allBooks(Model model){
        model.addAttribute("books", bookDao.getAll());
        return "book/list";
    }
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String getBook(@PathVariable long id, Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "book/edit";}

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public RedirectView editBook(Book book) {
        bookDao.update(book);
        return new RedirectView("/bookform/all");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public RedirectView deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return new RedirectView("/bookform/all");
    }

}
