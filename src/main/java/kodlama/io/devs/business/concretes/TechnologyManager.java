package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.TechnologyService;
import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.LanguagesResponse;
import kodlama.io.devs.business.responses.TechnologiesResponse;
import kodlama.io.devs.dataAccess.abstracts.LanguageRepository;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.Language;
import kodlama.io.devs.entities.concretes.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyRepository repository;
    private LanguageRepository languageRepository;

    @Autowired
    public TechnologyManager(TechnologyRepository repository, LanguageRepository languageRepository) {
        this.repository = repository;
        this.languageRepository = languageRepository;
    }

    @Override
    public TechnologiesResponse add(CreateTechnologyRequest createTechnologyRequest) throws Exception {
        Language language = languageRepository.findById(createTechnologyRequest.getLanguageId()).orElse(null);
        if (language == null) {
            throw new Exception("Language id does not exist. id: " + createTechnologyRequest.getLanguageId());
        }
        Technology technology = new Technology();
        technology.setName(createTechnologyRequest.getName());
        technology.setLanguage(language);
        repository.save(technology);

        return new TechnologiesResponse(technology.getId(), technology.getName(), new LanguagesResponse(language.getId(), language.getName()));
    }

    @Override
    public List<TechnologiesResponse> getAll() {
        List<Technology> technologies = repository.findAll();

        return technologies.stream().map(technology -> {
            Language language = languageRepository.findById(technology.getLanguage().getId()).get();
            LanguagesResponse languagesResponse = new LanguagesResponse(language.getId(), language.getName());
            return new TechnologiesResponse(technology.getId(), technology.getName(), languagesResponse);
        }).toList();
    }

    @Override
    public TechnologiesResponse getById(int id) throws Exception {
        Technology technology = repository.findById(id).orElse(null);
        if (technology == null) {
            throw new Exception("Technology id does not exist. id:" + id);
        }
        LanguagesResponse languagesResponse = new LanguagesResponse(technology.getLanguage().getId(),
                technology.getLanguage().getName());
        return new TechnologiesResponse(technology.getId(), technology.getName(), languagesResponse);
    }

    @Override
    public TechnologiesResponse update(UpdateTechnologyRequest updateTechnologyRequest) throws Exception {
        Technology technology = repository.findById(updateTechnologyRequest.getId()).orElse(null);
        if (technology == null) {
            throw new Exception("Technology id does not exist. id:" + updateTechnologyRequest.getId());
        }
        Language language = languageRepository.findById(updateTechnologyRequest.getLanguageId()).orElse(null);
        if (language == null) {
            throw new Exception("Language id does not exist. id: " + updateTechnologyRequest.getLanguageId());
        }
        technology.setName(updateTechnologyRequest.getName());
        technology.setLanguage(language);
        repository.save(technology);
        LanguagesResponse languagesResponse = new LanguagesResponse(language.getId(), language.getName());
        return new TechnologiesResponse(technology.getId(), technology.getName(), languagesResponse);
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
