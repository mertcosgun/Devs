package kodlama.io.devs.dataAccess.concretes;

import kodlama.io.devs.dataAccess.abstracts.LanguageRepository;
import kodlama.io.devs.entities.concretes.Language;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Artık bu sınıfı kullanamayacağız bu sınıfın implemente ettiği interface JpaRepository
 * üzerinden extends edildiği için bu sınıf geçersiz kaldı
 */
public class InMemoryLanguageRepository {

    List<Language> languages;

    public InMemoryLanguageRepository() {
        languages = new ArrayList<>();
        languages.add(new Language(1, "Assembly"));
        languages.add(new Language(2, "C#"));
        languages.add(new Language(3, "Java"));
        languages.add(new Language(4, "Python"));
        languages.add(new Language(5, "Dart"));
    }

//    @Override
    public Language add(Language language) {
        languages.add(language);
        return language;
    }

//    @Override
    public List<Language> getAll() {
        return languages;
    }

//    @Override
    public Language getById(int id) {
        return languages.stream().filter(language -> language.getId() == id).findFirst().orElse(null);
    }

//    @Override
    public Language update(Language language) {
        Language lang = getById(language.getId());
        lang.setName(language.getName());
        return lang;
    }

//    @Override
    public boolean delete(int id) {
       return languages.removeIf(language -> language.getId() == id);
    }

}
