package kodlama.io.devs.business.abstracts;

import kodlama.io.devs.entities.concretes.Language;

import java.util.List;

public interface LanguageService {
    Language add(Language language) throws Exception;
    List<Language> getAll();
    Language getById(int id) throws Exception;
    Language update(Language language);
    boolean delete(int id);
}
