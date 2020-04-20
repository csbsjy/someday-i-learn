package item37;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

//TODO: 열거타입은 근본적으로 불변이라 모든 필드는 final 이어야 한다.
public enum Phase {
    SOLID, LIQUID, GAS; // 상태

    public enum Transition { // 전이
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

        private static final Map<Phase, Map<Phase, Transition>> // Key 가 이전상태, Value가 이전상태에서 상태변이
                m = Stream.of(values()).collect(
                groupingBy(t -> t.from, () -> new EnumMap<>(Phase.class), // 이전 상태로 묶고
                        toMap(t -> t.to, t -> t, // value가 Map이라서, key는 이후상태, value는 변이상수
                                (x, y) -> y, () -> new EnumMap<>(Phase.class))));
        final Phase from;
        final Phase to;

        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        // 같은데이터가 들어오면 y 로 선택한다.
        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }
}
