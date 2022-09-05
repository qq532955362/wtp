package org.example.user.pojo.assembler;

import org.example.user.pojo.domain.AppShippingAddress;
import org.example.user.pojo.request.address.AddAddressRequest;
import org.example.user.pojo.vo.AddressVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressAssembler {

    AddressVO requestToVO(AddAddressRequest addAddressRequest);

    List<AddressVO> requestListToVOList(List<AddAddressRequest> addAddressRequestList);

    AppShippingAddress requestToModel(AddAddressRequest addAddressRequest);

}
