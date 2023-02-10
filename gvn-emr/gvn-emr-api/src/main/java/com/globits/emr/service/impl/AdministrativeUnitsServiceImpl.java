package com.globits.emr.service.impl;

import com.globits.core.domain.AdministrativeUnit;
import com.globits.core.repository.AdministrativeUnitRepository;
import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.emr.dto.AdministrativeUnitDto;
import com.globits.emr.dto.function.AdministrativeUnitImportExcel;
import com.globits.emr.dto.search.AdministrativeUnitSearchDto;
import com.globits.emr.dto.search.SearchDto;
import com.globits.emr.service.AdministrativeUnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AdministrativeUnitsServiceImpl extends GenericServiceImpl<AdministrativeUnit, UUID> implements AdministrativeUnitsService {
    @Autowired
    AdministrativeUnitRepository repository;
//    @Autowired
//    PatientRepository patientRepository;
//    @Autowired
//    private UserHealthOrganizationService userHealthOrganizationService;
//    @Autowired
//    private HealthOrganizationRepository healthOrganizationRepository;
    @Override
    public Page<AdministrativeUnitDto> searchByDto(AdministrativeUnitSearchDto dto) {
        if (dto == null)
            return null;

//        UserInfoDto userInfor = null;
//        List<UUID> listUUIDUnitByUser = new ArrayList<UUID>();
//        if(dto.getIsGetUserLogin() != null && dto.getIsGetUserLogin()) {
//            userInfor = userHealthOrganizationService.getAllUserInfoByLogin();
//            if (userInfor != null && !userInfor.isAdmin() && !userInfor.isNation()) {
//                //Lấy danh sách đơn vị hành chính theo user
//                if((userInfor.isRegion() || userInfor.isProvince() || userInfor.isDistrict() || userInfor.isOrganization() || userInfor.isDepartment() ) && userInfor.getAdminUnitIds() != null && userInfor.getAdminUnitIds().size() > 0) {
//                    listUUIDUnitByUser.addAll(userInfor.getAdminUnitIds());
//                    for(UUID id: userInfor.getAdminUnitIds()) {
//                        AdministrativeUnit adminUnit = repository.findById(id).orElse(null);
//                        List<UUID> uuids= getAllAdministrativeIdByParentId(id, false);
//                        if(uuids != null && uuids.size() > 0){
//                            listUUIDUnitByUser.addAll(uuids);
//                        }
//                        if(adminUnit != null && adminUnit.getParent() != null && adminUnit.getParent().getId() != null) {
//                            listUUIDUnitByUser.add(adminUnit.getParent().getId());
//                            if(adminUnit.getParent().getParent() != null && adminUnit.getParent().getParent().getId() != null) {
//                                listUUIDUnitByUser.add(adminUnit.getParent().getParent().getId());
//                            }
//                        }
//
//                    }
//                }
//            }
//        }


        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();

        if (pageIndex > 0)
            pageIndex--;
        else
            pageIndex = 0;
        String sqlCount = "select count(entity.id) from AdministrativeUnit as entity ";
        String hql = "SELECT new com.globits.vitimes.patient.dto.AdministrativeUnitDto(entity.id, entity.name, entity.code, entity.level) FROM AdministrativeUnit entity";
        String where = " WHERE (1=1)";
        String orderBy = " ORDER BY entity.level, entity.name, entity.code ";

        if (dto.getKeyword() != null && !dto.getKeyword().isEmpty())
            where += " AND (entity.code LIKE :text OR entity.name LIKE :text) ";

        if (dto.getParentId() != null && dto.getProvinceId() == null && dto.getDistrictId() == null)
            where += " AND entity.parent.id = :parentId";

        if (dto.getLevel() != null)
            where += " AND entity.level = :level";

        if(dto.getProvinceId() != null && dto.getDistrictId() == null)
            where += " AND entity.id= :provinceId AND entity.parent.id= :parentId";

        if (dto.getDistrictId() != null && dto.getProvinceId() != null && dto.getParentId() != null && dto.getCommuneId() == null)
            where += " AND entity.id= :districtId AND entity.parent.id= :provinceId";

        if(dto.getCommuneId() != null && dto.getDistrictId() != null && dto.getProvinceId() != null && dto.getParentId() != null)
            where += " AND entity.id= :communeId AND entity.parent.id= :districtId";

//        if(listUUIDUnitByUser != null && listUUIDUnitByUser.size() > 0) {
//            where += " AND (entity.id in (:listUUIDUnitByUser) )";
//        }
        hql += where + orderBy;
        sqlCount += where;
        Query query = manager.createQuery(hql, AdministrativeUnitDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
            query.setParameter("text", "%" + dto.getKeyword() + "%");
            qCount.setParameter("text", '%' + dto.getKeyword().trim() + '%');
        }

        if (dto.getParentId() != null && dto.getProvinceId() == null && dto.getDistrictId() == null) {
            query.setParameter("parentId", dto.getParentId());
            qCount.setParameter("parentId", dto.getParentId());
        }

        if (dto.getLevel() != null) {
            query.setParameter("level", dto.getLevel());
            qCount.setParameter("level", dto.getLevel());
        }

//        if(dto.getRegionId() != null) {
//            query.setParameter("regionId", dto.getRegionId());
//            qCount.setParameter("regionId", dto.getRegionId());
//        }

        if(dto.getProvinceId() != null && dto.getDistrictId() == null) {
            query.setParameter("provinceId", dto.getProvinceId());
            qCount.setParameter("provinceId", dto.getProvinceId());
            query.setParameter("parentId", dto.getParentId());
            qCount.setParameter("parentId", dto.getParentId());
        }

        if(dto.getDistrictId() != null && dto.getProvinceId() != null && dto.getParentId() != null && dto.getCommuneId() == null) {
            query.setParameter("districtId", dto.getDistrictId());
            qCount.setParameter("districtId", dto.getDistrictId());
            query.setParameter("provinceId", dto.getProvinceId());
            qCount.setParameter("provinceId", dto.getProvinceId());
        }

        if(dto.getCommuneId() != null && dto.getDistrictId() != null && dto.getProvinceId() != null && dto.getParentId() != null) {
            query.setParameter("communeId", dto.getCommuneId());
            qCount.setParameter("communeId", dto.getCommuneId());
            query.setParameter("districtId", dto.getDistrictId());
            qCount.setParameter("districtId", dto.getDistrictId());
        }

//        if(listUUIDUnitByUser != null && listUUIDUnitByUser.size() > 0) {
//            query.setParameter("listUUIDUnitByUser", listUUIDUnitByUser);
//            qCount.setParameter("listUUIDUnitByUser", listUUIDUnitByUser);
//        }
        Page<AdministrativeUnitDto> result = null;
        List<AdministrativeUnitDto> entities = query.getResultList();

        int startPosition = pageIndex * pageSize;
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        entities = query.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        result = new PageImpl<AdministrativeUnitDto>(entities, pageable, count);

        return result;
    }

    @Override
    public AdministrativeUnitDto getById(UUID id) {
        if (id != null) {
            AdministrativeUnit entity = repository.findById(id).orElse(null);
            if (entity != null) {
                return new AdministrativeUnitDto(entity, true, false);
            }
        }
        return null;
    }

    @Override
    public AdministrativeUnitDto saveOrUpdate(AdministrativeUnitDto dto, UUID id) {
        if (dto != null) {
            AdministrativeUnit administrativeUnit = null;

            if (id != null) {// trường hợp edit
                administrativeUnit = repository.getOne(id);
            } else if (dto.getId() != null) {
                administrativeUnit = this.repository.getOne(dto.getId());
            }

            if (administrativeUnit == null) {// trường hợp thêm mới
                administrativeUnit = new AdministrativeUnit();
            }

            if (dto.getName() != null)
                administrativeUnit.setName(dto.getName());

            if (dto.getCode() != null)
                administrativeUnit.setCode(dto.getCode());
            if (dto.getLinePath() != null) {
                administrativeUnit.setLinePath(dto.getLinePath());
            }
            administrativeUnit.setLevel(dto.getLevel());
            if (dto.getParent() != null) {
                AdministrativeUnit parent = null;
                if (dto.getParent().getId() != null) {
                    parent = repository.getOne(dto.getParent().getId());
                } else if (dto.getParent().getCode() != null) {
                    List<AdministrativeUnit> aus = repository.findListByCode(dto.getParent().getCode());
                    if (aus != null && aus.size() == 1) {
                        parent = aus.get(0);
                    } else if (aus != null && aus.size() > 1) {
                        for (AdministrativeUnit item : aus) {
                            if (item.getName().equals(dto.getParent().getName())) {
                                parent = item;
                                break;
                            }
                        }
                    }
                }
                if (parent != null) {
                    administrativeUnit.setParent(parent);
                    if (administrativeUnit.getLevel() == null && parent.getLevel() != null && parent.getLevel() > 0) {
                        administrativeUnit.setLevel(parent.getLevel() + 1);
                    }
                }
            }
            else {
                if(administrativeUnit.getLevel() == null) {
                    administrativeUnit.setLevel(3); // level = 3 là cấp thành phố
                }
                administrativeUnit.setParent(null);
            }

            repository.save(administrativeUnit);
            dto.setId(administrativeUnit.getId());
            return dto;
        }
        return null;
    }

    @Override
    public Boolean deleteById(UUID id) {
        if (id != null) {
//            Integer countPatientByAdministrativeUnit = patientRepository.countPatientByAdministrativeUnit(id);
//            if(countPatientByAdministrativeUnit != 0) {
//                return false;
//            }
            AdministrativeUnit entity = repository.getOne(id);
            if (entity != null) {
                repository.deleteById(id);
                return true;
            }
        }
        return null;
    }

    @Override
    public List<AdministrativeUnitDto> getAllChildByParentId(UUID parentId) {
        String sql = "SELECT new com.globits.vitimes.patient.dto.AdministrativeUnitDto(entity,true,false) FROM AdministrativeUnit entity where entity.parent.id =:parentId ";
        Query q = manager.createQuery(sql, AdministrativeUnitDto.class);
        q.setParameter("parentId", parentId);
        List<AdministrativeUnitDto> dtos = q.getResultList();
        return dtos;
    }

    @Override
    public List<AdministrativeUnitDto> getAllByLevel(Integer level) {
        String sql = "SELECT new com.globits.vitimes.patient.dto.AdministrativeUnitDto(entity,true,false) FROM AdministrativeUnit entity where entity.level =:level ";
        Query q = manager.createQuery(sql, AdministrativeUnitDto.class);
        q.setParameter("level", level);
        List<AdministrativeUnitDto> dtos = q.getResultList();
        return dtos;
    }

    @Override
    public List<AdministrativeUnitDto> getAllByLevelAndParentId(Integer level, UUID parentId) {
        String sql = "SELECT new com.globits.vitimes.patient.dto.AdministrativeUnitDto(entity,true,false) FROM AdministrativeUnit entity where entity.level =:level AND entity.parent.id =:parentId ";
        Query q = manager.createQuery(sql, AdministrativeUnitDto.class);
        q.setParameter("level", level);
        q.setParameter("parentId", parentId);
        List<AdministrativeUnitDto> dtos = q.getResultList();
        return dtos;
    }

    @Override
    public Page<AdministrativeUnitDto> getRootUnit(SearchDto dto) {

        if (dto == null)
            return null;

        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();

        if (pageIndex > 0)
            pageIndex--;
        else
            pageIndex = 0;

        String sqlCount = "select count(entity.id) from AdministrativeUnit as entity ";
        String sql = "SELECT new com.globits.vitimes.patient.dto.AdministrativeUnitDto(entity) FROM AdministrativeUnit entity ";
        String where = " WHERE entity.parent is null ";
        String orderBy = " ORDER BY entity.name ";

        if (dto.getKeyword() != null && !dto.getKeyword().isEmpty())
            where += " AND entity.name LIKE :text";

        sql += where + orderBy;
        sqlCount += where;
        Query query = manager.createQuery(sql, AdministrativeUnitDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
            query.setParameter("text", "%" + dto.getKeyword() + "%");
            qCount.setParameter("text", '%' + dto.getKeyword().trim() + '%');
        }

        int totalResult = query.getResultList().size();
        int startPosition = pageIndex * pageSize;

        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);

        // Query q = manager.createQuery(sql, AdministrativeUnitDto.class);
        List<AdministrativeUnitDto> listAdministrativeUnitDto = query.getResultList();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return new PageImpl<>(listAdministrativeUnitDto, pageable, totalResult);
    }

    @Override
    public Boolean checkDuplicateCode(AdministrativeUnitDto dto) {
        if (dto.getCode() != null && StringUtils.hasText(dto.getCode())) {
            List<AdministrativeUnit> list = repository.findListByCode(dto.getCode());
            if (list != null && list.size() > 0) {
                if (dto.getId() != null && list.get(0).getId().equals(dto.getId())) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public List<AdministrativeUnitDto> importExcel(List<AdministrativeUnitImportExcel> dtos) {
        if (dtos != null && dtos.size() > 0) {
            List<AdministrativeUnitDto> listData = new ArrayList<>();
            for (AdministrativeUnitImportExcel dto : dtos) {
                AdministrativeUnit adminUnit = null;
                AdministrativeUnit district = null;
                AdministrativeUnit commune = null;

                if (dto.getIdTinh() != null) {
                    List<AdministrativeUnit> listUnit = this.repository.findListByCode(dto.getIdTinh());
                    if (listUnit != null && listUnit.size() > 0) {
                        adminUnit = listUnit.get(0);
                    }
                    if (adminUnit == null) {
                        adminUnit = new AdministrativeUnit();
                        adminUnit.setCode(dto.getIdTinh());
                        if (dto.getTenTinh() != null) {
                            adminUnit.setName(dto.getTenTinh());
                        }
                        adminUnit.setLevel(3);
                        adminUnit = this.repository.save(adminUnit);
                    }
                }

                if (adminUnit != null) {
                    if (dto.getIdHuyen() != null) {
                        List<AdministrativeUnit> listUnit = this.repository.findListByCode(dto.getIdHuyen());
                        if (listUnit != null && listUnit.size() > 0) {
                            district = listUnit.get(0);
                        }
                        if (district == null) {
                            district = new AdministrativeUnit();
                            district.setParent(adminUnit);
                            district.setCode(dto.getIdHuyen());
                            if (dto.getTenHuyen() != null) {
                                district.setName(dto.getTenHuyen());
                            }
                            district.setLevel(4);
                            district = this.repository.save(district);
                        }
                    }
                }

                if (district != null) {
                    if (dto.getIdXa() != null) {
                        List<AdministrativeUnit> listUnit = this.repository.findListByCode(dto.getIdXa());
                        if (listUnit != null && listUnit.size() > 0) {
                            commune = listUnit.get(0);
                        }
                        if (commune == null) {
                            commune = new AdministrativeUnit();
                            commune.setParent(district);
                            commune.setCode(dto.getIdXa());
                            if (dto.getTenXa() != null) {
                                commune.setName(dto.getTenXa());
                            }
                            commune.setLevel(5);
                            commune = this.repository.save(commune);
                        }
                    }
                }

            }
            return listData;
        }
        return null;
    }

    @Override
    public List<UUID> getAllAdministrativeIdByParentId(UUID parentId, Boolean isCommunes){
        AdministrativeUnit parent = repository.findById(parentId).orElse(null);
        if(parent != null && parent.getId() != null) {
            List<UUID> ret = new ArrayList<UUID>();
            List<AdministrativeUnit> chidren = new ArrayList<AdministrativeUnit>();

            ret.add(parent.getId());
            this.addChldren(parent, chidren, isCommunes);
            if(chidren != null && chidren.size() >0) {
                for(AdministrativeUnit admin: chidren) {
                    ret.add(admin.getId());
                }
            }
            return ret;
        }
        return null;
    }

    private void addChldren(AdministrativeUnit parent, List<AdministrativeUnit> children, Boolean isCommunes) {
        if(parent != null && parent.getSubAdministrativeUnits() != null) {
            if(!isCommunes && parent.getLevel() != null && parent.getLevel() != 5 && parent.getLevel() != 4) {
                for(AdministrativeUnit child: parent.getSubAdministrativeUnits()) {
                    children.add(child);
                    addChldren(child, children, isCommunes);
                }
            }
        }
    }

    @Override
    public AdministrativeUnit getAdministrativeUnitByNameAndLevel(String name, Integer level, UUID parentId){
        AdministrativeUnit administrativeUnit = null;
        String sql = "SELECT entity FROM AdministrativeUnit entity where (1=1) ";
        if(name != null){
            sql += " and entity.name =:name ";
        }
        if(level != null){
            sql += " AND entity.level =:level ";
        }
        if(parentId != null){
            sql += " AND entity.parent.id =: parentId ";
        }
        Query q = manager.createQuery(sql, AdministrativeUnit.class);
        if(level != null){
            q.setParameter("level", level);
        }
        if(name != null){
            q.setParameter("name", name);
        }
        if(parentId != null){
            q.setParameter("parentId", parentId);
        }
        List<AdministrativeUnit> dtos = q.getResultList();
        if(dtos != null && dtos.size() > 0){
            administrativeUnit = dtos.get(0);
        }
        return administrativeUnit;
    }

    @Override
    public List<AdministrativeUnitDto> getAllByLevelNew(Integer level) {
        String sql = "SELECT new com.globits.vitimes.patient.dto.AdministrativeUnitDto(entity.id,entity.name,entity.code,entity.level) FROM AdministrativeUnit entity where entity.level =:level ";
        Query q = manager.createQuery(sql, AdministrativeUnitDto.class);
        q.setParameter("level", level);
        List<AdministrativeUnitDto> dtos = q.getResultList();
        return dtos;
    }
}
