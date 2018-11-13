package jewel.api.controller;

import jewel.api.model.AdModel;
import jewel.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static jewel.api.converter.AdConverter.toModel;

@RestController("ads")
public class AdController {
    private final AdRepository adRepository;

    public AdController(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @GetMapping
    List<AdModel> loadAds(@RequestParam(name = "load", required = false) Integer load) {
        if (Objects.nonNull(load)) {
            Pageable pageable = PageRequest.of(0, load, Sort.Direction.DESC, "createdAt");
            return toModel(adRepository.findAll(pageable).getContent());
        }
        return toModel(adRepository.findAll());
    }
}
