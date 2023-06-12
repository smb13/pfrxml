package ru.mshamanin.pfrxml.web.pack;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mshamanin.pfrxml.model.Pack;
import ru.mshamanin.pfrxml.View;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;
import java.util.List;


@ApiIgnore
@RestController
@RequestMapping(value = "/profile/packs", produces = MediaType.APPLICATION_JSON_VALUE)
public class PackUIController extends AbstractPackController {


    @Override
    @GetMapping
    public List<Pack> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Pack get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Validated(View.Web.class) Pack pack) {
        if (pack.isNew()) {
            super.create(pack);
        } else {
            super.update(pack, pack.getId());
        }
    }

    @Override
    @GetMapping("/filter")
    public List<Pack> getBetween(
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalDate endDate) {
        return super.getBetween(startDate, endDate);
    }
}

