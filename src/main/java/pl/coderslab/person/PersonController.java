package pl.coderslab.person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
public class PersonController {


    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }
    @RequestMapping (value = "/add", method = RequestMethod.GET)
    public String addPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "person/add";
    }
    @RequestMapping (value = "/add", method = RequestMethod.POST)
    public void addPerson(Person person) {
        personDao.save(person);
        System.out.println(person);
    }


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        PersonDetails personDetails = new PersonDetails();
        Person person = new Person();
        person.setLogin("Bruce");
        person.setEmail("poczta@op.pl");
        person.setPassword("Eckel");
        personDetails.setCity("Warszawa");
        personDetails.setFirstName("Michał");
        personDetails.setLastName("Włodar");
        personDetailsDao.save(personDetails);
//        person.setPersonDetails(personDetails);
        personDao.save(person);

        return "Id dodanej osoby to:"
                + person.getId();

    }
//
//        @RequestMapping("/update/{id}/{lastName}")
//        @ResponseBody
//        public String updateAuthor(@PathVariable long id, @PathVariable String lastName) {
//            Author author = authorDao.findById(id);
//            author.setLastName(lastName);
//            authorDao.update(author);
//            return author.toString();
//        }
//
//        @RequestMapping("/get/{id}")
//        @ResponseBody
//        public String getAuthor(@PathVariable long id) {
//            Author author = authorDao.findById(id);
//            return author.toString();
//        }
//
//        @RequestMapping("/delete/{id}")
//        @ResponseBody
//        public String deleteAuthor(@PathVariable long id) {
//            Author author = authorDao.findById(id);
//            authorDao.delete(author);
//            return "deleted";
//        }
}
