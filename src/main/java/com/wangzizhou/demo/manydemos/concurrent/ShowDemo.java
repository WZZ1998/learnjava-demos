package com.wangzizhou.demo.manydemos.concurrent;

class Weapon {
    Integer hurt;
}
class Soldier {
    Integer HP;
    String name;
    Weapon weapon;

    Integer fight() {
        return this.weapon.hurt;
    }
    Double fightBack() {
        return this.weapon.hurt*0.3;
    }

    void bleed(Integer cnt) {
        this.HP = this.HP - cnt;
    }

    public Soldier(Integer HP, String name, Weapon weapon) {
        this.HP = HP;
        this.name = name;
        this.weapon = weapon;
    }
}
public class ShowDemo {
    public static void main(String[] args) {
        X x = new X();
        XA xa = new X();
    }
}


abstract class XA{

}
class X extends XA{

}



