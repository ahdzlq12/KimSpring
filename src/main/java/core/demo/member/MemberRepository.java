package core.demo.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(long id);
}
