package pl.coderslab.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/author")
public class AuthorController {


        private final AuthorDao authorDao;

        public AuthorController(AuthorDao authorDao) {
            this.authorDao = authorDao;
        }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RedirectView addForm(Author author){
        System.out.println(author);
        authorDao.save(author);
        return new RedirectView("/author/all");}

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/form";
    }


    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String getBook(@PathVariable long id, Model model) {
        model.addAttribute("author", authorDao.findById(id));
        return "author/edit";}

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public RedirectView editBook(Author author) {
        authorDao.update(author);
        return new RedirectView("/author/all");
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public RedirectView deleteBook(@PathVariable long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return new RedirectView("/author/all");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allBooks(Model model){
        model.addAttribute("authors", authorDao.getAll());
        return "author/list";
    }


    }
