package pl.pjwstk.PKP_IC_Copy.modelDTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TrainDTO {

    private long id;
    private String start;
    private String end;
    private String type;
    private String depart;
    private String arrive;
    private Double price;
}
