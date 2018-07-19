package com.guidewire.signagecenter.mapper;

public interface DTOMapper<A, B> {
    B mapToDTO(A a);

    A mapFromDTO(B b);
}
