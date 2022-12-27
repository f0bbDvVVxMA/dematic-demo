package dematic.demo.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "antiqueBook")
@XmlRootElement(name = "antiqueBook")
public class AntiqueBook extends AbstractBook {
    @Max(value = 1900, message = "Release year must be less than or equal to 1900")
    @NotNull(message = "Release year is required")
    private int releaseYear;

    public AntiqueBook(AbstractBook book) {
        super(book);
    }

    public AntiqueBook() {
        super();
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
