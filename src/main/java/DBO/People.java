package DBO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "people")
public class People implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private int age;

    @ManyToOne (fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public static List<People> createPeoples() {
        List<People> list = new ArrayList<>();

        People people1 = People.builder().name("Илья1").surname("Кечко").age(22).build();
        People people2 = People.builder().name("Илья2").surname("Кечко").age(23).build();
        People people3 = People.builder().name("Илья3").surname("Кечко").age(24).build();
        People people4 = People.builder().name("Илья4").surname("Кечко").age(25).build();
        People people5 = People.builder().name("Илья5").surname("Кечко").age(26).build();

        list.add(people1);
        list.add(people2);
        list.add(people3);
        list.add(people4);
        list.add(people5);
        return list;
    }
}
