package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.AssignmentInputModel;
import com.laptrinhjavaweb.dto.AssignmentOutputModel;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingEditDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.searchOutput.*;
import com.laptrinhjavaweb.entity.*;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictEnum;
import com.laptrinhjavaweb.repository.BuildingAssignmentRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.UploadFileUtils;
import com.laptrinhjavaweb.utils.ValidateUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private RentAreaConverter rentAreaConverter;



    public List<BuildingTypesEnum> getTypeCode(){
        List<BuildingTypesEnum> buildingTypes = Arrays.asList(BuildingTypesEnum.class.getEnumConstants());
        return buildingTypes;
    }

    public List<String> getType(){
        List<String> results = new ArrayList<>();
        List<BuildingTypesEnum> buildingTypes = Arrays.asList(BuildingTypesEnum.class.getEnumConstants());
        for(BuildingTypesEnum item: buildingTypes){
            results.add(item.getValue());
        }
        return results;
    }

    public List<DistrictEnum> getDistrictCode(){

        List<String> districtsName = new ArrayList<>();
        List<DistrictEnum> districts = Arrays.asList(DistrictEnum.class.getEnumConstants());
        List<DistrictEnum> results = new ArrayList<>();
        for(DistrictEnum item: districts){
            districtsName.add(item.getValue());
        }
        for(String item: districtsName){
            results.add(DistrictEnum.fromValue(item));
        }
        return results;

    }

    @Override
    public List<BuildingSearchOutput> getAllBuildings(Pageable pageable) {
        List<BuildingEntity> buildingEntities = buildingRepository.getAllBuildings(pageable);
        List<BuildingSearchOutput> results = new ArrayList<>();
        for(BuildingEntity buildingEntity : buildingEntities){
            BuildingSearchOutput buildingSearchOutput = buildingConverter.convertToBuildingSearchOutput(buildingEntity);
            results.add(buildingSearchOutput);
        }
        return results;
    }

    @Override
    public int countTotalItems() {
        return buildingRepository.countTotalItems();
    }

    private List<Long> findItemOfSourceNotTarget(List<Long> source, List<Long> target) {
        List<Long> itemOfSourceButNotTarget = new ArrayList<>();
        for(int i =0;i<source.size();i++) {
            if(!target.contains(source.get(i))) {
                itemOfSourceButNotTarget.add(source.get(i));
            }
        }
        return itemOfSourceButNotTarget;
    }

    @Override
    public ResponseDTO loadStaff(Long buildingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId);

        List<Long> userIds = userEntities.stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList());

        List<UserEntity> users = buildingEntity.getUser();
        List<Long> assignedUserIds = users.stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList());

        List<Long> uncheckedUserIds = findItemOfSourceNotTarget(userIds, assignedUserIds);

//        userEntities.stream().forEach(userEntity -> {
//            BuildingAssignmentSearchOutput buildingAssignmentSearchOutput = new BuildingAssignmentSearchOutput();
//            buildingAssignmentSearchOutput.setFullName(userEntity.getFullName());
//            buildingAssignmentSearchOutput.setStaffId(userEntity.getId());
//            if (assignedUserIds.contains(userEntity.getId())) {
//                buildingAssignmentSearchOutput.setChecked("checked");
//                buildingAssignmentSearchOutputs.add(buildingAssignmentSearchOutput);
//            } else if (uncheckedUserIds.contains(userEntity.getId())) {
//                buildingAssignmentSearchOutput.setChecked("unchecked");
//                buildingAssignmentSearchOutputs.add(buildingAssignmentSearchOutput);
//            }
//        });

        List<BuildingAssignmentSearchOutput> buildingAssignmentSearchOutputs = userEntities.stream()
                .map(userEntity -> {
                    BuildingAssignmentSearchOutput buildingAssignmentSearchOutput = new BuildingAssignmentSearchOutput();
                    buildingAssignmentSearchOutput.setFullName(userEntity.getFullName());
                    buildingAssignmentSearchOutput.setStaffId(userEntity.getId());
                    if (assignedUserIds.contains(userEntity.getId())) {
                        buildingAssignmentSearchOutput.setChecked("checked");
                    } else if (uncheckedUserIds.contains(userEntity.getId())) {
                        buildingAssignmentSearchOutput.setChecked("unchecked");
                    }
                    return buildingAssignmentSearchOutput;
                })
                .collect(Collectors.toList());

        responseDTO.setMessage("success");
        responseDTO.setData(buildingAssignmentSearchOutputs);

        return responseDTO;
    }

    @Override
    public BuildingSearchOutput getBuildigSearch(BuildingDTO buildingDTO, Pageable pageable) {
        return null;
    }

    public List<String> getDistrict(){
        List<String> results = new ArrayList<>();
        List<DistrictEnum> districts = Arrays.asList(DistrictEnum.class.getEnumConstants());
        for(DistrictEnum item: districts){
            results.add(item.getValue());
        }
        return results;
    }

    private List<BuildingSearchOutput> checkDuplicates(List<BuildingSearchOutput> buildingSearchOutputs, BuildingSearchOutput buildingSearchOutput){
        List<Long> ids = new ArrayList<>();
        for(BuildingSearchOutput item: buildingSearchOutputs){
            ids.add(item.getId());
        }
        if(!ids.contains(buildingSearchOutput.getId())){
            buildingSearchOutputs.add(buildingSearchOutput);
        }
        return buildingSearchOutputs;
    }



    private List<Long> remove(List<Long> staffIdsToDelete,List<Long> currentIds){
        for(int i=0;i<staffIdsToDelete.size();i++) {
            currentIds.remove(staffIdsToDelete.get(i));
        }
        return currentIds;
    }

    private List<Integer> convertRentAreas(String rentArea){
        List<Integer> result = new ArrayList<>();
        String[] arrOfAreas = rentArea.split(",");
        for(int i =0; i< arrOfAreas.length;i++){
            result.add(Integer.parseInt(arrOfAreas[i]));
        }
        return result;
    }


    public List<BuildingSearchOutput> findAll() {
        List<BuildingSearchOutput> results = new ArrayList<>();
        List<BuildingEntity> entities = buildingRepository.findAll();
        for (BuildingEntity item: entities){
            List<RentAreaSearchOutput> rentAreaSearchOutputs = rentAreaService.findByBuilding(new BuildingEntity(item.getId()));
            BuildingSearchOutput buildingSearchOutput = buildingConverter.convertToBuildingSearchOutput(item);
            results.add(buildingSearchOutput);
        }
        return results;
    }

    private Map<String, Object> validateSearchParams(Map<String, Object> params) {
        Map<String, Object> validParams = new HashMap<>();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (ValidateUtils.isValid(entry.getValue())) {
                validParams.put(entry.getKey().toLowerCase(), entry.getValue());
            }
        }
        return validParams;
    }

    @Override
    public AssignmentOutputModel assignBuildingToStaffs(AssignmentInputModel assignmentInputModel) {
        Long buildingId = assignmentInputModel.getBuildingId();
        List<Long> inputIds = assignmentInputModel.getStaffIds();

        BuildingEntity building = buildingRepository.findById(buildingId);

        List<UserEntity> newUsers = userRepository.findByIdIn(inputIds);
        building.setUser(newUsers);
        buildingRepository.save(building);

        String currentIdslistString = inputIds.toString();
        currentIdslistString = currentIdslistString.substring(1, currentIdslistString.length()-1);
        AssignmentOutputModel assignmentOutputModel = new AssignmentOutputModel();
        assignmentOutputModel.setStaffIds(currentIdslistString);
        return assignmentOutputModel;
    }

    @Override
    public List<BuildingSearchOutput> findBuilding(BuildingDTO buildingDTO, Pageable pageable) {
        List<BuildingSearchOutput> results = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = convertToBuildingSearchBuilder(buildingDTO);
        List<BuildingEntity> buildingEntities = buildingRepository.searchBuilding(buildingSearchBuilder, pageable);

        for (BuildingEntity item : buildingEntities) {
            List<RentAreaSearchOutput> rentAreaSearchOutputs = rentAreaService.findByBuilding(new BuildingEntity(item.getId()));
            BuildingSearchOutput buildingSearchOutput = buildingConverter.convertToBuildingSearchOutput(item, rentAreaSearchOutputs);

            checkDuplicates(results,buildingSearchOutput);
        }
        return results;
    }

    @Override
    public List<BuildingSearchOutput> search(BuildingDTO buildingDTO) {
        List<BuildingSearchOutput> results = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = convertToBuildingSearchBuilder(buildingDTO);
        List<BuildingEntity> buildingEntities = buildingRepository.search(buildingSearchBuilder);

        for (BuildingEntity item : buildingEntities) {
            List<RentAreaSearchOutput> rentAreaSearchOutputs = rentAreaService.findByBuilding(new BuildingEntity(item.getId()));
            BuildingSearchOutput buildingSearchOutput = buildingConverter.convertToBuildingSearchOutput(item, rentAreaSearchOutputs);

            checkDuplicates(results,buildingSearchOutput);
        }
        return results;
    }

    @Override
    @Transactional
    public BuildingEditDTO editBuilding(BuildingEditDTO buildingEditDTO) {

        BuildingEntity building = buildingConverter.convertFromBuildingEditDTOToEntity(buildingEditDTO);
        if(buildingEditDTO.getId() != null){
            BuildingEntity buildingEntity = buildingRepository.findById(buildingEditDTO.getId());
            if(buildingEditDTO.getImageBase64() == null){
                building.setImage(buildingEntity.getImage());
            }
            building.setUser(buildingEntity.getUser());
        }
        List<Integer> values = convertRentAreas(buildingEditDTO.getRentArea());
//        for(Integer i : values){
//            RentAreaEntity rentAreaEntity = new RentAreaEntity();
//            rentAreaEntity.setValue(i);
//            rentAreaEntity.setBuilding(building);
//            rentAreaEntities.add(rentAreaEntity);
//        }
        List<RentAreaEntity> rentAreaEntities = values.stream()
                .map(item ->{
                    RentAreaEntity rentAreaEntity = new RentAreaEntity();
                    rentAreaEntity.setValue(item);
                    rentAreaEntity.setBuilding(building);
                    return rentAreaEntity;
                })
                .collect(Collectors.toList());
        building.setRentAreas(rentAreaEntities);
        saveThumbnail(buildingEditDTO, building);
        //buildingRepository.save(building);
        BuildingEditDTO result = buildingConverter.convertToBuildingEditDTO(buildingRepository.save(building),rentAreaConverter.convertToRentAreaSearchOutputs(rentAreaEntities));
        return result;
    }

    @Override
    public BuildingEditDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id);
        List<RentAreaSearchOutput> rentAreaSearchOutputs = rentAreaService.findByBuilding(buildingEntity);
        BuildingEditDTO buildingEditDTO = buildingConverter.convertToBuildingEditDTO(buildingEntity,rentAreaSearchOutputs);
        return buildingEditDTO;
    }

    @Override
    @Transactional
    public void deleteItems(List<Long> ids) {
        List<BuildingEntity> buildings = buildingRepository.findByIdIn(ids);
        if (buildings.size() == ids.size()) {
            buildingRepository.deleteByIdIn(ids);
        }

    }

    private BuildingSearchBuilder convertToBuildingSearchBuilder(BuildingDTO buildingDTO){
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
                                                                .setName(buildingDTO.getName())
                                                                .setNumberOfBasement(buildingDTO.getNumberOfBasement())
                                                                .setFloorArea(buildingDTO.getFloorArea())
                                                                .setWard(buildingDTO.getWard())
                                                                .setStreet(buildingDTO.getStreet())
                                                                .setRentAreaFrom(buildingDTO.getRentAreaFrom())
                                                                .setRentAreaTo(buildingDTO.getRentAreaTo())
                                                                .setDistrict(buildingDTO.getDistrict())
                                                                .setType(buildingDTO.getBuildingTypes())
                                                                .setFloorArea(buildingDTO.getFloorArea())
                                                                .setDirection(buildingDTO.getDirection())
                                                                .setLevel(buildingDTO.getLevel())
                                                                .setStaffId(buildingDTO.getStaffId())
                                                                .setManagerName(buildingDTO.getManagerName())
                                                                .setManagerPhone(buildingDTO.getManagerPhone())
                                                                .setRentPriceTo(buildingDTO.getRentPriceTo())
                                                                .setRentPriceFrom(buildingDTO.getRentPriceFrom())
                                                                .build();
        return result;

    }

    private void saveThumbnail(BuildingEditDTO buildingEditDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingEditDTO.getImageName();
        if (null != buildingEditDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    //File file = new File("/Users/hoangtrungkien/Documents/home/office" + buildingEntity.getImage());
                    File file = new File("/home/javahosting/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingEditDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }




}
