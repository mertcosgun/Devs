package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.ProgramingLanguagesResponse;
import kodlama.io.devs.business.responses.TechnologiesResponse;
import kodlama.io.devs.dataAccess.abstracts.ProgramingLanguageRepository;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.ProgramingLanguage;
import kodlama.io.devs.entities.concretes.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyRepository repository;
    private ProgramingLanguageRepository programingLanguageRepository;

    @Autowired
    public TechnologyManager(TechnologyRepository repository, ProgramingLanguageRepository programingLanguageRepository) {
        this.repository = repository;
        this.programingLanguageRepository = programingLanguageRepository;
    }

    @Override
    public TechnologiesResponse add(CreateTechnologyRequest createTechnologyRequest) throws Exception {
        ProgramingLanguage programingLanguage = programingLanguageRepository.findById(createTechnologyRequest.getLanguageId()).orElse(null);
        if (programingLanguage == null) {
            throw new Exception("Language id does not exist. id: " + createTechnologyRequest.getLanguageId());
        }
        Technology technology = new Technology();
        technology.setName(createTechnologyRequest.getName());
        technology.setProgramingLanguage(programingLanguage);
        repository.save(technology);

        return new TechnologiesResponse(technology.getId(), technology.getName(), new ProgramingLanguagesResponse(programingLanguage.getId(), programingLanguage.getName()));
    }

    @Override
    public List<TechnologiesResponse> getAll() {
        List<Technology> technologies = repository.findAll();

        return technologies.stream().map(technology -> {
            ProgramingLanguage programingLanguage = programingLanguageRepository.findById(technology.getProgramingLanguage().getId()).get();
            ProgramingLanguagesResponse programingLanguagesResponse = new ProgramingLanguagesResponse(programingLanguage.getId(), programingLanguage.getName());
            return new TechnologiesResponse(technology.getId(), technology.getName(), programingLanguagesResponse);
        }).toList();
    }

    @Override
    public TechnologiesResponse getById(int id) throws Exception {
        Technology technology = repository.findById(id).orElse(null);
        if (technology == null) {
            throw new Exception("Technology id does not exist. id:" + id);
        }
        ProgramingLanguagesResponse programingLanguagesResponse = new ProgramingLanguagesResponse(technology.getProgramingLanguage().getId(),
                technology.getProgramingLanguage().getName());
        return new TechnologiesResponse(technology.getId(), technology.getName(), programingLanguagesResponse);
    }

    @Override
    public TechnologiesResponse update(UpdateTechnologyRequest updateTechnologyRequest) throws Exception {
        Technology technology = repository.findById(updateTechnologyRequest.getId()).orElse(null);
        if (technology == null) {
            throw new Exception("Technology id does not exist. id:" + updateTechnologyRequest.getId());
        }
        ProgramingLanguage programingLanguage = programingLanguageRepository.findById(updateTechnologyRequest.getLanguageId()).orElse(null);
        if (programingLanguage == null) {
            throw new Exception("Language id does not exist. id: " + updateTechnologyRequest.getLanguageId());
        }
        technology.setName(updateTechnologyRequest.getName());
        technology.setProgramingLanguage(programingLanguage);
        repository.save(technology);
        ProgramingLanguagesResponse programingLanguagesResponse = new ProgramingLanguagesResponse(programingLanguage.getId(), programingLanguage.getName());
        return new TechnologiesResponse(technology.getId(), technology.getName(), programingLanguagesResponse);
    }

    @Override
    public void delete(int id) throws Exception {
        Technology technology = repository.findById(id).orElse(null);
        if (technology == null) {
            throw new Exception("Technology id does not exist. id:" + id);
        }
        repository.deleteById(id);
    }
}
