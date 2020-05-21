package cn.edu.nenu.service;

import org.springframework.stereotype.Service;

/**
 * OrganizationService Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-04-30 16:13
 */
@Service
public class OrganizationService {
    //
    //final
    //OrganizationRepository organizationRepo;
    //final
    //OrganizationMapper organizationMapper;
    //
    //public OrganizationService(OrganizationRepository organizationRepo, OrganizationMapper organizationMapper) {
    //    this.organizationRepo = organizationRepo;
    //    this.organizationMapper = organizationMapper;
    //}
    //
    ///**
    // * 保存
    // */
    //public Organization save(Organization entity){
    //    return organizationRepo.save(entity);
    //}
    //
    ///**
    // * 更新
    // */
    //public void update(Organization entity){
    //    organizationRepo.save(entity);
    //}
    //
    ///**
    // * 删除
    // */
    //public void delete(Integer id){
    //    organizationRepo.delete(id);
    //}
    //public void delete(String autoCode){
    //    organizationMapper.delete(autoCode);
    //}
    //
    ///**
    // * 获取机构信息
    // * @return
    // */
    //public Organization get(Integer id){
    //    return organizationRepo.findOne(id);
    //}
    //public Organization get(String autoCode){
    //    return organizationRepo.findByAutoCode(autoCode);
    //}
    ///**
    // * 上移，改变的是排序规则，其他不改变
    // * @return
    // */
    //public String moveUp(Integer id){
    //    return null;
    //}
    //
    ///**
    // * 下移，改变的是排序规则，其他不改变
    // * @return
    // */
    //public String moveDown(Integer id){
    //    return null;
    //}
    //
    ///**
    // * 获取子节点
    // * @return
    // */
    //public List getSubList(Integer id){
    //    return organizationRepo.findByPId(id);
    //}
    //public List getSubList(String autoCode){
    //    return null;
    //}
    //
    ///**
    // * 获取本级最大四位一体编码
    // * @param autoCode
    // * @return
    // */
    //public String getMaxAutoCode(String autoCode){
    //    //截取掉后四位，获得上级编码，调用getSubMaxAutoCode获得本级最大编码
    //    return null;
    //}
    //
    ///**
    // * 获取下级最大四位一体编码
    // * @param autoCode
    // * @return
    // */
    //public String getSubMaxAutoCode(String autoCode){
    //    // 0001-> 模糊查询0001____->将____4位截取出来转换为数字->在转换的数字中找最大值->最大值+1->格式化位4位编码XXXX->0001XXXX
    //    return null;
    //}
    //public String getSubMaxAutoCode(Integer id){
    //    //返回值是当前最大值+1格式化之后的值，000100040003->0003->3+1->4->0004->000100040004
    //    //Organization organization = organizationRepo.findOne(id);
    //    //String autoCode = organization.getAutoCode();
    //    //return StringUtil.getNextAutoCode(autoCode,4);
    //    return null;
    //}
    //
    ///**
    // * 获取本级排序最大值
    // * @return
    // */
    //public float getMaxSort(Integer id){
    //    //根据id获取pID，调用getSubMaxSort获取最大值
    //    Organization organization = organizationRepo.findOne(id);
    //    return getSubMaxSort(organization.getPId());
    //}
    //
    ///**
    // * 获取下级排序最大值
    // * @param pId
    // * @return
    // */
    //public float getSubMaxSort(Integer pId){
    //    Float maxSort = organizationMapper.getSubMaxSort(pId);
    //    if (maxSort == null){
    //        maxSort=1f;
    //    }
    //    return maxSort+1f;
    //}
}
