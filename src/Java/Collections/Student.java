package Java.Collections;

import java.util.Objects;

public class Student implements Comparable<Student> {

    private int rollNo;
    private String name;
    private int age;
    private String phone;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        return rollNo == student.rollNo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rollNo);
    }

    @Override
    public int compareTo(Student that) {
        return Integer.compare(this.rollNo, that.rollNo);
    }


    public Student() {}

    public Student(Integer rollNo, String name, int age, String phone) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }

}
