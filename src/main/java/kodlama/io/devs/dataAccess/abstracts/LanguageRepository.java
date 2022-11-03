package kodlama.io.devs.dataAccess.abstracts;

import kodlama.io.devs.entities.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
/*
! JpaRepository eklendiği için zaten bu ihtiyaçları karşılayacak metodları içince barındırıyor.
    Language add(Language language);

    List<Language> getAll();

    Language getById(int id);

    Language update(Language language);

    boolean delete(int id);*/
