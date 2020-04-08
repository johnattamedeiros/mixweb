package com.mixapp.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mixapp.app.web.rest.TestUtil;

public class ExampleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Example.class);
        Example example1 = new Example();
        example1.setId(1L);
        Example example2 = new Example();
        example2.setId(example1.getId());
        assertThat(example1).isEqualTo(example2);
        example2.setId(2L);
        assertThat(example1).isNotEqualTo(example2);
        example1.setId(null);
        assertThat(example1).isNotEqualTo(example2);
    }
}
