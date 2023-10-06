package com.prueba.moviecrud.director;

// public record DirectorDTO(Long id, String firstName, String lastName) {
//     public DirectorDTO{
//         if(firstName.isBlank()) {
//             throw new IllegalArgumentException("First name cannot be empty.");
//         } 
//         if(lastName.isBlank()) {
//             throw new IllegalArgumentException("Last name cannot be empty.");
//         }
//     }
// }

public class DirectorDTO{

    private String id;
    private String firstName;
    private String lastName;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
