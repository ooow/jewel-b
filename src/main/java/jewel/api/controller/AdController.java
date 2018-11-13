package jewel.api.controller;

import jewel.api.model.AdModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController("/api/ad")
public class AdController {

    @GetMapping
    List<AdModel> get() {
        return Collections.singletonList(AdModel.builder().title("Title1").build());
    }

    @GetMapping("/delete")
    List<AdModel> delete() {
        return Collections.singletonList(AdModel.builder().title("Title2").build());
    }

}
