package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제
 * ConcurrentHasshMap, AtomicLong 사용 고려!
 */

public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //static 사용

    private MemberRepository(){

    }

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values()); //store에 있는 모든 값 새로 담아서 넘겨줌 -> store값 변경 막기 위한(깊/얕은복사)
    }

    public void clearStore(){
        store.clear();
    }
}
