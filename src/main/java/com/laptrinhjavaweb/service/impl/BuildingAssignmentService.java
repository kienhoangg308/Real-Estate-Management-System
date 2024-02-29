package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.service.IBuildingAssignmentService;

//@Service
public class BuildingAssignmentService implements IBuildingAssignmentService {

//    @Autowired
//    private BuildingAssignmentRepository buildingAssignmentRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private List<Long> findItemOfSourceNotTarget(List<Long> source, List<Long> target) {
//        List<Long> itemOfSourceButNotTarget = new ArrayList<>();
//        for(int i =0;i<source.size();i++) {
//            if(!target.contains(source.get(i))) {
//                itemOfSourceButNotTarget.add(source.get(i));
//            }
//        }
//        return itemOfSourceButNotTarget;
//    }
//
//    @Override
//    public ResponseDTO loadStaff(Long buildingId) {
//        ResponseDTO responseDTO = new ResponseDTO();
//        List<BuildingAssignmentSearchOutput> buildingAssignmentSearchOutputs = new ArrayList<>();
//        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1,"STAFF");
//        List<BuildingAssignmentEntity> buildingAssignmentEntities = buildingAssignmentRepository.findByBuilding(new BuildingEntity(buildingId));
//
//        List<Long> userIds = new ArrayList<>();
//        List<Long> userIdsOfAssignment = new ArrayList<>();
//        for(BuildingAssignmentEntity item: buildingAssignmentEntities){
//            userIdsOfAssignment.add(item.getUser().getId());
//        }
//        for(UserEntity userEntity: userEntities){
//            userIds.add(userEntity.getId());
//        }
//
//        List<Long> uncheckedUserIds = findItemOfSourceNotTarget(userIds,userIdsOfAssignment);
//
//        for(UserEntity userEntity : userEntities){
//            BuildingAssignmentSearchOutput buildingAssignmentSearchOutput = new BuildingAssignmentSearchOutput();
//            for(Long item : userIdsOfAssignment){
//                if(userEntity.getId().equals(item)){
//                    buildingAssignmentSearchOutput.setFullName(userEntity.getFullName());
//                    buildingAssignmentSearchOutput.setChecked("checked");
//                    buildingAssignmentSearchOutput.setStaffId(userEntity.getId());
//                    buildingAssignmentSearchOutputs.add(buildingAssignmentSearchOutput);
//                }
//            }
//        }
//
//        for(UserEntity userEntity : userEntities){
//            BuildingAssignmentSearchOutput buildingAssignmentSearchOutput = new BuildingAssignmentSearchOutput();
//            for(Long item : uncheckedUserIds){
//                if(userEntity.getId().equals(item)){
//                    buildingAssignmentSearchOutput.setFullName(userEntity.getFullName());
//                    buildingAssignmentSearchOutput.setChecked("unchecked");
//                    buildingAssignmentSearchOutput.setStaffId(userEntity.getId());
//                    buildingAssignmentSearchOutputs.add(buildingAssignmentSearchOutput);
//                }
//            }
//        }
//
//
//        responseDTO.setMessage("success");
//        responseDTO.setData(buildingAssignmentSearchOutputs);
//
//
////        for(UserEntity userEntity : userEntities){
////            BuildingAssignmentSearchOutput buildingAssignmentSearchOutput = new BuildingAssignmentSearchOutput();
////            for(BuildingAssignmentEntity item: buildingAssignmentEntities) {
////                if (userEntity.getId().equals(item.getUser().getId())) {
////                    buildingAssignmentSearchOutput.setChecked("checked");
////                    buildingAssignmentSearchOutput.setFullName(userEntity.getFullName());
////                    buildingAssignmentSearchOutputs.add(buildingAssignmentSearchOutput);
////                }
////            }
////        }
////
////        for(UserEntity userEntity : userEntities){
////            for(BuildingAssignmentSearchOutput item: buildingAssignmentSearchOutputs){
////
////            }
////        }
//        return responseDTO;
//    }
//
//    @Override
//    public void delete(BuildingEntity buildingEntity) {
//        List<BuildingAssignmentEntity> buildingAssignmentEntities = buildingAssignmentRepository.findByBuilding(buildingEntity);
//        if(buildingAssignmentEntities.size() != 0){
//            for(BuildingAssignmentEntity i : buildingAssignmentEntities){
//                buildingAssignmentRepository.delete(i.getId());
//            }
//        }
//    }
}
