package practice.jpa_basic.entity.inheritMapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@DiscriminatorValue(value = "Album")
public class Albumi extends Itemi {
    private String artist;
}
