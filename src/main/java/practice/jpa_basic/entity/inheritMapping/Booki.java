package practice.jpa_basic.entity.inheritMapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@DiscriminatorValue(value = "Book")
public class Booki extends Itemi{
    private String author;
}
