package kodlama.io.devs.dataAccess.abstracts;

import kodlama.io.devs.entities.concretes.ProgramingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramingLanguageRepository extends JpaRepository<ProgramingLanguage, Integer> {
}
/*
! JpaRepository eklendiği için zaten bu ihtiyaçları karşılayacak metodları içince barındırıyor.
    Language add(Language language);

    List<Language> getAll();

    Language getById(int id);

    Language update(Language language);

    boolean delete(int id);*/
