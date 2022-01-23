package pl.pjwstk.PKP_IC_Copy.reposiory;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjwstk.PKP_IC_Copy.model.Train;
import pl.pjwstk.PKP_IC_Copy.modelDTO.TrainDTO;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainService {
    private TrainRepository trainRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<Train> displayTrains(){
        return new ArrayList<>(trainRepository.findAll());
    }

    private Train mapToTrain(TrainDTO trainDTO){
        return Train.builder()
                .price(trainDTO.getPrice())
                .type(trainDTO.getType())
                .depart(LocalTime.parse(trainDTO.getDepart()))
                .arrive(LocalTime.parse(trainDTO.getArrive()))
                .build();
    }

    public void addTrainToRepo(TrainDTO trainDTO) {
        trainRepository.save(mapToTrain(trainDTO));
    }
    public void deleteTrainFromRepo(long id){
        trainRepository.delete(trainRepository.findById(id));
    }

}
