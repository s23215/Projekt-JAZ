package pl.pjwstk.PKP_IC_Copy.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjwstk.PKP_IC_Copy.model.Train;

@Repository
public interface TrainRepository extends JpaRepository <Train, Long> {
    public Train findById(long id);
}
