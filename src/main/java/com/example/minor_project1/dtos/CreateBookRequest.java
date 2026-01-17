package com.example.minor_project1.dtos;

import com.example.minor_project1.models.Author;
import com.example.minor_project1.models.Book;
import com.example.minor_project1.models.Genre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

    @NotBlank       // name != null && name.length() > 0
 //   @NotNull        // name != null
    private String name;

    @NotNull
    private Genre genre;

    private Boolean isAvailable;

    @NotBlank(message = "author name is empty or blank")
    private String authorName;

    @NotBlank
    @Email
    private String authorEmail;

    private String authorCountry;

    public Book convertToBook() {           // a method to convert DTO data into an Entity object

        return Book.builder()               // create entity object
                .name(this.name)
                .genre(this.genre)
                .isAvailable(this.isAvailable == null ? true: this.isAvailable)
                .issueCount(0L)
                .author(
                        Author.builder()
                                .name(this.authorName)
                                .email(this.authorEmail)
                                .country(this.authorCountry)
                                .build()
                )
                .build();
    }
}
