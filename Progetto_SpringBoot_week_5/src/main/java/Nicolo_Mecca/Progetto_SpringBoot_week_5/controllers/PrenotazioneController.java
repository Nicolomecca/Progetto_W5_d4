package Nicolo_Mecca.Progetto_SpringBoot_week_5.controllers;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Prenotazione;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads.NewPrenotazioneDTO;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    // 1. GET http://localhost:3001/prenotazioni
    @GetMapping
    public Page<Prenotazione> getPrenotazioni(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return prenotazioneService.findAll(page, size, sortBy);
    }

    // 2. POST http://localhost:3001/prenotazioni
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(@RequestBody @Validated NewPrenotazioneDTO body) {
        return prenotazioneService.save(body);
    }

    // 3. GET http://localhost:3001/prenotazioni/{prenotazioneId}
    @GetMapping("/{prenotazioneId}")
    public Prenotazione getPrenotazioneById(@PathVariable Long prenotazioneId) {
        return prenotazioneService.findById(prenotazioneId);
    }

    // 4. DELETE http://localhost:3001/prenotazioni/{prenotazioneId}
    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long prenotazioneId) {
        prenotazioneService.findByIdAndDelete(prenotazioneId);
    }

}
