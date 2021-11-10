package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static com.codebreeze.algorithms.Utils.getFile;
import static java.nio.file.Files.readString;
import static org.assertj.core.api.Assertions.assertThat;

class FormGeneratorTest {

    @Test
    void apply() throws IOException {
        // given
        // when
        FormGenerator formGenerator = new FormGenerator();

        // then
        assertThat(formGenerator.apply("form-generator/schema-for-FormGenerator.txt", new HashMap<>() {
            {
                put("sender_name", "Runk Holla");
                put("sender_address", "126 Dinosaur Square, Old City, Ancient State, Sunk Country");
                put("date", "2020-09-03");
                put("recipient_name", "Roosevelt Stucky");
                put("recipient_address", "876 Cracking Road, Bustown City, Clean State, Unstable Continent");
                put("recipient_title", "Ms");
                put("event_name", "Duke Celebration");
                put("event_address", "456 Night owl Street, Stubborn City, Old State, United Steak Of Africa");
                put("event_date", "2021-12-03");
                put("event_time", "11:13pm");
                put("confirmation_date", "2020-11-12");
                put("organizer_contact_number", "3456789");
                put("organizer_email", "mango@fruit.com");
                put("signature", "Bluely_SiGnAtUrE");
            }
        })).isEqualTo(readString(getFile("form-generator/test-output.txt").toPath()));
    }
}
