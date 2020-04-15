package item34;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OperationTest {

    static final Map<String, Operation> stringToEnum = Stream.of(Operation.values()).collect(
            toMap(Object::toString, operation -> operation)
    );

    static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    @DisplayName("symbol 에 따른 Operation이 나오는지")
    @MethodSource(value = "operationBySymbol")
    @ParameterizedTest
    void test(String symbol, Operation operation) {
        assertThat(fromString(symbol)).isEqualTo(Optional.of(operation));
    }

    static Stream<Arguments> operationBySymbol() {
        return Stream.of(
                Arguments.of("+", Operation.PLUS),
                Arguments.of("-", Operation.MINUS),
                Arguments.of("*", Operation.TIMES),
                Arguments.of("/", Operation.DIVIDE));
    }


}
