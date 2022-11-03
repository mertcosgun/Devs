package kodlama.io.devs.business.abstracts;

import kodlama.io.devs.business.requests.CreateTechnologyRequest;
import kodlama.io.devs.business.requests.UpdateTechnologyRequest;
import kodlama.io.devs.business.responses.TechnologiesResponse;
import kodlama.io.devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.devs.entities.concretes.Technology;

import java.util.List;

public interface TechnologyService {
    TechnologiesResponse add(CreateTechnologyRequest createTechnologyRequest) throws Exception;
    List<TechnologiesResponse> getAll();

    TechnologiesResponse getById(int id) throws Exception;

    TechnologiesResponse update(UpdateTechnologyRequest updateTechnologyRequest) throws Exception;

    void delete(int id) throws Exception;
}
