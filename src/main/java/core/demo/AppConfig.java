package core.demo;

import core.demo.discount.DiscountPolicy;
import core.demo.discount.RateDiscountPolicy;
import core.demo.member.MemberRepository;
import core.demo.member.MemberService;
import core.demo.member.MemberServiceImpl;
import core.demo.member.MemoryMemberRepository;
import core.demo.order.OrderService;
import core.demo.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //구성(설정) 정보 -> @configuration붙은 AppConfig를 구성정보로 사용한다.(appconfig가 아니면 안되나? -> o)
public class AppConfig {

    @Bean //(name = "dasdasd") 이름 추가 가능. 설정안하면  메서드 이름 -> 보통 메서드 이름으로 함
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
