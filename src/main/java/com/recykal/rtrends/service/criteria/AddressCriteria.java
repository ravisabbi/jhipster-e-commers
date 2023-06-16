package com.recykal.rtrends.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.recykal.rtrends.domain.Address} entity. This class is used
 * in {@link com.recykal.rtrends.web.rest.AddressResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /addresses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AddressCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter pinCode;

    private StringFilter doorNo;

    private StringFilter city;

    private LongFilter customerId;

    private Boolean distinct;

    public AddressCriteria() {}

    public AddressCriteria(AddressCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.pinCode = other.pinCode == null ? null : other.pinCode.copy();
        this.doorNo = other.doorNo == null ? null : other.doorNo.copy();
        this.city = other.city == null ? null : other.city.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AddressCriteria copy() {
        return new AddressCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getPinCode() {
        return pinCode;
    }

    public LongFilter pinCode() {
        if (pinCode == null) {
            pinCode = new LongFilter();
        }
        return pinCode;
    }

    public void setPinCode(LongFilter pinCode) {
        this.pinCode = pinCode;
    }

    public StringFilter getDoorNo() {
        return doorNo;
    }

    public StringFilter doorNo() {
        if (doorNo == null) {
            doorNo = new StringFilter();
        }
        return doorNo;
    }

    public void setDoorNo(StringFilter doorNo) {
        this.doorNo = doorNo;
    }

    public StringFilter getCity() {
        return city;
    }

    public StringFilter city() {
        if (city == null) {
            city = new StringFilter();
        }
        return city;
    }

    public void setCity(StringFilter city) {
        this.city = city;
    }

    public LongFilter getCustomerId() {
        return customerId;
    }

    public LongFilter customerId() {
        if (customerId == null) {
            customerId = new LongFilter();
        }
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AddressCriteria that = (AddressCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(pinCode, that.pinCode) &&
            Objects.equals(doorNo, that.doorNo) &&
            Objects.equals(city, that.city) &&
            Objects.equals(customerId, that.customerId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pinCode, doorNo, city, customerId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (pinCode != null ? "pinCode=" + pinCode + ", " : "") +
            (doorNo != null ? "doorNo=" + doorNo + ", " : "") +
            (city != null ? "city=" + city + ", " : "") +
            (customerId != null ? "customerId=" + customerId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
