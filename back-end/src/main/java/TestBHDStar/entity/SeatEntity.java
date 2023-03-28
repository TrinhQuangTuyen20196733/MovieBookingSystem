package TestBHDStar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seat")
public class SeatEntity extends BaseEntity {
    @Column(name = "seat_row")
    private int row;
    @Column(name = "seat_column")
    private int column;
    @Column(name = "type")
    private String type;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
    })
    @JoinColumn(name = "room_id")
    private RoomEntity room;

}
