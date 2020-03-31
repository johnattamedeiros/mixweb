package com.mixapp.app.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mixapp.app.domain.Match} entity. This class is used
 * in {@link com.mixapp.app.web.rest.MatchResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /matches?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MatchCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter iduser;

    public MatchCriteria() {
    }

    public MatchCriteria(MatchCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.iduser = other.iduser == null ? null : other.iduser.copy();
    }

    @Override
    public MatchCriteria copy() {
        return new MatchCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIduser() {
        return iduser;
    }

    public void setIduser(LongFilter iduser) {
        this.iduser = iduser;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MatchCriteria that = (MatchCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(iduser, that.iduser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        iduser
        );
    }

    @Override
    public String toString() {
        return "MatchCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (iduser != null ? "iduser=" + iduser + ", " : "") +
            "}";
    }

}
