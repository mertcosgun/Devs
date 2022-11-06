package kodlama.io.devs.dataAccess.concretes;

import kodlama.io.devs.entities.concretes.ProgramingLanguage;

import java.util.ArrayList;
import java.util.List;

/**
 * Artık bu sınıfı kullanamayacağız bu sınıfın implemente ettiği interface JpaRepository
 * üzerinden extends edildiği için bu sınıf geçersiz kaldı
 */
public class InMemoryLanguageRepository {

    List<ProgramingLanguage> programingLanguages;

    public InMemoryLanguageRepository() {
        programingLanguages = new ArrayList<>();
        /*languages.add(new Language(1, "Assembly"));
        languages.add(new Language(2, "C#"));
        languages.add(new Language(3, "Java"));
        languages.add(new Language(4, "Python"));
        languages.add(new Language(5, "Dart"));*/
    }

//    @Override
    public ProgramingLanguage add(ProgramingLanguage programingLanguage) {
        programingLanguages.add(programingLanguage);
        return programingLanguage;
    }

//    @Override
    public List<ProgramingLanguage> getAll() {
        return programingLanguages;
    }

//    @Override
    public ProgramingLanguage getById(int id) {
        return programingLanguages.stream().filter(language -> language.getId() == id).findFirst().orElse(null);
    }

//    @Override
    public ProgramingLanguage update(ProgramingLanguage programingLanguage) {
        ProgramingLanguage lang = getById(programingLanguage.getId());
        lang.setName(programingLanguage.getName());
        return lang;
    }

//    @Override
    public boolean delete(int id) {
       return programingLanguages.removeIf(language -> language.getId() == id);
    }

}
