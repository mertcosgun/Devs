package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.LanguageService;
import kodlama.io.devs.business.requests.CreateLanguageRequest;
import kodlama.io.devs.business.requests.UpdateLanguageRequest;
import kodlama.io.devs.business.responses.LanguagesResponse;
import kodlama.io.devs.dataAccess.abstracts.LanguageRepository;
import kodlama.io.devs.entities.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageManager implements LanguageService {
    final LanguageRepository languageRepository;

    @Autowired
    public LanguageManager(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    boolean isUniqueName(String name) {
        return languageRepository.findAll()
                .stream()
                .noneMatch(inDatabaseLanguage -> inDatabaseLanguage.getName().equalsIgnoreCase(name));
    }

    boolean isUniqueId(Language language) {
        return languageRepository.findAll()
                .stream()
                .noneMatch(inDatabaseLanguage -> inDatabaseLanguage.getId() == language.getId());
    }

    @Override
    public LanguagesResponse add(CreateLanguageRequest createLanguageRequest) throws Exception {
        if (!isUniqueName(createLanguageRequest.getName())) {
            throw new Exception("This language already exist in database");
        }
        Language language = new Language();
        language.setName(createLanguageRequest.getName());
        languageRepository.save(language);
        return new LanguagesResponse(language.getId(), language.getName());
    }

    @Override
    public List<LanguagesResponse> getAll() {
        List<Language> languages = languageRepository.findAll();
        return languages.stream().map(language -> new LanguagesResponse(language.getId(), language.getName())).toList();
    }

    @Override
    public LanguagesResponse getById(int id) throws Exception {
        Language language = languageRepository.findById(id).orElse(null);
        if (language == null) {
            throw new Exception("Language id does not exist. id: " + id);
        }
        return new LanguagesResponse(language.getId(), language.getName());
    }

    @Override
    public LanguagesResponse update(UpdateLanguageRequest updateLanguageRequest) throws Exception {
        Language language = languageRepository.findById(updateLanguageRequest.getId()).orElse(null);
        if (language == null) {
            throw new Exception("Language id does not exist. id: " + updateLanguageRequest.getId());
        }
        language.setName(updateLanguageRequest.getName());
        languageRepository.save(language);
        return new LanguagesResponse(language.getId(), language.getName());
    }

    @Override
    public void delete(int id) throws Exception {
        Language language = languageRepository.findById(id).orElse(null);
        if (language == null) {
            throw new Exception("Language id does not exist. id: " + id);
        }
        languageRepository.delete(language);
    }
}

/*

    @Override
    public Language add(Language language) throws Exception {
        if(language.getName().isEmpty()) {
            throw  new Exception("Language name is required.");
        }

        if (!isUniqueName(language) || !isUniqueId(language)) {
            throw new Exception("This language or id already exists in the database.");
        }
        return languageRepository.add(language);
    }

    @Override
    public List<Language> getAll() {
        return languageRepository.getAll();
    }

    @Override
    public Language getById(int id) throws Exception {
        Language language = languageRepository.getById(id);
        if(language == null) {
            throw new Exception("This language does not exist. id: " + id);
        }
        return language;
    }

    @Override
    public Language update(Language language) {
        System.out.println(language);
        return languageRepository.update(language);
    }

    @Override
    public boolean delete(int id) {
       return languageRepository.delete(id);
    }

*/