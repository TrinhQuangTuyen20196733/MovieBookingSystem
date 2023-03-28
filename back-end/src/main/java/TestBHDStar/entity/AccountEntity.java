package TestBHDStar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Indexed
@Entity
@Table(name="account")
public class AccountEntity extends BaseEntity {
    @KeywordField
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns  = @JoinColumn(name="role_id")

    )
    private List<RoleEntity> roles = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account",fetch = FetchType.EAGER)
    private UserEntity user;



    @Override
    public String toString() {
        return "AccountEntity{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }







}
