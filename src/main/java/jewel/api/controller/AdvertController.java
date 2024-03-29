package jewel.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jewel.api.converter.AdvertConverter;
import jewel.api.model.AdvertModel;
import jewel.exception.NotFoundException;
import jewel.repository.AdvertRepository;
import jewel.repository.ArchivedAdvertRepository;
import jewel.repository.domain.Advert;
import jewel.repository.domain.ArchivedAdvert;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.nonNull;

@CrossOrigin
@RestController
@RequestMapping("ads")
public class AdvertController {
    private final AdvertRepository advertRepository;
    private final ArchivedAdvertRepository archivedAdvertRepository;

    public AdvertController(AdvertRepository advertRepository,
                            ArchivedAdvertRepository archivedAdvertRepository) {
        this.advertRepository = advertRepository;
        this.archivedAdvertRepository = archivedAdvertRepository;
    }

    @GetMapping
    @ApiOperation("Get all adverts sorted by time of creation.")
    List<AdvertModel> getAdverts(@ApiParam("Optional size of result list.") @RequestParam(name = "size", required = false) Integer size) {
        if (nonNull(size)) {
            return AdvertConverter.toModel(advertRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0, size)).getContent());
        }
        return AdvertConverter.toModel(advertRepository.findAllByOrderByCreatedAtDesc());
    }

    @PatchMapping
    @ApiOperation("Create or update advert.")
    AdvertModel patchAdvert(@RequestBody AdvertModel advertModel) {
        if (nonNull(advertModel.getId())) {
            advertRepository.findById(advertModel.getId()).orElseThrow(NotFoundException::new);
        }
        return AdvertConverter.toModel(advertRepository.save(AdvertConverter.toDomain(advertModel)));
    }

    @DeleteMapping("/{advertId}")
    @ApiOperation("Delete advert by provided id.")
    void deleteAdvert(@PathVariable("advertId") String advertId) {
        Advert advert = advertRepository.findById(advertId).orElseThrow(NotFoundException::new);
        advertRepository.delete(advert);
        archivedAdvertRepository.save(ArchivedAdvert.of(advert));
    }
}
