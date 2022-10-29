package kodlama.io.devs.dataAccess.abstracts;

import kodlama.io.devs.entities.concretes.Language;

import java.util.List;

public interface LanguageRepository {
    Language add(Language language);

    List<Language> getAll();

    Language getById(int id);

    Language update(Language language);

    boolean delete(int id);
}
