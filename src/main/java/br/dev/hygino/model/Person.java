package br.dev.hygino.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person implements Comparable<Person> {
    private String name;
    private String email;
    private String address;

    @Override
    public int compareTo(Person otherPerson) {
        return this.getName().compareTo(otherPerson.getName());
    }
}
