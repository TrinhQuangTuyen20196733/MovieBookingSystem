package TestBHDStar.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
private int id;
private String lastName;
private String firstName;
private  String phoneNumber;
private  String address;
private Date birthDay;
private AccountDTO accountDTO;

}
