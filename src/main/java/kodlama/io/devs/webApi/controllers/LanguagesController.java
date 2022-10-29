package kodlama.io.devs.webApi.controllers;

import kodlama.io.devs.business.abstracts.LanguageService;
import kodlama.io.devs.entities.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/languages")
public class LanguagesController {
    private final LanguageService languageService;
    
    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/add")
    public Language add(@RequestBody Language language) throws Exception {
        return languageService.add(language);
    }

    @GetMapping("/getAll")
    public List<Language> getAll() {
        return languageService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Language getById(@PathVariable int id) throws Exception {
        return languageService.getById(id);
    }

    @PatchMapping(value = "/update")
    public Language update(@RequestBody Language language) {
        return languageService.update(language);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable int id) {
        return languageService.delete(id);
    }
}
