package Nicolo_Mecca.Progetto_SpringBoot_week_5.services;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Dipendente;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Prenotazione;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Viaggio;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.excepetions.BadRequestException;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.excepetions.NotFoundException;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads.NewPrenotazioneDTO;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories.DipendenteRepository;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories.PrenotazioneRepository;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private ViaggioRepository viaggioRepository;

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione con id " + id + " non trovata"));
    }

    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione save(NewPrenotazioneDTO body) {
        // Verifico esistenza del viaggio
        Viaggio viaggio = viaggioRepository.findById(body.viaggioId())
                .orElseThrow(() -> new BadRequestException("Viaggio con id " + body.viaggioId() + " non trovato"));

        // Verifico esistenza del dipendente
        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new BadRequestException("Dipendente con id " + body.dipendenteId() + " non trovato"));

        // Controllo prenotazioni duplicate
        boolean prenotazioneEsistente = prenotazioneRepository
                .existsByDipendenteIdAndData(dipendente.getId(), body.data());

        if (prenotazioneEsistente) {
            throw new BadRequestException("Il dipendente ha già una prenotazione per questa data");
        }

        // Controllo date valide
        if (body.data().isBefore(viaggio.getDataInizio()) || body.data().isAfter(viaggio.getDataFine())) {
            throw new BadRequestException(
                    "La data della prenotazione deve essere compresa tra " +
                            viaggio.getDataInizio() + " e " + viaggio.getDataFine()
            );
        }

        // Controllo stato viaggio
        if ("completato".equals(viaggio.getStato())) {
            throw new BadRequestException("Non è possibile prenotare un viaggio già completato");
        }

        Prenotazione prenotazione = new Prenotazione(body.data(), body.note(), viaggio);
        prenotazione = prenotazioneRepository.save(prenotazione);

        dipendenteRepository.save(dipendente);

        return prenotazione;
    }

    public void findByIdAndDelete(Long id) {
        Prenotazione prenotazione = this.findById(id);
        prenotazioneRepository.delete(prenotazione);
    }

    public Page<Prenotazione> getAllPrenotazioni(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable);
    }

    public Page<Prenotazione> getPrenotazioniByDipendente(Long dipendenteId, Pageable pageable) {
        return prenotazioneRepository.findByDipendenteId(dipendenteId, pageable);
    }

    public List<Prenotazione> getPrenotazioniByData(LocalDate data) {
        return prenotazioneRepository.findByData(data);
    }


    public boolean existsPrenotazioneForViaggioAndData(Long viaggioId, LocalDate data) {
        return prenotazioneRepository.existsByViaggioIdAndData(viaggioId, data);
    }
}



