package Java.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsClassDemo {

    static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        list.add(10);
        list.add(5);
        list.add(9);
        list.add(2);
        list.add(7);
        list.add(4);
        list.add(8);
        list.add(3);

        System.out.println("Minimum element is " + Collections.min(list));
        System.out.println("Maximum element is " + Collections.max(list));

        Collections.sort(list);
        System.out.println("Sorted list is " + list);

        System.out.println("Frequency of element 7 is " + Collections.frequency(list, 7));
        list.add(7);
        System.out.println(list);
        System.out.println("Frequency of element 7 is " + Collections.frequency(list, 7));

        Collections.shuffle(list);
        System.out.println(list);

        list.remove(Integer.valueOf(7));
        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);


        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(3, "Alice", 40, "1234567890"));
        studentList.add(new Student(2, "Bob", 20, "0987654321"));
        studentList.add(new Student(1, "Charlie", 30, "5555555555"));
        System.out.println(studentList);

        Student student1 = new Student(1, "Alice", 10, "1234567890");
        Student student2 = new Student(2, "Bob", 20, "0987654321");
        System.out.println(student1.compareTo(student2));

        Collections.sort(studentList);
        System.out.println(studentList);

        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        System.out.println(studentList);

        Collections.sort(studentList, (s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println(studentList);

        studentList.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println(studentList);

        studentList.sort((s1, s2) -> s1.getRollNo().compareTo(s2.getRollNo()));
        System.out.println(studentList);
    }

}
