package codesquad.airbnb.wish;

import codesquad.airbnb.wish.dto.WishAddRequest;
import codesquad.airbnb.wish.dto.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishes")
public class WishController {

    private final WishService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WishResponse addWish(@RequestBody WishAddRequest request) {
        return service.addWish(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeWish(@PathVariable("id") long id) {
        service.removeWish(id);
    }
}
