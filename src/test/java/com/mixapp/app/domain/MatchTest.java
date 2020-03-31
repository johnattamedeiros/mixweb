package com.mixapp.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mixapp.app.web.rest.TestUtil;

public class MatchTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Match.class);
        Match match1 = new Match();
        match1.setId(1L);
        Match match2 = new Match();
        match2.setId(match1.getId());
        assertThat(match1).isEqualTo(match2);
        match2.setId(2L);
        assertThat(match1).isNotEqualTo(match2);
        match1.setId(null);
        assertThat(match1).isNotEqualTo(match2);
    }
}
