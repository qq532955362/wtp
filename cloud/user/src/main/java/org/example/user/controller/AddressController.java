package org.example.user.controller;

import org.example.user.pojo.ResponseData;
import org.example.user.pojo.request.address.AddAddressRequest;
import org.example.user.pojo.request.address.UpdateAddressRequest;
import org.example.user.pojo.vo.AddressVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址管理
 * 客户地址管理
 *
 * @module app地址管理
 */
@RestController
@RequestMapping("/front/address")
public class AddressController {

    /**
     * 获取客户地址列表
     *
     * @return DUPLICATE_NAME(1001)
     * @rest
     */
    @GetMapping
    public ResponseEntity<ResponseData<List<AddressVO>>> getAddress() {
        return null;
    }

    /**
     * 获取地址详情
     *
     * @param id 地址标识
     * @return NOT_FOUND(1002)
     * @rest
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<AddressVO>> getAddress(@PathVariable(name = "id") Integer id) {
        return null;
    }

    /**
     * 新增收货地址
     *
     * @param addAddressRequest 新增请求
     * @return NOT_FOUND(1002)
     * @rest
     */
    @PostMapping
    public ResponseEntity<ResponseData<Boolean>> addAddress(@RequestBody AddAddressRequest addAddressRequest) {
        return null;
    }

    /**
     * 删除收货地址
     *
     * @param id 地址id标识
     * @return NOT_FOUND(1002)
     * @rest
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Boolean>> deleteAddress(@PathVariable("id") Integer id) {
        return null;
    }

    /**
     * 更新收货地址
     *
     * @param updateAddressRequest 更新请求
     * @return NOT_FOUND(1002)
     * @rest
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Boolean>> updateAddress(@PathVariable("id") UpdateAddressRequest updateAddressRequest) {
        return null;
    }

    /**
     * 设为默认收货地址
     *
     * @param id 地址标识
     * @return NOT_FOUND(1002)
     * @rest
     */
    @PutMapping("/default/{id}")
    public ResponseEntity<ResponseData<Boolean>> updateDefaultAddress(@PathVariable("id") Integer id) {
        return null;
    }
}
