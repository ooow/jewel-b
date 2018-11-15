package jewel.api.controller;

import jewel.api.converter.AdvertConverter;
import jewel.api.model.AdvertModel;
import jewel.repository.AdvertRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController("ads")
public class AdvertController {
    private final AdvertRepository advertRepository;

    public AdvertController(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @GetMapping
    List<AdvertModel> getAdverts(@RequestParam(name = "size", required = false) Integer size) {
        if (nonNull(size)) {
            return AdvertConverter.toModel(advertRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0, size)).getContent());
        }
        return AdvertConverter.toModel(advertRepository.findAllByOrderByCreatedAtDesc());
    }
}
