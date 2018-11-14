package jewel.api.controller;

import jewel.api.model.AdvertModel;
import jewel.repository.AdvertRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static jewel.api.converter.AdvertConverter.toModel;

@RestController("ads")
public class AdvertController {
    private final AdvertRepository advertRepository;

    public AdvertController(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @GetMapping
    List<AdvertModel> getAdverts(@RequestParam(name = "size", required = false) Integer size) {
        if (Objects.nonNull(size)) {
            Pageable pageable = PageRequest.of(0, size, Sort.Direction.DESC, "createdAt");
            return toModel(advertRepository.findAll(pageable).getContent());
        }
        return toModel(advertRepository.findAll());
    }
}
