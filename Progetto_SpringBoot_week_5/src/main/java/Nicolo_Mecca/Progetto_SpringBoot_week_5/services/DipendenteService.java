package Nicolo_Mecca.Progetto_SpringBoot_week_5.services;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Dipendente;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.excepetions.BadRequestException;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads.NewDipendenteDTO;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories.DipendenteRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Dipendente con id " + id + " non trovato"));
    }

    public Dipendente save(NewDipendenteDTO body) {
        // Verifico che l'email non sia già in uso
        dipendenteRepository.findByEmail(body.email()).ifPresent(
                dipendente -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );
        Dipendente newDipendente = new Dipendente();
        newDipendente.setUsername(body.username());
        newDipendente.setNome(body.nome());
        newDipendente.setCognome(body.cognome());
        newDipendente.setEmail(body.email());
        newDipendente.setImgProfilo("https://ui-avatars.com/api/?name=" +
                body.nome() + "+" + body.cognome());
        return dipendenteRepository.save(newDipendente);
    }

    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente findByIdAndUpdate(Long dipendenteId, NewDipendenteDTO body) {
        Dipendente found = this.findById(dipendenteId);

        // Verifico che la nuova email non sia già in uso
        if (!found.getEmail().equals(body.email())) {
            dipendenteRepository.findByEmail(body.email()).ifPresent(
                    dipendente -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso!");
                    }
            );
        }

        found.setUsername(body.username());
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());

        return dipendenteRepository.save(found);
    }

    public void findByIdAndDelete(Long dipendenteId) {
        Dipendente found = this.findById(dipendenteId);
        dipendenteRepository.delete(found);
    }

    public String uploadFotoProfilo(MultipartFile file, Long dipendenteId) {
        try {
            String url = (String) cloudinaryUploader.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap())
                    .get("url");

            Dipendente found = this.findById(dipendenteId);
            found.setImgProfilo(url);
            dipendenteRepository.save(found);

            return url;
        } catch (IOException e) {
            throw new BadRequestException("Errore durante l'upload dell'immagine!");
        }
    }
}