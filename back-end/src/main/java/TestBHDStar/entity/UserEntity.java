package TestBHDStar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.search.engine.backend.types.ObjectStructure;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Indexed
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @IndexedEmbedded(structure = ObjectStructure.NESTED)
    @JoinColumn(name = "account_id")
    private AccountEntity account ;

    public UserEntity(int id, Date createdDate, Date modifiedDate, String createdBy, String modifiedBy) {
        super(id, createdDate, modifiedDate, createdBy, modifiedBy);
    }

    @FullTextField(analyzer = "standard")
    @Column(name = "last_name")
    private String lastName;


    @FullTextField(analyzer = "standard")
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthday")
    private Date birthDay;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Receipt> receiptList;


    @Override
    public String toString() {
        return "UserEntity{" +
                "account=" + account.getEmail()+
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
