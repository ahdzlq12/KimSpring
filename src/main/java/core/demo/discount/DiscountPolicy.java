package core.demo.discount;

import core.demo.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
