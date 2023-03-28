package TestBHDStar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="movie_system")
public class MovieSystemEntity extends  BaseEntity {
    @Column(name="name",unique = true)
    private String name;
    @Column(name = "address",unique = true)
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "movieSystem",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RoomEntity> roomList;
}
