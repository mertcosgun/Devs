package kodlama.io.devs.business.abstracts;

import kodlama.io.devs.business.requests.CreateProgramingLanguageRequest;
import kodlama.io.devs.business.requests.UpdateProgramingLanguageRequest;
import kodlama.io.devs.business.responses.ProgramingLanguagesResponse;

import java.util.List;

public interface ProgramingLanguageService {
    ProgramingLanguagesResponse add(CreateProgramingLanguageRequest createProgramingLanguageRequest) throws Exception;

    List<ProgramingLanguagesResponse> getAll();

    ProgramingLanguagesResponse getById(int id) throws Exception;

    ProgramingLanguagesResponse update(UpdateProgramingLanguageRequest updateProgramingLanguageRequest) throws Exception;

    void delete(int id) throws Exception;
}
