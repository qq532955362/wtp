package org.example.user.pojo.dao.dao.model;

import java.util.Date;

public class AppShippingAddress {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.id
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.user_id
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.site
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String site;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.country
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.email
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.first_name
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.last_name
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.zip
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String zip;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.phone
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.address1
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String address1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.address2
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String address2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.city
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.province
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String province;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.province_code
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private String provinceCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.default_address
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private Boolean defaultAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.deleted
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private Boolean deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.create_time
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_shipping_address.update_time
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_shipping_address
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public AppShippingAddress(Integer id, Integer userId, String site, String country, String email, String firstName, String lastName, String zip, String phone, String address1, String address2, String city, String province, String provinceCode, Boolean defaultAddress, Boolean deleted, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.site = site;
        this.country = country;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zip = zip;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.provinceCode = provinceCode;
        this.defaultAddress = defaultAddress;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_shipping_address
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public AppShippingAddress() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.id
     *
     * @return the value of app_shipping_address.id
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.id
     *
     * @param id the value for app_shipping_address.id
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.user_id
     *
     * @return the value of app_shipping_address.user_id
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.user_id
     *
     * @param userId the value for app_shipping_address.user_id
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.site
     *
     * @return the value of app_shipping_address.site
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getSite() {
        return site;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.site
     *
     * @param site the value for app_shipping_address.site
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.country
     *
     * @return the value of app_shipping_address.country
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.country
     *
     * @param country the value for app_shipping_address.country
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.email
     *
     * @return the value of app_shipping_address.email
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.email
     *
     * @param email the value for app_shipping_address.email
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.first_name
     *
     * @return the value of app_shipping_address.first_name
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.first_name
     *
     * @param firstName the value for app_shipping_address.first_name
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.last_name
     *
     * @return the value of app_shipping_address.last_name
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.last_name
     *
     * @param lastName the value for app_shipping_address.last_name
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.zip
     *
     * @return the value of app_shipping_address.zip
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getZip() {
        return zip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.zip
     *
     * @param zip the value for app_shipping_address.zip
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.phone
     *
     * @return the value of app_shipping_address.phone
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.phone
     *
     * @param phone the value for app_shipping_address.phone
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.address1
     *
     * @return the value of app_shipping_address.address1
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.address1
     *
     * @param address1 the value for app_shipping_address.address1
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setAddress1(String address1) {
        this.address1 = address1 == null ? null : address1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.address2
     *
     * @return the value of app_shipping_address.address2
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.address2
     *
     * @param address2 the value for app_shipping_address.address2
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setAddress2(String address2) {
        this.address2 = address2 == null ? null : address2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.city
     *
     * @return the value of app_shipping_address.city
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.city
     *
     * @param city the value for app_shipping_address.city
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.province
     *
     * @return the value of app_shipping_address.province
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.province
     *
     * @param province the value for app_shipping_address.province
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.province_code
     *
     * @return the value of app_shipping_address.province_code
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.province_code
     *
     * @param provinceCode the value for app_shipping_address.province_code
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.default_address
     *
     * @return the value of app_shipping_address.default_address
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.default_address
     *
     * @param defaultAddress the value for app_shipping_address.default_address
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.deleted
     *
     * @return the value of app_shipping_address.deleted
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.deleted
     *
     * @param deleted the value for app_shipping_address.deleted
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.create_time
     *
     * @return the value of app_shipping_address.create_time
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.create_time
     *
     * @param createTime the value for app_shipping_address.create_time
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_shipping_address.update_time
     *
     * @return the value of app_shipping_address.update_time
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_shipping_address.update_time
     *
     * @param updateTime the value for app_shipping_address.update_time
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_shipping_address
     *
     * @mbg.generated Thu Jun 02 15:38:01 CST 2022
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", site=").append(site);
        sb.append(", country=").append(country);
        sb.append(", email=").append(email);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", zip=").append(zip);
        sb.append(", phone=").append(phone);
        sb.append(", address1=").append(address1);
        sb.append(", address2=").append(address2);
        sb.append(", city=").append(city);
        sb.append(", province=").append(province);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", defaultAddress=").append(defaultAddress);
        sb.append(", deleted=").append(deleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}