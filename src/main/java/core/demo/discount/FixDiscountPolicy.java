package core.demo.discount;

import core.demo.member.Grade;
import core.demo.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) //enum은 ==으로 비교
            return discountFixAmount;

        return 0;
    }
}
