package dematic.demo.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "book")
@XmlRootElement(name = "book")
public class Book extends AbstractBook {

}
