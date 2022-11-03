package kodlama.io.devs.webApi.controllers;

import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.TechnologiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/technologies")
public class TechnologiesController {
    private TechnologyService service;

    @Autowired
    public TechnologiesController(TechnologyService service) {
        this.service = service;
    }

    @PostMapping(value = "/add")
    public TechnologiesResponse add(CreateTechnologyRequest createTechnologyRequest) throws Exception {
        return service.add(createTechnologyRequest);
    }

    @GetMapping(value = "/getAll")
    public List<TechnologiesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/getById/{id}")
    public TechnologiesResponse getById(@PathVariable int id) throws Exception {
        return service.getById(id);
    }

    @PatchMapping(value = "/update")
    public TechnologiesResponse update(UpdateTechnologyRequest updateTechnologyRequest) throws Exception {
        return service.update(updateTechnologyRequest);
    }

    @DeleteMapping(value = "/delete")
    public void delete(int id) throws Exception {
        service.delete(id);
    }
}
