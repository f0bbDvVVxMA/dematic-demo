package dematic.demo.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "scienceJournal")
@XmlRootElement(name = "scienceJournal")
public class ScienceJournal extends AbstractBook {
    @NotNull(message = "Science index is required")
    @Min(value = 1, message = "Science index must be greater than or equal to 1")
    @Max(value = 10, message = "Science index must be less than or equal to 10")
    private int scienceIndex;

    public int getScienceIndex() {
        return scienceIndex;
    }

    public void setScienceIndex(int scienceIndex) {
        this.scienceIndex = scienceIndex;
    }
}