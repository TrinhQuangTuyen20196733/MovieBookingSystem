package TestBHDStar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "session",uniqueConstraints = @UniqueConstraint(columnNames={"movie_id", "room_id"}))
public class SessionEntity  extends  BaseEntity{
    @Column(name = "cost")
    private int cost;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
    })
    @JoinColumn(name="movie_id")
    private MovieEntity movie;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "start_time")
    private Date startTime;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
    })
    @JoinColumn(name = "room_id")
    private  RoomEntity room;
    @OneToMany(mappedBy = "session",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SeatOnSessionEntity> seatOnSessionEntityList;
}
