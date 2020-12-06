package aoc.day06;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Question {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h"),
    I("i"),
    J("j"),
    K("k"),
    L("l"),
    M("m"),
    N("n"),
    O("o"),
    P("p"),
    Q("q"),
    R("r"),
    S("s"),
    T("t"),
    U("u"),
    V("v"),
    W("w"),
    X("x"),
    Y("y"),
    Z("z");

    private String letter;

    public static Question toField(String key) {
        return Question.valueOf(uppercaseOf(key));
    }

    private static String uppercaseOf(String key) {
        if (key == null) {
            return key;
        }
        return key.toUpperCase();
    }

}
