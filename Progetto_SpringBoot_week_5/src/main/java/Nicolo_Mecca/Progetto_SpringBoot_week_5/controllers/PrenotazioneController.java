package Nicolo_Mecca.Progetto_SpringBoot_week_5.controllers;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Prenotazione;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads.NewPrenotazioneDTO;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads.PrenotazioneResponseDTO;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    // 1. GET http://localhost:3001/prenotazioni
    @GetMapping
    public Page<PrenotazioneResponseDTO> getAllPrenotazioni(Pageable pageable) {
        Page<Prenotazione> prenotazioni = prenotazioneService.getAllPrenotazioni(pageable);
        return prenotazioni.map(PrenotazioneResponseDTO::new);
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

    @GetMapping("/dipendente/{dipendenteId}")
    public Page<PrenotazioneResponseDTO> getPrenotazioniByDipendente(
            @PathVariable Long dipendenteId,
            Pageable pageable) {
        Page<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniByDipendente(dipendenteId, pageable);
        return prenotazioni.map(PrenotazioneResponseDTO::new);
    }

    @GetMapping("/data/{data}")
    public List<PrenotazioneResponseDTO> getPrenotazioniByData(@PathVariable LocalDate data) {
        List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniByData(data);
        return prenotazioni.stream().map(PrenotazioneResponseDTO::new).collect(Collectors.toList());
    }
    

    @GetMapping("/exists")
    public boolean existsPrenotazioneForViaggioAndData(
            @RequestParam Long viaggioId,
            @RequestParam LocalDate data) {
        return prenotazioneService.existsPrenotazioneForViaggioAndData(viaggioId, data);
    }

}
