package item32;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Item32Test {

    static void dangerous(List<String>... stringLists) { //List<List<String>> stringLists ??
        // initial: stringLists[0] = Arrays.asList("a", "b", "c")
        List<Integer> intList = Collections.singletonList(42);

        // objects ---> stringLists ----> [Arrays.asList("a", "b", "c")]
        Object[] objects = stringLists;

        // objects[0] ----> stringLi
        // sts[0] -----> intList
        objects[0] = intList;

        int result = Integer.class.cast(stringLists[0].get(0));
        assertThat(result).isEqualTo(42);
    }

    @DisplayName("가변인스와 제네릭을 함께쓸 때")
    @Test
    void 가변인수_제네릭() {
        dangerous(Arrays.asList("a", "b", "c"));
    }
}
