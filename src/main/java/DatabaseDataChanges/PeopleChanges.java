package DatabaseDataChanges;

import DBO.Address;
import DBO.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleChanges {
    public static List<People> changesAge(List<People> peopleList) {
        People people1 = peopleList.get(peopleList.size() - 1);
        People people2 = peopleList.get(peopleList.size() - 2);
        people1.setAge(people1.getAge() + 2);
        people2.setAge(people2.getAge() + 2);
        List<People> list = new ArrayList<>();
        list.add(people1);
        list.add(people2);
        return list;
    }
}
