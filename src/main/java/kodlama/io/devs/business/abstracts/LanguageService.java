package kodlama.io.devs.business.abstracts;

import kodlama.io.devs.business.requests.CreateLanguageRequest;
import kodlama.io.devs.business.requests.UpdateLanguageRequest;
import kodlama.io.devs.business.responses.LanguagesResponse;

import java.util.List;

public interface LanguageService {
    LanguagesResponse add(CreateLanguageRequest createLanguageRequest) throws Exception;

    List<LanguagesResponse> getAll();

    LanguagesResponse getById(int id) throws Exception;

    LanguagesResponse update(UpdateLanguageRequest updateLanguageRequest) throws Exception;

    void delete(int id) throws Exception;
}
