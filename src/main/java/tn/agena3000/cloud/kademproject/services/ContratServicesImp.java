package tn.agena3000.cloud.kademproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tn.agena3000.cloud.kademproject.Contrat;
import tn.agena3000.cloud.kademproject.Etudiant;
import tn.agena3000.cloud.kademproject.repositories.ContratRepository;
import tn.agena3000.cloud.kademproject.repositories.EtudiantRepository;

import java.util.List;

@Service
public class ContratServicesImp implements ContratServices{

    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;


    @Override
    public void ajouterContrat(Contrat e) {
        contratRepository.save(e);

    }

    @Override
    public void updateContrat(Contrat e) {
        contratRepository.save(e);

    }

    @Override
    public List<Contrat> getAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat getByID(Integer id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteContrat(Integer id) {
        contratRepository.deleteById(id);

    }

    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String nom, String prenomE) {
            Contrat contrat = contratRepository.findById(ce.getIdContrat()).orElse(null);
            Etudiant etudiant= etudiantRepository.findByNomEAndPrenomE(nom,prenomE);
            Assert.notNull(contrat, "universite must not be null.") ;
            Assert.notNull(etudiant, "universite must not be null.") ;
            if (etudiant.getContrat().size()>=5){
                return null;
            }
            etudiant.getContrat().add(contrat);
            etudiantRepository.save(etudiant);
            return contrat;

    }
}
