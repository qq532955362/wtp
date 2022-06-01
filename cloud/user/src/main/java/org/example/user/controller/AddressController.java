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
@RequestMapping("/app-mall/v1/address")
public class AddressController {

    /**
     * 获取客户地址列表
     *
     * @return DUPLICATE_NAME(1001)
     * @rest
     */
    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<AddressVO>>> getAddress() {
        return null;
    }

    /**
     * 获取地址详情
     *
     * @param id 地址标识
     * @return
     * @rest
     */
    @GetMapping
    public ResponseEntity<ResponseData<AddressVO>> getAddress(@RequestParam("id") Integer id) {
        return null;
    }

    /**
     * 新增收货地址
     *
     * @param addAddressRequest 新增请求
     * @return
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
    @DeleteMapping
    public ResponseEntity<ResponseData<Boolean>> deleteAddress(@RequestParam("id") Integer id) {
        return null;
    }

    /**
     * 更新收货地址
     *
     * @param updateAddressRequest 更新请求
     * @return
     * @rest
     */
    @PutMapping
    public ResponseEntity<ResponseData<Boolean>> updateAddress(@RequestParam("id") Integer id, @RequestBody UpdateAddressRequest updateAddressRequest) {
        return null;
    }

    /**
     * 设为默认收货地址
     *
     * @param id 地址标识
     * @return
     * @rest
     */
    @PutMapping("/default")
    public ResponseEntity<ResponseData<Boolean>> updateDefaultAddress(@RequestParam("id") Integer id) {
        return null;
    }
}
