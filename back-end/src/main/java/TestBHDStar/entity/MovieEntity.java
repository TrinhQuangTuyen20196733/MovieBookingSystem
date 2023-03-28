package TestBHDStar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Indexed
@Table(name = "movie")
public class MovieEntity extends BaseEntity {
 @FullTextField(analyzer = "standard")
 @Column(name = "title",unique = true)
 private String title;
 @Column(name = "thumbnail")
 private String thumbnail;
 @Column(name = "alt")
 private String alt;

 @Column(name = "trailer")
 private String trailer;

 @Column(name="short_description")
 private String shortDescription;
 @Column(name = "classify")
 private String classify;
 @Column(name = "director")
 private String director;
 @Column(name = "actor")
 private String actor;
 @Column(name = "type")
 private String type;
 @Temporal(TemporalType.DATE)
 @DateTimeFormat(pattern = "dd/MM/yyyy")
 @JsonFormat(pattern = "dd/MM/yyyy")
 @Column(name="start_date")
 private Date startDate;
 @Column(name="length")
 private int length;
 @Column(name = "language")
 private String language;
 @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
 private List<SessionEntity> sessionList;

 public MovieEntity(String title, String thumbnail, String alt) {
  this.title = title;
  this.thumbnail = thumbnail;
  this.alt = alt;
 }

 @Override
 public String toString() {
  return "Movie{" +
          "title='" + title + '\'' +
          ", thumbnail='" + thumbnail+ '\'' +
          ", trailer='" + trailer + '\'' +
          ", classify='" + classify + '\'' +
          ", director='" + director + '\'' +
          ", actor='" + actor + '\'' +
          ", type='" + type + '\'' +
          ", startDate=" + startDate +
          ", length=" + length +
          ", language='" + language + '\'' +
          ", alt='" + alt + '\'' +
          '}';
 }
}
