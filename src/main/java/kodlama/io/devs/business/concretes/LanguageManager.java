package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.LanguageService;
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

    boolean isUniqueName(Language language) {
        return languageRepository.getAll()
                .stream()
                .noneMatch(inDatabaseLanguage -> inDatabaseLanguage.getName().equalsIgnoreCase(language.getName()));
    }

    boolean isUniqueId(Language language) {
        return languageRepository.getAll()
                .stream()
                .noneMatch(inDatabaseLanguage -> inDatabaseLanguage.getId() == language.getId());
    }
}
