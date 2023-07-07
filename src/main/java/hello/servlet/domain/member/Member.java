package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    //private String userName;
    private String username;
    private int age;

    public Member(){}

    public Member(String username, int age){
      //  this.userName = userName;
        this.username = username;
        this.age = age;
    }


}
