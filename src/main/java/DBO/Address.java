package DBO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private int house;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "people_address", joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "people_id"))
    private Set<People> peoples = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
//    private Set<People> peoples = new HashSet<>();


    public Set<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(Set<People> peoples) {
        this.peoples = peoples;
    }

    public void addPeoples(People people) {
        this.peoples.add(people);
    }

    public static List<Address> createAddress() {
        List<Address> list = new ArrayList<>();

        Address address1 = Address.builder().street("Независимости").house(22).build();
        Address address2 = Address.builder().street("Независимости").house(23).build();
        Address address3 = Address.builder().street("Независимости").house(24).build();
        Address address4 = Address.builder().street("Независимости").house(25).build();
        Address address5 = Address.builder().street("Независимости").house(26).build();

        list.add(address1);
        list.add(address2);
        list.add(address3);
        list.add(address4);
        list.add(address5);
        return list;
    }

}
