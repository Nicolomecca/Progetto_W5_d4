package Nicolo_Mecca.Progetto_SpringBoot_week_5.controllers;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Viaggio;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.excepetions.BadRequestException;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads.NewViaggioDTO;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    // 1. GET http://localhost:3001/viaggi
    @GetMapping
    public Page<Viaggio> getViaggi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.viaggioService.findAll(page, size, sortBy);
    }

    // 2. POST http://localhost:3001/viaggi
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated NewViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.viaggioService.save(body);
    }

    // 3. GET http://localhost:3001/viaggi/{viaggioId}
    @GetMapping("/{viaggioId}")
    public Viaggio findById(@PathVariable Long viaggioId) {
        return this.viaggioService.findById(viaggioId);
    }

    // 4. PUT http://localhost:3001/viaggi/{viaggioId}/stato
    @PutMapping("/{viaggioId}/stato")
    public Viaggio updateStato(
            @PathVariable Long viaggioId,
            @RequestBody String nuovoStato) {
        return this.viaggioService.updateStato(viaggioId, nuovoStato);
    }

    // 5. DELETE http://localhost:3001/viaggi/{viaggioId}
    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long viaggioId) {
        this.viaggioService.findByIdAndDelete(viaggioId);
    }

    // 6. PUT http://localhost:3001/viaggi/{viaggioId}
    @PutMapping("/{viaggioId}")
    public Viaggio findByIdAndUpdate(
            @PathVariable Long viaggioId,
            @RequestBody @Validated NewViaggioDTO body,
            BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.viaggioService.findByIdAndUpdate(viaggioId, body);
    }
}