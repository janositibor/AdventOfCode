package tzjanosi.y2016.day07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IPAddressTest {
    public static Stream<Arguments> supportTLS() {
        return Stream.of(
                Arguments.of("abba[mnop]qrst", true),
                Arguments.of("abcd[bddb]xyyx", false),
                Arguments.of("aaaa[qwer]tyui", false),
                Arguments.of("ioxxoj[asdfgh]zxcvbn", true)
        );
    }

    @Test
    void create() {
        IPAddress ipAddress = new IPAddress("wysextplwqpvipxdv[srzvtwbfzqtspxnethm]syqbzgtboxxzpwr[kljvjjkjyojzrstfgrw]obdhcczonzvbfby[svotajtpttohxsh]cooktbyumlpxostt");
        assertThat((ipAddress.getOutsideBrackets()))
                .hasSize(4)
                .containsExactly("wysextplwqpvipxdv", "syqbzgtboxxzpwr", "obdhcczonzvbfby", "cooktbyumlpxostt");
        assertThat((ipAddress.getInsideBrackets()))
                .hasSize(3)
                .containsExactly("srzvtwbfzqtspxnethm", "kljvjjkjyojzrstfgrw", "svotajtpttohxsh");

        ipAddress = new IPAddress("abba[mnop]qrst");
        assertThat((ipAddress.getOutsideBrackets()))
                .hasSize(2)
                .containsExactly("abba", "qrst");
        assertThat((ipAddress.getInsideBrackets()))
                .hasSize(1)
                .containsExactly("mnop");

        ipAddress = new IPAddress("[x]abba[mnop]qrst");
        assertThat((ipAddress.getOutsideBrackets()))
                .hasSize(2)
                .containsExactly("abba", "qrst");
        assertThat((ipAddress.getInsideBrackets()))
                .hasSize(2)
                .containsExactly("x", "mnop");
    }

    @ParameterizedTest
    @MethodSource
    void supportTLS(String input, boolean result) {
        IPAddress ipAddress = new IPAddress(input);
        assertEquals(result, ipAddress.supportTLS());
    }
}