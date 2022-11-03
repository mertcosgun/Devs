package kodlama.io.devs.webApi.controllers;

import kodlama.io.devs.business.abstracts.LanguageService;
import kodlama.io.devs.business.requests.CreateLanguageRequest;
import kodlama.io.devs.business.requests.UpdateLanguageRequest;
import kodlama.io.devs.business.responses.LanguagesResponse;
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

    @PostMapping(value = "/add")
    public LanguagesResponse add(CreateLanguageRequest language) throws Exception {
        return languageService.add(language);
    }

    @GetMapping(value = "/getAll")
    public List<LanguagesResponse> getAll() {
        return languageService.getAll();
    }

    @GetMapping(value = "/getById/{id}")
    public LanguagesResponse getById(@PathVariable() int id) throws Exception {
        return languageService.getById(id);
    }

    @PatchMapping(value = "/update")
    public LanguagesResponse update(UpdateLanguageRequest updateLanguageRequest) throws Exception {
        return languageService.update(updateLanguageRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable() int id) throws Exception {
        languageService.delete(id);
    }
}
/*
    InMemoryLanguageRepository kullanılırken yazılımş metodlar.
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

 */
