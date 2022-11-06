package kodlama.io.devs.webApi.controllers;

import kodlama.io.devs.business.abstracts.ProgramingLanguageService;
import kodlama.io.devs.business.requests.CreateProgramingLanguageRequest;
import kodlama.io.devs.business.requests.UpdateProgramingLanguageRequest;
import kodlama.io.devs.business.responses.ProgramingLanguagesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/programingLanguages")
public class ProgramingLanguagesController {
    private final ProgramingLanguageService programingLanguageService;

    @Autowired
    public ProgramingLanguagesController(ProgramingLanguageService programingLanguageService) {
        this.programingLanguageService = programingLanguageService;
    }

    @PostMapping(value = "/add")
    public ProgramingLanguagesResponse add(CreateProgramingLanguageRequest language) throws Exception {
        return programingLanguageService.add(language);
    }

    @GetMapping(value = "/getAll")
    public List<ProgramingLanguagesResponse> getAll() {
        return programingLanguageService.getAll();
    }

    @GetMapping(value = "/getById/{id}")
    public ProgramingLanguagesResponse getById(@PathVariable() int id) throws Exception {
        return programingLanguageService.getById(id);
    }

    @PatchMapping(value = "/update")
    public ProgramingLanguagesResponse update(UpdateProgramingLanguageRequest updateProgramingLanguageRequest) throws Exception {
        return programingLanguageService.update(updateProgramingLanguageRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable() int id) throws Exception {
        programingLanguageService.delete(id);
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
