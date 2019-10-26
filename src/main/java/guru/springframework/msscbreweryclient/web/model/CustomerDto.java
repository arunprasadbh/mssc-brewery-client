package guru.springframework.msscbreweryclient.web.model;

/*
 * Created by arunabhamidipati on 24/10/2019
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private UUID id;
    private String name;

}
