package pl.coderslab.animals;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalDao animalDao;

    public AnimalController(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @RequestMapping("/save")
    @ResponseBody
    public String saveAnimal() {
        Animal azor = new Animal();
        azor.setName("azor");
        azor.setAge(12);
        animalDao.save(azor);
        return "ok" + " id:" + azor.getId();
    }

}
