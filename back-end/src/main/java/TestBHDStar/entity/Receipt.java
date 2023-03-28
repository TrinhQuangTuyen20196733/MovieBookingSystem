package TestBHDStar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receipt")
public class Receipt extends BaseEntity {

    @Column(name = "discount")
    private int discount;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
    })
    @JoinColumn(name = "user_id")
    private  UserEntity user;
    @OneToMany(mappedBy = "receipt",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SeatOnSessionEntity> seatOnSessionEntityList;

    @ManyToMany(mappedBy = "receipts",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private List<ServiceEntity> services;

}
