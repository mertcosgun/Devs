package kodlama.io.devs.business.concretes;

import kodlama.io.devs.business.abstracts.ProgramingLanguageService;
import kodlama.io.devs.business.requests.CreateProgramingLanguageRequest;
import kodlama.io.devs.business.requests.UpdateProgramingLanguageRequest;
import kodlama.io.devs.business.responses.ProgramingLanguagesResponse;
import kodlama.io.devs.dataAccess.abstracts.ProgramingLanguageRepository;
import kodlama.io.devs.entities.concretes.ProgramingLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramingLanguageManager implements ProgramingLanguageService {
    final ProgramingLanguageRepository programingLanguageRepository;

    @Autowired
    public ProgramingLanguageManager(ProgramingLanguageRepository programingLanguageRepository) {
        this.programingLanguageRepository = programingLanguageRepository;
    }

    boolean isUniqueName(String name) {
        return programingLanguageRepository.findAll()
                .stream()
                .noneMatch(inDatabaseLanguage -> inDatabaseLanguage.getName().equalsIgnoreCase(name));
    }

    boolean isUniqueId(ProgramingLanguage programingLanguage) {
        return programingLanguageRepository.findAll()
                .stream()
                .noneMatch(inDatabaseLanguage -> inDatabaseLanguage.getId() == programingLanguage.getId());
    }

    @Override
    public ProgramingLanguagesResponse add(CreateProgramingLanguageRequest createProgramingLanguageRequest) throws Exception {
        if (!isUniqueName(createProgramingLanguageRequest.getName())) {
            throw new Exception("This language already exist in database");
        }
        ProgramingLanguage programingLanguage = new ProgramingLanguage();
        programingLanguage.setName(createProgramingLanguageRequest.getName());
        programingLanguageRepository.save(programingLanguage);
        return new ProgramingLanguagesResponse(programingLanguage.getId(), programingLanguage.getName());
    }

    @Override
    public List<ProgramingLanguagesResponse> getAll() {
        List<ProgramingLanguage> programingLanguages = programingLanguageRepository.findAll();
        return programingLanguages.stream().map(language -> new ProgramingLanguagesResponse(language.getId(), language.getName())).toList();
    }

    @Override
    public ProgramingLanguagesResponse getById(int id) throws Exception {
        ProgramingLanguage programingLanguage = programingLanguageRepository.findById(id).orElse(null);
        if (programingLanguage == null) {
            throw new Exception("Language id does not exist. id: " + id);
        }
        return new ProgramingLanguagesResponse(programingLanguage.getId(), programingLanguage.getName());
    }

    @Override
    public ProgramingLanguagesResponse update(UpdateProgramingLanguageRequest updateProgramingLanguageRequest) throws Exception {
        ProgramingLanguage programingLanguage = programingLanguageRepository.findById(updateProgramingLanguageRequest.getId()).orElse(null);
        if (programingLanguage == null) {
            throw new Exception("Language id does not exist. id: " + updateProgramingLanguageRequest.getId());
        }
        programingLanguage.setName(updateProgramingLanguageRequest.getName());
        programingLanguageRepository.save(programingLanguage);
        return new ProgramingLanguagesResponse(programingLanguage.getId(), programingLanguage.getName());
    }

    @Override
    public void delete(int id) throws Exception {
        ProgramingLanguage programingLanguage = programingLanguageRepository.findById(id).orElse(null);
        if (programingLanguage == null) {
            throw new Exception("Language id does not exist. id: " + id);
        }
        programingLanguageRepository.delete(programingLanguage);
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